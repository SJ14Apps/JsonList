package com.sj14apps.jsonlist.core;

import java.util.ArrayList;
import java.util.Stack;

public class JsonData {
    String path = "";
    @Deprecated
    ArrayList<ListItem> rootList = new ArrayList<>();
    @Deprecated
    ArrayList<ListItem> currentList = new ArrayList<>();

    JsonNode rootNode;
    JsonNode currentNode;
    Stack<Integer> previousPosStack = new Stack<>();
    String rawData = "";
    String fileName;

    int previousPos = -1;
    public int searchMode = 0;
    static int maxPathNameLength = 3;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Deprecated
    public ArrayList<ListItem> getRootList() {
        return rootList;
    }
    @Deprecated
    public void setRootList(ArrayList<ListItem> rootList) {
        this.rootList = rootList;
    }
    @Deprecated
    public ArrayList<ListItem> getCurrentList() {
        return currentList;
    }
    @Deprecated
    public void setCurrentList(ArrayList<ListItem> currentList) {
        this.currentList = currentList;
    }


    public JsonNode getRootNode() {
        return rootNode;
    }
    public void setRootNode(JsonNode rootNode) {
        this.rootNode = rootNode;
    }
    public JsonNode getCurrentNode() {
        return currentNode;
    }
    public void setCurrentNode(ArrayList<ListItem> currentList) {
        this.currentList = currentList;
    }


    public void setRawData(String data) {
        this.rawData = data;
    }

    public String getRawData() {
        return rawData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isEmptyPath(){
        return path.equals("");
    }
    public void clearPath(){
        path = "";
    }
    public String[] splitPath(){
        return path.split("///");
    }
    public static String[] splitPath(String path){
        return path.split("///");
    }

    public boolean isRootNodeNull(){
        return rootNode == null;
    }

    public void goBack(){

        if (!previousPosStack.isEmpty())
            previousPos = previousPosStack.pop();

        String[] pathStrings = splitPath();
        clearPath();
        for (int i = 0; i < pathStrings.length-1; i++) {
            setPath(path.concat((isEmptyPath()?"":"///") + pathStrings[i]));
        }

    }

    public void addPreviousPos(int pos){
        previousPosStack.push(pos);
    }

    public int getPreviousPos(){
        return previousPos;
    }

    public void clearPreviousPos(){
        previousPosStack.clear();
    }

    public static String getPathFormat(String path){
        String[] pathStrings = splitPath(path);
        StringBuilder builder = new StringBuilder();
        builder.append(pathStrings.length > maxPathNameLength ? "..." : pathStrings[0]);

        for (int i = pathStrings.length > maxPathNameLength ? pathStrings.length- maxPathNameLength : 1; i < pathStrings.length; i++) {
            builder.append("/").append(getName(pathStrings[i]));
        }

        return builder.toString();
    }

    public static String getName(String str){
        if (str.startsWith("{") && str.contains("}") && str.substring(1, str.indexOf("}")).matches("^[0-9]+"))
            return str.substring(str.indexOf("}")+1);
        return str;
    }
}
