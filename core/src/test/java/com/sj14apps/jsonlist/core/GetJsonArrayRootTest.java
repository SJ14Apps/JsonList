package com.sj14apps.jsonlist.core;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Test;

import java.util.ArrayList;

public class GetJsonArrayRootTest {

    @Test
    public void getJsonArrayRootTest(){
        String data = "[ {data2:\"123\"} ]";
        JsonElement element = JsonParser.parseString(data);

        JsonArray array = element.getAsJsonArray();

        JsonNode itemsArr = JsonFunctions.getJsonArrayRoot(array);
        JsonNode expectedArr = new JsonNode().array();
        expectedArr.setKey(JsonNode.ARRAY_OBJECTS_NAME);

        JsonNode listItems = new JsonNode().object();
        listItems.setId(0);

        JsonNode OLItem = new JsonNode();

        OLItem.setKey("data2");
        OLItem.setValue("123");
        OLItem.setParent(listItems);
        listItems.children.add(OLItem);

        expectedArr.children.add(listItems);

//        ArrayList<ArrayList<ListItem>> arrayListArrayList = new ArrayList<>();
//        arrayListArrayList.add(listItems);
//        root.setListObjects(arrayListArrayList);
//        expectedArr.add(root);
        assertEquals(expectedArr,itemsArr);

    }
    /*@Test
    public void getJsonArrayRootTest2(){
        String data = "[{\"data2\":123},1242,true,null]";
        JsonElement element = JsonParser.parseString(data);

        JsonArray array = element.getAsJsonArray();

        ArrayList<ListItem> itemsArr = JsonFunctions.getJsonArrayRoot(array);


        ArrayList<ListItem> expectedArr = new ArrayList<>();

        ListItem root = new ListItem();
        root.setName(ListItem.ARRAY_ITEMS_NAME);
        root.setIsArray(true);

        ArrayList<ArrayList<ListItem>> arrayListArrayList = new ArrayList<>();

        ArrayList<ListItem> items1 = new ArrayList<>();
        ListItem item1 = new ListItem();
        item1.setName("data2");
        item1.setValue("123");
        items1.add(item1);
        arrayListArrayList.add(items1);

        ArrayList<ListItem> items2 = new ArrayList<>();
        ListItem item2 = new ListItem();
        item2.setValue("1242");
        items2.add(item2);
        arrayListArrayList.add(items2);

        ArrayList<ListItem> items3 = new ArrayList<>();
        ListItem item3 = new ListItem();
        item3.setValue("true");
        items3.add(item3);
        arrayListArrayList.add(items3);

        ArrayList<ListItem> items4 = new ArrayList<>();
        ListItem item4 = new ListItem();
        item4.setValue("null");
        items4.add(item4);
        arrayListArrayList.add(items4);


        root.setListObjects(arrayListArrayList);

        expectedArr.add(root);
        assertEquals(expectedArr,itemsArr);

    }

    @Test
    public void testGetJsonArrayRootWithEmptyArray() {
        JsonArray jsonArray = new JsonArray();
        ArrayList<ListItem> result = JsonFunctions.getJsonArrayRoot(jsonArray);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ListItem.ARRAY_OBJECTS_NAME, result.get(0).getName());
        assertTrue(result.get(0).isArray());
        assertEquals(0, result.get(0).getListObjects().size());
    }
*/
}
