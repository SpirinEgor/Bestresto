package com.bestresto.Restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestresto.AddDbThread;
import com.bestresto.Dish.DishManager;
import com.bestresto.ManagerInterface;
import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;
import com.bestresto.data.DatabaseContract;
import com.bestresto.data.dbHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantManager implements ManagerInterface {

    private SQLiteDatabase db;

    public void openDb(Context context){
        dbHelper dbh = new dbHelper(context);
        db = dbh.getWritableDatabase();
    }

    public void closeDb(){
        db.close();
    }

    public void cleanTable(){
        dbHelper.createRestaurant(db);
        db.delete(DatabaseContract.RestaurantsColumns.TABLE_NAME, null, null);
    }

    public void setDb(SQLiteDatabase db){
        this.db = db;
    }

    public void addAllDb(ArrayList<HashMap<String, Object>> data){
        this.cleanTable();
        int itemPerThread = 250;
        int cnt = data.size() / itemPerThread + ((data.size() % itemPerThread > 0) ? 1: 0);
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < cnt; ++i){
            final ArrayList<HashMap<String, Object>> curData = new ArrayList<>();
            for (int j = i * itemPerThread; j < Math.min((i + 1) * itemPerThread, data.size()); ++j){
                curData.add(data.get(j));
            }
            RestaurantManager curManager = new RestaurantManager();
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

    synchronized public void addDb(HashMap<String, Object> rest){
        ContentValues values = new ContentValues();
        KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
        kitchenTypesManager.setDb(this.db);
        values.put(DatabaseContract.RestaurantsColumns.CAPTION,
                (rest.get(DatabaseContract.RestaurantsColumns.CAPTION) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.CAPTION).toString()));

        values.put(DatabaseContract.RestaurantsColumns.URL,
                (rest.get(DatabaseContract.RestaurantsColumns.URL) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.URL).toString()));

        values.put(DatabaseContract.RestaurantsColumns.LOGO,
                (rest.get(DatabaseContract.RestaurantsColumns.LOGO) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.LOGO).toString()));

        values.put(DatabaseContract.RestaurantsColumns.REITING,
                (rest.get(DatabaseContract.RestaurantsColumns.REITING) == null ?
                        0.0 : Float.parseFloat(rest.get(DatabaseContract.RestaurantsColumns.REITING).toString())));

        values.put(DatabaseContract.RestaurantsColumns.TIP,
                (rest.get(DatabaseContract.RestaurantsColumns.TIP) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.TIP).toString()));

        values.put(DatabaseContract.RestaurantsColumns.MIN_PRICE,
                (rest.get(DatabaseContract.RestaurantsColumns.MIN_PRICE) == null ?
                        0 : Integer.parseInt(rest.get(DatabaseContract.RestaurantsColumns.MIN_PRICE).toString())));

        values.put(DatabaseContract.RestaurantsColumns.MAX_PRICE,
                (rest.get(DatabaseContract.RestaurantsColumns.MAX_PRICE) == null ?
                        0 : Integer.parseInt(rest.get(DatabaseContract.RestaurantsColumns.MAX_PRICE).toString())));

        values.put(DatabaseContract.RestaurantsColumns.KITCHEN,
                (rest.get(DatabaseContract.RestaurantsColumns.KITCHEN) == null ?
                        0 : kitchenTypesManager.getKitchenNumber(rest.get(DatabaseContract.RestaurantsColumns.KITCHEN).toString())));

        values.put(DatabaseContract.RestaurantsColumns.ADDRESS,
                (rest.get(DatabaseContract.RestaurantsColumns.ADDRESS) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.ADDRESS).toString()));

        long newRowId = db.insert(DatabaseContract.RestaurantsColumns.TABLE_NAME, null, values);
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
                DatabaseContract.RestaurantsColumns.TABLE_NAME,
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
        return new RestaurantManager.CustomAdapter(context, makeData(whenConditions, orderByConditions, columns));
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
            case DatabaseContract.RestaurantsColumns.MIN_PRICE:
            case DatabaseContract.RestaurantsColumns.MAX_PRICE:
                return cursor.getInt(index);
            case DatabaseContract.RestaurantsColumns.REITING:
                return cursor.getFloat(index);
            case DatabaseContract.RestaurantsColumns.KITCHEN:
                KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
                kitchenTypesManager.setDb(this.db);
                return kitchenTypesManager.getKitchens(cursor.getInt(index));
            default:
                return cursor.getString(index);
        }
    }

    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {

        private Context context;
        private CustomAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
            super(context, R.layout.rest_listitem, data);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            HashMap<String, Object> rest = getItem(position);

            if (convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.rest_listitem, null);

            TextView caption = (TextView)convertView.findViewById(R.id.rest_caption);
            caption.setText( rest.get(DatabaseContract.RestaurantsColumns.CAPTION).toString() );

            TextView kitchen = (TextView)convertView.findViewById(R.id.rest_kitchen);
            kitchen.setText( rest.get(DatabaseContract.RestaurantsColumns.KITCHEN).toString() );

            TextView address = (TextView)convertView.findViewById(R.id.rest_address);
            address.setText( rest.get(DatabaseContract.RestaurantsColumns.ADDRESS).toString() );

            ImageView logo = (ImageView)convertView.findViewById(R.id.rest_logo);
            String url = "http://www.bestresto.ru/" + rest.get(DatabaseContract.RestaurantsColumns.LOGO);
            Picasso.with(context).load(url).into(logo);

            return convertView;
        }
    }
}
