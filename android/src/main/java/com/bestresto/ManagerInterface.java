package com.bestresto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public interface ManagerInterface {

    SQLiteDatabase db = null;

    public void addAllDb(ArrayList<HashMap<String, Object>> data);

    public void addDb(HashMap<String, Object> data);

    public void setDb(SQLiteDatabase db);

    public void cleanTable();

    public Object getValue(Cursor cursor, String column, int index);

}
