package com.sj14apps.jsonlist.core.controllers;

import com.sj14apps.jsonlist.core.AppState;

import java.util.regex.Pattern;

public abstract class RawJsonView{


    int textColor;
    int keyColor;
    int numberColor;
    int booleanAndNullColor;
    int bgColor;
    public boolean showJson;
    public boolean isRawJsonLoaded;

    public RawJsonView(int textColor, int keyColor, int numberColor, int booleanAndNullColor, int bgColor) {
        this.textColor = textColor;
        this.keyColor = keyColor;
        this.numberColor = numberColor;
        this.booleanAndNullColor = booleanAndNullColor;
        this.bgColor = bgColor;
    }

    public String generateHtml(String jsonStr, AppState state) {

        String textColorHex = String.format("#%06X", (0xFFFFFF & textColor));
        String keyColorHex = String.format("#%06X", (0xFFFFFF & keyColor));
        String numberColorHex = String.format("#%06X", (0xFFFFFF & numberColor));
        String booleanAndNullColorHex = String.format("#%06X", (0xFFFFFF & booleanAndNullColor));
        String bgColorHex = String.format("#%06X", (0xFFFFFF & bgColor));

        if (state != null && state.isSyntaxHighlighting())
            jsonStr = highlightJsonSyntax(jsonStr);

        String style =
                ".key { color: " + keyColorHex + "; }" +
                        ".string { color: " + textColorHex + "; }" +
                        ".number { color: " + numberColorHex + "; }" +
                        ".boolean { color: " + booleanAndNullColorHex + "; }" +
                        ".null { color: " + booleanAndNullColorHex + "; }";

        return "<html>" +
                "<head>" +
                "<style>" +
                "body { background-color: " + bgColorHex + "; color: " + textColorHex + "; padding: 10px; }" +
                style +
                "</style>" +
                "</head>" +
                "<body>" +
                "<pre>" + jsonStr + "</pre>" +
                "</body>" +
                "</html>";
    }

    private static final Pattern KEY_PATTERN = Pattern.compile("\"([^\"]*)\"(?=\\s*:)");
    private static final Pattern STRING_PATTERN = Pattern.compile(":\\s*\"([^\"]*)\"");
    private static final Pattern NUMBER_PATTERN = Pattern.compile(":\\s*(-?\\d+(?:\\.\\d+)?)");
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile(":\\s*(true|false)");
    private static final Pattern NULL_PATTERN = Pattern.compile(":\\s*(null)");

    private String highlightJsonSyntax(String json) {
        json = json.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");

        json = KEY_PATTERN.matcher(json).replaceAll("<span class='key'>\"$1\"</span>"); // Keys
        json = STRING_PATTERN.matcher(json).replaceAll(": <span class='string'>\"$1\"</span>"); // Strings
        json = NUMBER_PATTERN.matcher(json).replaceAll(": <span class='number'>$1</span>"); // Numbers
        json = BOOLEAN_PATTERN.matcher(json).replaceAll(": <span class='boolean'>$1</span>"); // Booleans
        json = NULL_PATTERN.matcher(json).replaceAll(": <span class='null'>$1</span>"); // Null

        return json;
    }

    public abstract void toggleSplitView();
    public abstract void ShowJSON();
    public abstract void updateRawJson(String string);
}


