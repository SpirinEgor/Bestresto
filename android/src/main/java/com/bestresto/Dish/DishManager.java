package com.bestresto.Dish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.FloatProperty;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.data.DatabaseContract;
import com.bestresto.data.dbHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by egor on 30.05.17.
 * make bd, make adapters, get data
 */

public class DishManager {

    private SQLiteDatabase db;

    public void openbd(Context context){
        dbHelper dbh = new dbHelper(context);
        db = dbh.getWritableDatabase();
        dbh.createDish(db);
    }

    public void closebd(){
        db.close();
    }

    public void cleantable(){
        db.delete(DatabaseContract.DishesColumns.TABLE_NAME, null, null);
    }

    public void addDB(HashMap<String, Object> dish, Context context){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DishesColumns.INDEXID,
                (dish.get(DatabaseContract.DishesColumns.INDEXID) == null ? 0 : Integer.parseInt(dish.get(DatabaseContract.DishesColumns.INDEXID).toString())));
        values.put(DatabaseContract.DishesColumns.PARENT_ID,
                (dish.get(DatabaseContract.DishesColumns.PARENT_ID) == null ? "" : dish.get(DatabaseContract.DishesColumns.PARENT_ID).toString()));
        values.put(DatabaseContract.DishesColumns.ACTIVE,
                (dish.get(DatabaseContract.DishesColumns.ACTIVE) == null ? 0 :
                        dish.get(DatabaseContract.DishesColumns.ACTIVE).toString().equals("yes") ? 1: 0));
        values.put(DatabaseContract.DishesColumns.CAPTION,
                (dish.get(DatabaseContract.DishesColumns.CAPTION) == null ? "" : dish.get(DatabaseContract.DishesColumns.CAPTION).toString()));
        values.put(DatabaseContract.DishesColumns.KITCHEN,
                (dish.get(DatabaseContract.DishesColumns.KITCHEN) == null ? -1 :
                        new KitchenTypesManager().getKitchenNumber(context, dish.get(DatabaseContract.DishesColumns.KITCHEN).toString())));
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
        //Log.d("add", String.valueOf(newRowId));
    }

    public ArrayAdapter getFilteredAdapter(Context context, String dishtitle){
        return new CustomAdapter(context, make_data_filtered(context, dishtitle));
    }

    private ArrayList<HashMap<String,Object>> make_data_filtered(Context context, String dishtitle) {
        openbd(context);
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        String[] projection = {
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE
        };
        Cursor cursor = db.query(
                DatabaseContract.DishesColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                DatabaseContract.DishesColumns.ACTIVE + " = 1" +
                " AND " + DatabaseContract.DishesColumns.CAPTION + " = \"" + dishtitle + "\"",                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        Log.e("tag", DatabaseContract.DishesColumns.ACTIVE + " = 1" +
                " AND " + DatabaseContract.DishesColumns.CAPTION + " = \"" + dishtitle + "\"");

        try {
            // Узнаем индекс каждого столбца

            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.CAPTION);
            int priceColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PRICE);
            int pictureColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PICTURE);

            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                String currentPicture = cursor.getString(pictureColumnIndex);

                HashMap<String, Object> cur = new HashMap<>();
                cur.put(DatabaseContract.DishesColumns.CAPTION, currentCaption);
                cur.put(DatabaseContract.DishesColumns.PRICE, currentPrice);
                cur.put(DatabaseContract.DishesColumns.PICTURE, currentPicture);
                data.add(cur);
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        closebd();
        return data;
    }

    ArrayAdapter getAdapter(Context context){
        return new CustomAdapter(context, make_data_all(context));
    }

    public ArrayList<HashMap<String, Object>> make_data_all(Context context){
        openbd(context);

        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        String[] projection = {
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE
        };
        Cursor cursor = db.query(
                DatabaseContract.DishesColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                DatabaseContract.DishesColumns.ACTIVE + " = 1",                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        Log.d("size", String.valueOf(cursor.getCount()));
        try {
            // Узнаем индекс каждого столбца

            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.CAPTION);
            int priceColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PRICE);
            int pictureColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PICTURE);

            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                String currentPicture = cursor.getString(pictureColumnIndex);

                HashMap<String, Object> cur = new HashMap<>();
                cur.put(DatabaseContract.DishesColumns.CAPTION, currentCaption);
                cur.put(DatabaseContract.DishesColumns.PRICE, currentPrice);
                cur.put(DatabaseContract.DishesColumns.PICTURE, currentPicture);
                data.add(cur);
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        closebd();
        return data;
    }

    public ArrayList<HashMap<String, Object>> make_data_first(Context context){
        openbd(context);
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        String[] projection = {
                DatabaseContract.DishesColumns.FIRST_PAGE,
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE
        };
        String selection = DatabaseContract.DishesColumns.FIRST_PAGE + " = 1 AND " + DatabaseContract.DishesColumns.ACTIVE + " = 1";
        Cursor cursor = db.query(
                DatabaseContract.DishesColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                selection,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        try {
            // Узнаем индекс каждого столбца

            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.CAPTION);
            int priceColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PRICE);
            int pictureColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PICTURE);

            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                String currentPicture = cursor.getString(pictureColumnIndex);

                HashMap<String, Object> cur = new HashMap<>();
                cur.put(DatabaseContract.DishesColumns.CAPTION, currentCaption);
                cur.put(DatabaseContract.DishesColumns.PRICE, currentPrice);
                cur.put(DatabaseContract.DishesColumns.PICTURE, currentPicture);
                data.add(cur);
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        closebd();
        return data;
    }

    HashMap<String, Object> make_data_about(Context context, String caption){
        HashMap<String, Object> info = new HashMap<>();
        openbd(context);

        String[] projection = {
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE,
                DatabaseContract.DishesColumns.REITING,
                DatabaseContract.DishesColumns.DESC,
                DatabaseContract.DishesColumns.GARANT,
                DatabaseContract.DishesColumns.PARENT_ID
        };
        String selection = DatabaseContract.DishesColumns.CAPTION + " = \"" + check(caption) + "\"";
        Cursor cursor = db.query(
                DatabaseContract.DishesColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                selection,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,
                null);
        try {
            // Узнаем индекс каждого столбца

            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.CAPTION);
            int priceColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PRICE);
            int pictureColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.PICTURE);
            int reitingColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.REITING);
            int descColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.DESC);
            int garantColumnIndex = cursor.getColumnIndex(DatabaseContract.DishesColumns.GARANT);
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                String currentPicture = cursor.getString(pictureColumnIndex);
                float currentReiting = cursor.getFloat(reitingColumnIndex);
                String currentDesc = cursor.getString(descColumnIndex);
                String currentGarant = cursor.getString(garantColumnIndex);

                info.put(DatabaseContract.DishesColumns.CAPTION, currentCaption);
                info.put(DatabaseContract.DishesColumns.PRICE, currentPrice);
                info.put(DatabaseContract.DishesColumns.PICTURE, currentPicture);
                info.put(DatabaseContract.DishesColumns.DESC, currentDesc);
                info.put(DatabaseContract.DishesColumns.GARANT, currentGarant);
                info.put(DatabaseContract.DishesColumns.REITING, currentReiting);
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        closebd();
        return info;
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

    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {

        private Context context;
        private CustomAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
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

