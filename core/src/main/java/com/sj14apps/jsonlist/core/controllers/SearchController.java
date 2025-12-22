package com.sj14apps.jsonlist.core.controllers;

import com.sj14apps.jsonlist.core.JsonData;
import com.sj14apps.jsonlist.core.JsonFunctions;
import com.sj14apps.jsonlist.core.SearchItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class SearchController {

    private final ExecutorService searchExecutor = Executors.newSingleThreadExecutor();
    private Future<?> searchTask;

    public abstract void search(JsonData data, String string);
    public abstract void setEvents(JsonData data);

    public abstract void showSearchView();

    public abstract void hideSearchView();


    protected void search(JsonData data, String string, SearchEvent event) {

        if (searchTask != null && !searchTask.isDone()) {
            searchTask.cancel(true);
        }

        if (string.isEmpty()){
            event.empty();
            return;
        }

        event.startSearch();


        searchTask = searchExecutor.submit(()->{
            ArrayList<SearchItem> result = JsonFunctions.searchItem(data,string);

            if (Thread.currentThread().isInterrupted()){
                return;
            }

            event.result(result);


        });
    }

    public interface SearchEvent{

        void empty();
        void startSearch();
        void result(ArrayList<SearchItem> result);

    }
}
