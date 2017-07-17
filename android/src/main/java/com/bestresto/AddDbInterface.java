package com.bestresto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;

/**
 * Created by voudy on 18.07.17.
 */

public interface AddDbInterface {

    SQLiteDatabase db = null;

    public void addAllDb(List<HashMap<String, Object>> data, Context context);

    public void setDb(SQLiteDatabase db);

    public void cleanTable();
}
