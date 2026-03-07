package com.sj14apps.jsonlist.core;

import java.util.ArrayList;
import java.util.Objects;

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
    public ArrayList<JsonNode> children;

    public JsonNode root() {
        isRoot = true;
        return this;
    }

    public JsonNode object() {
        isObject = true;
        children = new ArrayList<>();
        return this;
    }

    public JsonNode array() {
        isArray = true;
        children = new ArrayList<>();
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                "," + getKeyToString() +
                "," + getValueToString() +
                ",\"isObject\":" + isObject +
                ",\"isArray\":" + isArray +
                ",\"isRoot\":" + isRoot +
                ",\"children\":" + children +
                ",\"parent\": " + (parent== null? null: "{ \"id\":" + parent.id + "," + parent.getKeyToString() + "}") +
                '}';
    }

    public String getKeyToString(){
        return "\"key\":" + (key!=null && !key.startsWith("\"")?"\"":"") +  key + (key!=null && !key.startsWith("\"")?"\"":"");
    }

    public String getValueToString(){
        return "\"value\":" + (value!=null && !value.startsWith("\"")?"\"":"") + value + (value!=null && !value.startsWith("\"")?"\"":"");
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

    public void setChildren(ArrayList<JsonNode> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JsonNode jsonNode = (JsonNode) o;
        return isObject == jsonNode.isObject && isArray == jsonNode.isArray && isRoot == jsonNode.isRoot && Objects.equals(key, jsonNode.key) && Objects.equals(value, jsonNode.value) && Objects.equals(id, jsonNode.id) && Objects.equals(children, jsonNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, id, isObject, isArray, isRoot, children);
    }
}
