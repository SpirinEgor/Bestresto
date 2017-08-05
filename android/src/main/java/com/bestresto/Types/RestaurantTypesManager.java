package com.bestresto.Types;

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
        StringBuilder SQLScript = new StringBuilder("INSERT INTO " + DatabaseContract.RestaurantTypesColumns.TABLE_NAME + " (" +
                DatabaseContract.RestaurantTypesColumns.INDEXID + ", " +
                DatabaseContract.RestaurantTypesColumns.CAPTION + ", " +
                DatabaseContract.RestaurantTypesColumns.SORT + ", " +
                DatabaseContract.RestaurantTypesColumns.ACTIVE + ", " +
                DatabaseContract.RestaurantTypesColumns.PRIMEID + ") VALUES ");
        for (HashMap<String, Object> type: data) {
            SQLScript.append(createStringForSQLScript(type));
            if (type.equals(data.get(data.size() - 1))) {
                SQLScript.append(";");
            } else {
                SQLScript.append(", ");
            }
        }
        DatabaseWork.execSQL(SQLScript.toString());
    }

    @Override
    public String createStringForSQLScript(HashMap<String, Object> type) {
        String result = "(";
        result += (type.get(DatabaseContract.RestaurantTypesColumns.INDEXID) == null ? 0 :
                Integer.parseInt(type.get(DatabaseContract.RestaurantTypesColumns.INDEXID).toString())) + ", ";
        result += DatabaseWork.prepare(type.get(DatabaseContract.RestaurantTypesColumns.CAPTION) == null ? "" :
                type.get(DatabaseContract.RestaurantTypesColumns.CAPTION).toString()) + ", ";
        result += (type.get(DatabaseContract.RestaurantTypesColumns.SORT) == null ? 0 :
                Integer.parseInt(type.get(DatabaseContract.RestaurantTypesColumns.SORT).toString())) + ", ";
        result += (type.get(DatabaseContract.RestaurantTypesColumns.ACTIVE) == null ? 0 :
                Integer.parseInt(type.get(DatabaseContract.RestaurantTypesColumns.ACTIVE).toString())) + ", ";
        result += currentPrimeNumber;
        result += ")";
        return result;
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
