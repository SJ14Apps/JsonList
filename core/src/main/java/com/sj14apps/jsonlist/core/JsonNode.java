package com.sj14apps.jsonlist.core;

import java.util.ArrayList;

public class JsonNode {
    public static final String ARRAY_NAME = "[...]";
    public static final String ARRAY_ITEMS_NAME = "[...]";
    public static final String ARRAY_OBJECTS_NAME = "[...]";
    public String key;
    public String value;
    public JsonNode parent;
    public Integer id;
    public boolean isObject;
    public boolean isArray;
    public boolean isRoot;
    public ArrayList<JsonNode> children = new ArrayList<>();

    public JsonNode createRoot() {
        isRoot = true;
        return this;
    }

    public JsonNode object() {
        isObject = true;
        return this;
    }

    public JsonNode array() {
        isArray = true;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"key\":" + (key!=null && !key.startsWith("\"")?"\"":"") +  key + (key!=null && !key.startsWith("\"")?"\"":"") +
                ",\"value\":" + (value!=null && !value.startsWith("\"")?"\"":"") + value + (value!=null && !value.startsWith("\"")?"\"":"") +
                ",\"isObject\":" + isObject +
                ",\"isArray\":" + isArray +
                ",\"children\":" + children +
                '}';
    }


    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setParent(JsonNode parent) {
        this.parent = parent;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsObject(boolean object) {
        isObject = object;
    }

    public void setIsArray(boolean array) {
        isArray = array;
    }

    public void setIsRoot(boolean root) {
        isRoot = root;
    }

    public void setChildren(ArrayList<JsonNode> children) {
        this.children = children;
    }


}
