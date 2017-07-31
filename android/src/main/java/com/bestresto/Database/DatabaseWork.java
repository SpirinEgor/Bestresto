package com.bestresto.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by voudy on 27.07.17.
 */

public class DatabaseWork {

    private static SQLiteDatabase db;

    public DatabaseWork(Context context) {
        db = new DbHelper(context).getWritableDatabase();
    }

    private static Object getValue(Cursor cursor, String column, int index, String tableName) {
        switch (tableName) {
            case DatabaseContract.DishesColumns.TABLE_NAME:
                return new DishManager().getValue(cursor, column, index);
            case DatabaseContract.RestaurantsColumns.TABLE_NAME:
                return new RestaurantManager().getValue(cursor, column, index);
            case DatabaseContract.KitchenTypesColumns.TABLE_NAME:
                return new KitchenTypesManager().getValue(cursor, column, index);
            case DatabaseContract.RestaurantTypesColumns.TABLE_NAME:
                return new RestaurantTypesManager().getValue(cursor, column, index);
            default:
                return "";
        }
    }

    public static synchronized ArrayList<HashMap<String, Object>> makeData(QueryConditions queryConditions) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        String[] columns = queryConditions.getColumns();
        Log.e("WHERE", queryConditions.getTableName()+ " " + queryConditions.getWhereCondition());
        Cursor cursor = db.query(
                queryConditions.getTableName(),
                columns,
                (queryConditions.getWhereCondition().equals("") ? null : queryConditions.getWhereCondition()),
                null,
                null,
                null,
                (queryConditions.getOrderByCondition().equals("") ? null : queryConditions.getOrderByCondition())
        );
        try{
            int[] index = new int[columns.length];
            for (int i = 0; i < columns.length; ++i) {
                index[i] = cursor.getColumnIndex(columns[i]);
            }
            while (cursor.moveToNext()){
                HashMap<String, Object> cur = new HashMap<>();
                for (int i = 0; i < columns.length; ++i){
                    cur.put(columns[i], getValue(cursor, columns[i], index[i], queryConditions.getTableName()));
                }
                data.add(cur);
            }
        }
        finally {
            cursor.close();
        }
        return data;
    }

    public static String prepare(String current){
        String result = "";
        int prev = 0;
        for (int i = 0; i < current.length(); ++i){
            if (current.charAt(i) == '"'){
                String tmp = current.substring(prev, i);
                result = result.concat(tmp).concat("\"");
                prev = i;
            }
        }
        result = result.concat(current.substring(prev, current.length()));
        result = "\"".concat(result).concat("\"");
        return result;
    }

    public static synchronized void insertContentValue(String tableName, ContentValues values) {
        db.insert(tableName, null, values);
    }

    public static synchronized void cleanTable(String tableName) {
        DbHelper.createTable(tableName);
        db.delete(tableName, null, null);
    }

    static synchronized void execSQL(String req) {
        db.execSQL(req);
    }

}

