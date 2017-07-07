package com.bestresto;

import com.bestresto.data.DatabaseContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    List<HashMap<String, Object>> parserSimple(String json){
        List<HashMap<String, Object>> data = new ArrayList<>();
        try {
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(json);
            JSONObject DishObject;
            for (int i = 0; i < jsonArray.length(); i++){
                DishObject = jsonArray.getJSONObject(i);
                HashMap<String, Object> values = new HashMap<>();
                values.put(DatabaseContract.DishesColumns.INDEXID, DishObject.getString("indexid"));
                values.put(DatabaseContract.DishesColumns.PARENT_ID, DishObject.getString("parent_id"));
                values.put(DatabaseContract.DishesColumns.ACTIVE, DishObject.getString("active"));
                values.put(DatabaseContract.DishesColumns.CAPTION, DishObject.getString("caption"));
                values.put(DatabaseContract.DishesColumns.KITCHEN, DishObject.getString("kitchen"));
                values.put(DatabaseContract.DishesColumns.PRICE, DishObject.getString("price"));
                values.put(DatabaseContract.DishesColumns.DESC, DishObject.getString("desc"));
                values.put(DatabaseContract.DishesColumns.FIRST_PAGE, DishObject.getString("first_page"));
                values.put(DatabaseContract.DishesColumns.PICTURE, DishObject.getString("picture"));
                values.put(DatabaseContract.DishesColumns.DOPPIC, DishObject.getString("dopPic"));
                values.put(DatabaseContract.DishesColumns.SORT, DishObject.getString("sort"));
                values.put(DatabaseContract.DishesColumns.REITING, DishObject.getString("reiting"));
                values.put(DatabaseContract.DishesColumns.GARANT, DishObject.getString("garant"));
                values.put(DatabaseContract.DishesColumns.SEARCHTAGS, DishObject.getString("searchTags"));
                values.put(DatabaseContract.DishesColumns.CREATEDATE, DishObject.getString("createDate"));
                data.add(values);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return data;
    }

    List<HashMap<String, Object>> parserJackson(String json) throws IOException {
        List<HashMap<String, Object>> data;
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(json,  new TypeReference<List<HashMap<String, Object>>>() { });
        return data;
    }

}
