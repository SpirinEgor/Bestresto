package com.bestresto.Dish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestresto.AddDbThread;
import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;
import com.bestresto.data.DatabaseContract;
import com.bestresto.data.dbHelper;
import com.squareup.picasso.Picasso;
import com.bestresto.ManagerInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by egor on 30.05.17.
 * make bd, make adapters, get data
 */

public class DishManager implements ManagerInterface {

    private SQLiteDatabase db;

    public void openDb(Context context){
        dbHelper dbh = new dbHelper(context);
        db = dbh.getWritableDatabase();
    }

    public void closeDb(){
        db.close();
    }

    public void cleanTable(){
        dbHelper.createDish(db);
        db.delete(DatabaseContract.DishesColumns.TABLE_NAME, null, null);
    }

    public void setDb(SQLiteDatabase db){
        this.db = db;
    }

    public void addAllDb(final ArrayList<HashMap<String, Object>> data){
        this.cleanTable();
        int itemPerThread = 250;
        int cnt = data.size() / itemPerThread + ((data.size() % itemPerThread > 0) ? 1: 0);
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < cnt; ++i){
            final ArrayList<HashMap<String, Object>> curData = new ArrayList<>();
            for (int j = i * itemPerThread; j < Math.min((i + 1) * itemPerThread, data.size()); ++j) {
                curData.add(data.get(j));
            }
            DishManager curManager = new DishManager();
            curManager.setDb(this.db);
            threads.add(new AddDbThread(curData, curManager));
        }
        for (Thread thread: threads){
            thread.start();
        }
        for (Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized public void addDb(HashMap<String, Object> dish){
        ContentValues values = new ContentValues();
        KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
        kitchenTypesManager.setDb(this.db);
        RestaurantTypesManager restaurantTypesManager = new RestaurantTypesManager();
        restaurantTypesManager.setDb(this.db);
        values.put(DatabaseContract.DishesColumns.INDEXID,
                (dish.get(DatabaseContract.DishesColumns.INDEXID) == null ? 0 : Integer.parseInt(dish.get(DatabaseContract.DishesColumns.INDEXID).toString())));
        values.put(DatabaseContract.DishesColumns.PARENT_ID,
                (dish.get(DatabaseContract.DishesColumns.PARENT_ID) == null ? -1 :
                        restaurantTypesManager.getRestaurantNumber(dish.get(DatabaseContract.DishesColumns.PARENT_ID).toString())));
        values.put(DatabaseContract.DishesColumns.ACTIVE,
                (dish.get(DatabaseContract.DishesColumns.ACTIVE) == null ? 0 :
                        dish.get(DatabaseContract.DishesColumns.ACTIVE).toString().equals("yes") ? 1: 0));
        values.put(DatabaseContract.DishesColumns.CAPTION,
                (dish.get(DatabaseContract.DishesColumns.CAPTION) == null ? "" : dish.get(DatabaseContract.DishesColumns.CAPTION).toString()));
        values.put(DatabaseContract.DishesColumns.KITCHEN,
                (dish.get(DatabaseContract.DishesColumns.KITCHEN) == null ? -1 :
                        kitchenTypesManager.getKitchenNumber(dish.get(DatabaseContract.DishesColumns.KITCHEN).toString())));
        values.put(DatabaseContract.DishesColumns.PRICE,
                (dish.get(DatabaseContract.DishesColumns.PRICE) == null ? 0 : Integer.parseInt(dish.get(DatabaseContract.DishesColumns.PRICE).toString())));
        values.put(DatabaseContract.DishesColumns.DESC,
                (dish.get(DatabaseContract.DishesColumns.DESC) == null ? "" : dish.get(DatabaseContract.DishesColumns.DESC).toString()));
        values.put(DatabaseContract.DishesColumns.FIRST_PAGE,
                (dish.get(DatabaseContract.DishesColumns.FIRST_PAGE) == null ? 0 :
                        dish.get(DatabaseContract.DishesColumns.FIRST_PAGE).toString().equals("yes") ? 1: 0));
        values.put(DatabaseContract.DishesColumns.PICTURE,
                (dish.get(DatabaseContract.DishesColumns.PICTURE) == null ? "" : dish.get(DatabaseContract.DishesColumns.PICTURE).toString()));
        values.put(DatabaseContract.DishesColumns.DOPPIC,
                (dish.get(DatabaseContract.DishesColumns.DOPPIC) == null ? "" : dish.get(DatabaseContract.DishesColumns.DOPPIC).toString()));
        values.put(DatabaseContract.DishesColumns.SORT,
                (dish.get(DatabaseContract.DishesColumns.SORT) == null ? 0 : Integer.parseInt(dish.get(DatabaseContract.DishesColumns.SORT).toString())));
        values.put(DatabaseContract.DishesColumns.REITING,
                (dish.get(DatabaseContract.DishesColumns.REITING) == null || dish.get(DatabaseContract.DishesColumns.REITING).equals("")
                        ? 0.0 : Float.parseFloat(dish.get(DatabaseContract.DishesColumns.REITING).toString())));
        values.put(DatabaseContract.DishesColumns.GARANT,
                (dish.get(DatabaseContract.DishesColumns.GARANT) == null ? "" : dish.get(DatabaseContract.DishesColumns.GARANT).toString()));
        values.put(DatabaseContract.DishesColumns.SEARCHTAGS,
                (dish.get(DatabaseContract.DishesColumns.SEARCHTAGS) == null ? "" : dish.get(DatabaseContract.DishesColumns.SEARCHTAGS).toString()));
        values.put(DatabaseContract.DishesColumns.CREATEDATE,
                (dish.get(DatabaseContract.DishesColumns.CREATEDATE) == null ? "" : dish.get(DatabaseContract.DishesColumns.CREATEDATE).toString()));
        long newRowId = db.insert(DatabaseContract.DishesColumns.TABLE_NAME, null, values);
    }

    public ArrayList<HashMap<String, Object>> makeData
            (HashMap<String, String> whenConditions, HashMap<String, String> orderByConditions, String[] columns){
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        StringBuilder when = new StringBuilder();
        for (Map.Entry<String, String> condition: whenConditions.entrySet()){
            String column = condition.getKey();
            String value = check(condition.getValue());
            when.append(column).append(" = \"").append(value).append("\" AND ");
        }
        if (!when.toString().equals("")){
            when.delete(when.length() - 5, when.length());
        }
        StringBuilder order = new StringBuilder();
        for (Map.Entry<String, String> condition: orderByConditions.entrySet()){
            String column = condition.getKey();
            String value = check(condition.getValue());
            order.append(column).append(" ").append(value).append(", ");
        }
        if (!order.toString().equals("")){
            order.delete(order.length() - 2, order.length());
        }
        Cursor cursor = db.query(
                DatabaseContract.DishesColumns.TABLE_NAME,
                columns,
                (when.toString().equals("") ? null : when.toString()),
                null,
                null,
                null,
                (order.toString().equals("") ? null : order.toString())
        );
        try{
            int[] index = new int[columns.length];
            for (int i = 0; i < columns.length; ++i) {
                index[i] = cursor.getColumnIndex(columns[i]);
            }
            while (cursor.moveToNext()){
                HashMap<String, Object> cur = new HashMap<>();
                for (int i = 0; i < columns.length; ++i){
                    cur.put(columns[i], getValue(cursor, columns[i], index[i]));
                }
                data.add(cur);
            }
        }
        finally {
            cursor.close();
        }
        return  data;
    }

    ArrayAdapter getAdapterWithData(Context context,
             HashMap<String, String> whenConditions, HashMap<String, String> orderByConditions, String[] columns){
        return new CustomAdapter(context, makeData(whenConditions, orderByConditions, columns));
    }

    private String check(String current){
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

    private Object getValue(Cursor cursor, String column, int index){
        switch (column) {
            case DatabaseContract.DishesColumns.INDEXID:
            case DatabaseContract.DishesColumns.PRICE:
            case DatabaseContract.DishesColumns.ACTIVE:
            case DatabaseContract.DishesColumns.FIRST_PAGE:
            case DatabaseContract.DishesColumns.SORT:
                return cursor.getInt(index);
            case DatabaseContract.DishesColumns.REITING:
                return cursor.getFloat(index);
            case DatabaseContract.DishesColumns.KITCHEN:
                KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
                kitchenTypesManager.setDb(this.db);
                return kitchenTypesManager.getKitchens(cursor.getInt(index));
            case DatabaseContract.DishesColumns.PARENT_ID:
                RestaurantTypesManager restaurantTypesManager = new RestaurantTypesManager();
                restaurantTypesManager.setDb(this.db);
                return restaurantTypesManager.getRestaurants(cursor.getInt(index));
            default:
                return cursor.getString(index);
        }
    }

    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {

        private Context context;
        CustomAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
            super(context, R.layout.dish_listitem, data);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            HashMap<String, Object> dish = getItem(position);

            if (convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.dish_listitem, null);

            TextView caption = (TextView)convertView.findViewById(R.id.dish_caption);
            TextView price = (TextView)convertView.findViewById(R.id.dish_price);
            ImageView picture = (ImageView)convertView.findViewById(R.id.dish_picture);

            caption.setText( dish.get(DatabaseContract.DishesColumns.CAPTION).toString() );
            price.setText( dish.get(DatabaseContract.DishesColumns.PRICE).toString() );

            String url = "http://www.bestresto.ru/" + dish.get(DatabaseContract.DishesColumns.PICTURE);
            Picasso.with(context).load(url).into(picture);

            return convertView;
        }

    }

}

