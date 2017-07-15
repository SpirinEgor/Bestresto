package com.bestresto.Types;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bestresto.data.DatabaseContract;
import com.bestresto.data.dbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitchenTypesManager {

    private SQLiteDatabase db;

    public void openbd(Context context){
        dbHelper dbh = new dbHelper(context);
        db = dbh.getWritableDatabase();
        dbh.createKitchenTypes(db);
    }

    public void closebd(){
        db.close();
    }

    public void cleantable(){
        db.delete(DatabaseContract.KitchenTypesColumns.TABLE_NAME, null, null);
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
            ++i;
            long newRowId = db.insert(DatabaseContract.KitchenTypesColumns.TABLE_NAME, null, values);
        }
    }

    public int getKitchenNumber(Context context, String req){
        ArrayList<Integer> kitchens = stringToArray(req);
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
        String[] spl = str.split("|");
        for (String aSpl : spl) {
            if (!(aSpl.equals(",") || aSpl.equals("") || aSpl.equals("|")))
                result.add(Integer.parseInt(aSpl));
        }
        return result;
    }

}
