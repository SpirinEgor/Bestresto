package com.bestresto.Types;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DbHelper;
import com.bestresto.ManagerInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantTypesManager implements ManagerInterface {

    private SQLiteDatabase db;

    @Override
    public void cleanTable(){
        DbHelper.createRestaurantTypes(db);
        db.delete(DatabaseContract.RestaurantTypesColumns.TABLE_NAME, null, null);
    }

    @Override
    public void setDb(SQLiteDatabase db){
        this.db = db;
    }

    private int currentPrimeNumber = 0;

    @Override
    public void addAllDb(ArrayList<HashMap<String, Object>> data){
        this.cleanTable();
        ArrayList<Integer> primeNumber = generatePrimeNumber();
        int i = 0;
        for (HashMap<String, Object> type: data){
            currentPrimeNumber = primeNumber.get(i);
            addDb(type);
            ++i;
        }
    }

    @Override
    public void addDb(HashMap<String, Object> type) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.RestaurantTypesColumns.INDEXID  ,
                (type.get(DatabaseContract.RestaurantTypesColumns.INDEXID) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.RestaurantTypesColumns.INDEXID).toString())));
        values.put(DatabaseContract.RestaurantTypesColumns.CAPTION,
                (type.get(DatabaseContract.DishesColumns.CAPTION) == null ? "" : type.get(DatabaseContract.DishesColumns.CAPTION).toString()));
        values.put(DatabaseContract.RestaurantTypesColumns.SORT,
                (type.get(DatabaseContract.RestaurantTypesColumns.SORT) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.RestaurantTypesColumns.ACTIVE).toString())));
        values.put(DatabaseContract.RestaurantTypesColumns.ACTIVE,
                (type.get(DatabaseContract.RestaurantTypesColumns.ACTIVE) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.RestaurantTypesColumns.ACTIVE).toString())));
        values.put(DatabaseContract.RestaurantTypesColumns.PRIMEID, currentPrimeNumber);
        //Log.d(type.get(DatabaseContract.DishesColumns.CAPTION).toString(), String.valueOf(primeNumber.get(i)));
        long newRowId = db.insert(DatabaseContract.RestaurantTypesColumns.TABLE_NAME, null, values);
    }

    @Override
    public Object getValue(Cursor cursor, String column, int index) {
        switch (column) {
            case DatabaseContract.RestaurantTypesColumns.CAPTION:
                return cursor.getString(index);
            default:
                return cursor.getInt(index);
        }
    }

    public int getRestaurantNumber(String req){
        ArrayList<Integer> kitchens = stringToArray(req);
        int result = 1;
        for (int kit: kitchens){
            String[] projection = {
                    DatabaseContract.RestaurantTypesColumns.INDEXID,
                    DatabaseContract.RestaurantTypesColumns.PRIMEID,
                    DatabaseContract.RestaurantTypesColumns.ACTIVE
            };
            Cursor cursor = db.query(
                    DatabaseContract.RestaurantTypesColumns.TABLE_NAME,   // таблица
                    projection,            // столбцы
                    DatabaseContract.DishesColumns.ACTIVE + " = 1" +
                            " AND " + DatabaseContract.DishesColumns.INDEXID + " = " + kit,                  // столбцы для условия WHERE
                    null,                  // значения для условия WHERE
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);
            try {
                // Узнаем индекс каждого столбца
                int primeColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantTypesColumns.PRIMEID);
                while (cursor.moveToNext()) {
                    // Используем индекс для получения строки или числа
                    int currentPrime = cursor.getInt(primeColumnIndex);
                    result *= currentPrime;
                }
            }
            finally {
                cursor.close();
            }
        }
        return result;
    }

    private ArrayList<Integer> generatePrimeNumber(){
        int upNumber = 10000;
        ArrayList<Integer> result = new ArrayList<Integer>();
        boolean[] used = new boolean[upNumber];
        for (int i = 2; i < upNumber; ++i){
            if (!used[i]) {
                result.add(i);
                for (int j = i; j < upNumber; j += i) {
                    used[j] = true;
                }
            }
        }
        return result;
    }

    private ArrayList<Integer> stringToArray(String str){
        ArrayList<Integer> result = new ArrayList<>();
        String cur = "";
        for (int i = 0; i < str.length(); ++i){
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                cur += str.charAt(i);
            }
            else{
                if (!cur.equals("")){
                    int num = Integer.parseInt(cur);
                    result.add(num);
                    cur = "";
                }
            }
        }
        if (!cur.equals("")){
            int num = Integer.parseInt(cur);
            result.add(num);
        }
        return result;
    }

    public String getRestaurants(int num){
        ArrayList<Integer> prime = simply(num);
        String result = "";
        for (int curPrime: prime){
            String[] projection = {
                    DatabaseContract.RestaurantTypesColumns.CAPTION,
                    DatabaseContract.RestaurantTypesColumns.PRIMEID,
                    DatabaseContract.RestaurantTypesColumns.ACTIVE
            };
            Cursor cursor = db.query(
                    DatabaseContract.RestaurantTypesColumns.TABLE_NAME,   // таблица
                    projection,            // столбцы
                    DatabaseContract.RestaurantTypesColumns.ACTIVE + " = 1" +
                            " AND " + DatabaseContract.RestaurantTypesColumns.PRIMEID + " = " + curPrime,                  // столбцы для условия WHERE
                    null,                  // значения для условия WHERE
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);
            try {
                // Узнаем индекс каждого столбца
                int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantTypesColumns.CAPTION);
                while (cursor.moveToNext()) {
                    // Используем индекс для получения строки или числа
                    String currentCaption = cursor.getString(captionColumnIndex);
                    result += currentCaption + ", ";
                }
            }
            finally {
                cursor.close();
            }
        }
        if (!result.equals(""))
            result = result.substring(0, result.length() - 2);
        return result;
    }

    private ArrayList<Integer> simply(int num){
        ArrayList<Integer> result = new ArrayList<>();
        int up = num;
        for (int i = 2; i * i <= up; ++i){
            if (num % i == 0){
                result.add(i);
                while (num % i == 0)
                    num /= i;
            }
        }
        if (num != 1)
            result.add(num);
        return result;
    }
}
