package com.bestresto.Types;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bestresto.AddDbInterface;
import com.bestresto.data.DatabaseContract;
import com.bestresto.data.dbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class KitchenTypesManager implements AddDbInterface{

    private SQLiteDatabase db;

    private void openbd(Context context){
        dbHelper dbh = new dbHelper(context);
        db = dbh.getWritableDatabase();
    }

    private void closebd(){
        db.close();
    }

    public void cleanTable(){
        db.delete(DatabaseContract.KitchenTypesColumns.TABLE_NAME, null, null);
    }

    public void setDb(SQLiteDatabase db){
        this.db = db;
    }

    public void addAllDb(List<HashMap<String, Object>> data, Context context){
        this.cleanTable();
        addDB(data);
    }

    public void addDB(List<HashMap<String, Object>> info){
        ArrayList<Integer> primeNumber = generatePrimeNumber();
        int i = 0;
        for (HashMap<String, Object> type: info){
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.KitchenTypesColumns.INDEXID,
                    (type.get(DatabaseContract.KitchenTypesColumns.INDEXID) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.KitchenTypesColumns.INDEXID).toString())));
            values.put(DatabaseContract.KitchenTypesColumns.CAPTION,
                    (type.get(DatabaseContract.DishesColumns.CAPTION) == null ? "" : type.get(DatabaseContract.DishesColumns.CAPTION).toString()));
            values.put(DatabaseContract.KitchenTypesColumns.SORT,
                    (type.get(DatabaseContract.KitchenTypesColumns.SORT) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.KitchenTypesColumns.ACTIVE).toString())));
            values.put(DatabaseContract.KitchenTypesColumns.ACTIVE,
                    (type.get(DatabaseContract.KitchenTypesColumns.ACTIVE) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.KitchenTypesColumns.ACTIVE).toString())));
            values.put(DatabaseContract.KitchenTypesColumns.PRIMEID, primeNumber.get(i));
            //Log.d(type.get(DatabaseContract.DishesColumns.CAPTION).toString(), String.valueOf(primeNumber.get(i)));
            ++i;
            long newRowId = db.insert(DatabaseContract.KitchenTypesColumns.TABLE_NAME, null, values);
        }
    }

    public int getKitchenNumber(Context context, String req){
        ArrayList<Integer> kitchens = stringToArray(req);
        //Log.d(req, kitchens.toString());
        int result = 1;
        openbd(context);
        for (int kit: kitchens){
            String[] projection = {
                    DatabaseContract.KitchenTypesColumns.INDEXID,
                    DatabaseContract.KitchenTypesColumns.PRIMEID,
                    DatabaseContract.KitchenTypesColumns.ACTIVE
            };
            Cursor cursor = db.query(
                    DatabaseContract.KitchenTypesColumns.TABLE_NAME,   // таблица
                    projection,            // столбцы
                    DatabaseContract.DishesColumns.ACTIVE + " = 1" +
                            " AND " + DatabaseContract.DishesColumns.INDEXID + " = " + kit,                  // столбцы для условия WHERE
                    null,                  // значения для условия WHERE
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);
            try {
                // Узнаем индекс каждого столбца
                int primeColumnIndex = cursor.getColumnIndex(DatabaseContract.KitchenTypesColumns.PRIMEID);
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
        closebd();
        //Log.d(req, String.valueOf(result));
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

    public String getKitchens(int num, Context context){
        ArrayList<Integer> prime = simply(num);
        String result = "";
        openbd(context);
        for (int curPrime: prime){
            String[] projection = {
                    DatabaseContract.KitchenTypesColumns.CAPTION,
                    DatabaseContract.KitchenTypesColumns.PRIMEID,
                    DatabaseContract.KitchenTypesColumns.ACTIVE
            };
            Cursor cursor = db.query(
                    DatabaseContract.KitchenTypesColumns.TABLE_NAME,   // таблица
                    projection,            // столбцы
                    DatabaseContract.KitchenTypesColumns.ACTIVE + " = 1" +
                            " AND " + DatabaseContract.KitchenTypesColumns.PRIMEID + " = " + curPrime,                  // столбцы для условия WHERE
                    null,                  // значения для условия WHERE
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);
            try {
                // Узнаем индекс каждого столбца
                int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.KitchenTypesColumns.CAPTION);
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
        closebd();
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

    public int getKitchenNumberByNames(Context context, ArrayList<String> names){
        int result = 1;
        openbd(context);
        for (String name: names){
            String[] projection = {
                    DatabaseContract.KitchenTypesColumns.CAPTION,
                    DatabaseContract.KitchenTypesColumns.PRIMEID,
                    DatabaseContract.KitchenTypesColumns.ACTIVE
            };
            Cursor cursor = db.query(
                    DatabaseContract.KitchenTypesColumns.TABLE_NAME,   // таблица
                    projection,            // столбцы
                    DatabaseContract.KitchenTypesColumns.ACTIVE + " = 1" +
                            " AND " + DatabaseContract.KitchenTypesColumns.CAPTION + " = " + name,                  // столбцы для условия WHERE
                    null,                  // значения для условия WHERE
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);
            try {
                // Узнаем индекс каждого столбца
                int primeColumnIndex = cursor.getColumnIndex(DatabaseContract.KitchenTypesColumns.PRIMEID);
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
        closebd();
        return result;
    }
}
