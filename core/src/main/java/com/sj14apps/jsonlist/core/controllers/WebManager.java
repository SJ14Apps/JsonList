package com.sj14apps.jsonlist.core.controllers;

import com.google.gson.Gson;
import com.sj14apps.jsonlist.core.AppInfo;
import com.sj14apps.jsonlist.core.AppState;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class WebManager {
    private OkHttpClient client;

    public void getFromUrl(String url, WebCallback webCallback){
        if (url.trim().isEmpty())
            return;

        if (!url.startsWith("http"))
            url = "https://" + url;

        createClientIfNeeded();

        @SuppressWarnings("java/ssrf")
        Request request;
        try {
            // CodeQL [java/ssrf] Safe to ignore: this is a client-side app, user controls their own URLs.
            request = new Request.Builder()
                    .url(url)
                    .build();

        }catch (IllegalArgumentException e){
            webCallback.onInvalidURL();
            return;
        }

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                webCallback.onFailure();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.body() == null) {
                    webCallback.onFailure(response.code());
                    return;
                }
                webCallback.onResponse(response.body().string());
            }
        });
        webCallback.onStarted();
    }

    public void checkHasNewVersion(AppState state, String url, int currentVersionCode, CheckNewVersionCallback callback){
        getFromUrl(url, new WebManager.WebCallback() {
            @Override
            public void onStarted() {
                System.out.println("Started");
            }

            @Override
            public void onInvalidURL() {
                System.out.println("Invalid URL");
            }

            @Override
            public void onResponse(String data) {
                state.setLastCheckForUpdate(System.currentTimeMillis()/1000);
                callback.saveState();

                AppInfo info = new Gson().fromJson(data, AppInfo.class);
                if (info == null)
                    return;

                if (currentVersionCode >= info.getVersion_code() && currentVersionCode >= state.getNewVersionCode())
                    return;

                state.setNewVersionCode(info.getVersion_code());
                state.setNewVersionName(info.getVersion());
                state.setHasNewVersion(true);
                callback.saveState();

                callback.updateUI();
            }

            @Override
            public void onFailure() {
                System.out.println("Fail");
            }

            @Override
            public void onFailure(int code) {
                System.out.println("Fail, code:" + code);
            }
        });
    }

    private void createClientIfNeeded(){
        if (client == null)
            client = new OkHttpClient();
    }

    public void shutdownClient() {
        if (client == null)
            return;
        client.connectionPool().evictAll();
        client.dispatcher().executorService().shutdown();
    }
    public interface WebCallback {
        void onStarted();
        void onInvalidURL();
        void onResponse(String data);
        void onFailure();
        void onFailure(int code);
    }

    public interface CheckNewVersionCallback {
        void saveState();
        void updateUI();

    }
}
