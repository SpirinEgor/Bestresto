package com.bestresto.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by voudy on 27.07.17.
 */

public class DatabaseGetter {

    private static SQLiteDatabase db;

    public DatabaseGetter(Context context) {
        db = new DbHelper(context).getReadableDatabase();
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

    public ArrayList<HashMap<String, Object>> makeData(QueryConditions queryConditions) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        String[] columns = queryConditions.getColumns();
        Cursor cursor = db.query(
                queryConditions.getTableName(),
                columns,
                (queryConditions.getWhenCondition().equals("") ? null : queryConditions.getWhenCondition()),
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
        db.close();
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
        return result;
    }

}

