package com.bestresto.Types;

import android.content.ContentValues;
import android.database.Cursor;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DatabaseWork;
import com.bestresto.Database.QueryConditions;
import com.bestresto.ManagerInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantTypesManager implements ManagerInterface {

    private int currentPrimeNumber = 0;

    @Override
    public void addAllDb(ArrayList<HashMap<String, Object>> data){
        DatabaseWork.cleanTable(DatabaseContract.RestaurantTypesColumns.TABLE_NAME);
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

        DatabaseWork.insertContentValue(DatabaseContract.RestaurantTypesColumns.TABLE_NAME, values);
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

    public int getRestaurantNumber(String req){
        ArrayList<Integer> kitchens = stringToArray(req);

        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setTableName(DatabaseContract.RestaurantTypesColumns.TABLE_NAME);
        queryConditions.setColumns(new String[]{ DatabaseContract.RestaurantTypesColumns.PRIMEID });
        StringBuilder whereCondition = new StringBuilder(DatabaseContract.RestaurantTypesColumns.ACTIVE + " = 1");
        if (kitchens.size() > 0) {
            whereCondition.append(" AND (");
            for (int kitchen: kitchens) {
                whereCondition.append("(").append(DatabaseContract.DishesColumns.INDEXID).append(" = ").append(kitchen).append(")");
                if (kitchen != kitchens.get(kitchens.size() - 1)) {
                    whereCondition.append(" OR ");
                }
            }
            whereCondition.append(")");
        }
        else return -1;
        queryConditions.setWhereCondition(whereCondition.toString());
        ArrayList<HashMap<String, Object>> data = DatabaseWork.makeData(queryConditions);

        int result = 1;
        for (HashMap<String, Object> singleType: data) {
            result *= Integer.parseInt(singleType.get(DatabaseContract.RestaurantTypesColumns.PRIMEID).toString());
        }
        return result;
    }

    public String getRestaurants(int num){
        ArrayList<Integer> primes = prime(num);

        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setTableName(DatabaseContract.RestaurantTypesColumns.TABLE_NAME);
        queryConditions.setColumns(new String[]{ DatabaseContract.RestaurantTypesColumns.CAPTION });
        StringBuilder whereCondition = new StringBuilder(DatabaseContract.RestaurantTypesColumns.ACTIVE + " = 1");
        if (primes.size() > 0) {
            whereCondition.append(" AND (");
            for (int prime: primes) {
                whereCondition.append("(").append(DatabaseContract.RestaurantTypesColumns.PRIMEID).append(" = ").
                        append(prime).append(")");
                if (prime != primes.get(primes.size() - 1)) {
                    whereCondition.append(" OR ");
                }
            }
            whereCondition.append(")");
        }
        queryConditions.setWhereCondition(whereCondition.toString());
        ArrayList<HashMap<String, Object>> data = DatabaseWork.makeData(queryConditions);

        if (data.size() == 0) { return ""; }
        StringBuilder result = new StringBuilder();
        HashMap<String, Object> last = data.get(data.size() - 1);
        for (HashMap<String, Object> singleType: data) {
            result.append(singleType.get(DatabaseContract.RestaurantTypesColumns.CAPTION).toString());
            if (!singleType.equals(last)) {
                result.append(", ");
            }
        }
        return result.toString();
    }

}
