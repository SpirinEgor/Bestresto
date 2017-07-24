package com.bestresto.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sergey on 24.07.17.
 */

public class QueryConditions{
    public ArrayList<String> kitchenConditions;
    public HashMap<String, String> otherConditions;
    public HashMap<String, String> orderByConditions;

    public QueryConditions(){
        this.kitchenConditions = new ArrayList<>();
        this.otherConditions = new HashMap<>();
        this.orderByConditions = new HashMap<>();
    }
}
