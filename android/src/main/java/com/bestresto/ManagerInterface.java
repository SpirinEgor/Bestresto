package com.bestresto;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;


public interface ManagerInterface {

    public void addAllDb(ArrayList<HashMap<String, Object>> data);

    public String createStringForSQLScript(HashMap<String, Object> data);

    public Object getValue(Cursor cursor, String column, int index);

}
