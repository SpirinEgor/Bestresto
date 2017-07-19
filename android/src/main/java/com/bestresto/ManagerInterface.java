package com.bestresto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voudy on 18.07.17.
 */

public interface ManagerInterface {

    SQLiteDatabase db = null;

    public void addAllDb(ArrayList<HashMap<String, Object>> data);

    public void addDb(HashMap<String, Object> data);

    public void setDb(SQLiteDatabase db);

    public void cleanTable();

    public void openDb(Context context);

    public void closeDb();
}
