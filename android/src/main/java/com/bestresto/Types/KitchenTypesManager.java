package com.bestresto.Types;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DbHelper;
import com.bestresto.Database.QueryConditions;
import com.bestresto.ManagerInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class KitchenTypesManager implements ManagerInterface {

    private SQLiteDatabase db;

    @Override
    public void cleanTable(){
        DbHelper.createKitchenTypes(db);
        db.delete(DatabaseContract.KitchenTypesColumns.TABLE_NAME, null, null);
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
        values.put(DatabaseContract.KitchenTypesColumns.INDEXID,
                (type.get(DatabaseContract.KitchenTypesColumns.INDEXID) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.KitchenTypesColumns.INDEXID).toString())));
        values.put(DatabaseContract.KitchenTypesColumns.CAPTION,
                (type.get(DatabaseContract.DishesColumns.CAPTION) == null ? "" : type.get(DatabaseContract.DishesColumns.CAPTION).toString()));
        values.put(DatabaseContract.KitchenTypesColumns.SORT,
                (type.get(DatabaseContract.KitchenTypesColumns.SORT) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.KitchenTypesColumns.ACTIVE).toString())));
        values.put(DatabaseContract.KitchenTypesColumns.ACTIVE,
                (type.get(DatabaseContract.KitchenTypesColumns.ACTIVE) == null ? 0 : Integer.parseInt(type.get(DatabaseContract.KitchenTypesColumns.ACTIVE).toString())));
        values.put(DatabaseContract.KitchenTypesColumns.PRIMEID, currentPrimeNumber);
        //Log.d(type.get(DatabaseContract.DishesColumns.CAPTION).toString(), String.valueOf(primeNumber.get(i)));
        long newRowId = db.insert(DatabaseContract.KitchenTypesColumns.TABLE_NAME, null, values);
    }

    @Override
    public Object getValue(Cursor cursor, String column, int index) {
        switch (column) {
            case DatabaseContract.KitchenTypesColumns.CAPTION:
                return cursor.getString(index);
            default:
                return cursor.getInt(index);
        }
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

    private ArrayList<Integer> prime(int num){
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

    public int getKitchenNumber(String req){
        ArrayList<Integer> kitchensId = stringToArray(req);
        int result = 1;

        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setTableName(DatabaseContract.KitchenTypesColumns.TABLE_NAME);
        queryConditions.setColumns(new String[]{
                DatabaseContract.KitchenTypesColumns.PRIMEID
        });
        StringBuilder whenCondition = new StringBuilder(DatabaseContract.KitchenTypesColumns.ACTIVE + " = 1");
        if (kitchensId.size() != 0) {
            whenCondition.append(" AND (");
            for (int curKitchenId: kitchensId) {
                whenCondition.append("(").append(DatabaseContract.KitchenTypesColumns.INDEXID).append(" = ").
                        append(curKitchenId).append(")");
                if (curKitchenId != kitchensId.get(kitchensId.size() - 1)) {
                    whenCondition.append(" OR ");
                }
            }
            whenCondition.append(")");
        }
        queryConditions.setWhenCondition(whenCondition.toString());
        
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

    public String getKitchens(int num){
        ArrayList<Integer> prime = prime(num);
        String result = "";
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
        if (!result.equals(""))
            result = result.substring(0, result.length() - 2);
        return result;
    }

    public ArrayList<Integer> getKitchenNumbersByNames(ArrayList<String> names){
        ArrayList<Integer> result = new ArrayList<>();
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
                            " AND " + DatabaseContract.KitchenTypesColumns.CAPTION + " = \"" + name + "\"",                  // столбцы для условия WHERE
                    null,                  // значения для условия WHERE
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);
            try {
                // Узнаем индекс каждого столбца
                int primeColumnIndex = cursor.getColumnIndex(DatabaseContract.KitchenTypesColumns.PRIMEID);
                while (cursor.moveToNext()) {
                    // Используем индекс для получения строки или числа
                    Integer currentPrime = cursor.getInt(primeColumnIndex);
                    result.add(currentPrime);
                }
            }
            finally {
                cursor.close();
            }
        }
        return result;
    }


}
