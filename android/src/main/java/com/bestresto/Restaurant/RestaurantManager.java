package com.bestresto.Restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestresto.R;
import com.bestresto.data.DatabaseContract;
import com.bestresto.data.dbHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantManager {

    private dbHelper dbh;
    private Context context;
    private SQLiteDatabase db;

    public void openbd(Context context){
        this.context = context;
        dbh = new dbHelper(context);
        db = dbh.getWritableDatabase();
        dbh.createRestaurant(db);
    }

    public void closebd(){
        db.close();
    }

    public void cleantable(){
        db.delete(DatabaseContract.RestaurantsColumns.TABLE_NAME, null, null);
    }

    public void addDB(HashMap<String, Object> rest){
        ContentValues values = new ContentValues();
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
                        "" : rest.get(DatabaseContract.RestaurantsColumns.REITING).toString()));

        values.put(DatabaseContract.RestaurantsColumns.TIP,
                (rest.get(DatabaseContract.RestaurantsColumns.TIP) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.TIP).toString()));

        values.put(DatabaseContract.RestaurantsColumns.MIN_PRICE,
                (rest.get(DatabaseContract.RestaurantsColumns.MIN_PRICE) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.MIN_PRICE).toString()));

        values.put(DatabaseContract.RestaurantsColumns.MAX_PRICE,
                (rest.get(DatabaseContract.RestaurantsColumns.MAX_PRICE) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.MAX_PRICE).toString()));

        values.put(DatabaseContract.RestaurantsColumns.KITCHEN,
                (rest.get(DatabaseContract.RestaurantsColumns.KITCHEN) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.KITCHEN).toString()));

        values.put(DatabaseContract.RestaurantsColumns.ADDRESS,
                (rest.get(DatabaseContract.RestaurantsColumns.ADDRESS) == null ?
                        "" : rest.get(DatabaseContract.RestaurantsColumns.ADDRESS).toString()));

        long newRowId = db.insert(DatabaseContract.RestaurantsColumns.TABLE_NAME, null, values);
        //Log.d("add", rest.get(DatabaseContract.RestaurantsColumns.CAPTION).toString());
    }

    public ArrayAdapter getAdapter(Context context){
        return new CustomAdapter(context, make_data_all(context));
    }

    public ArrayList<HashMap<String, Object>> make_data_all(Context context){
        openbd(context);

        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        String[] projection = {
                DatabaseContract.RestaurantsColumns.CAPTION,
                DatabaseContract.RestaurantsColumns.LOGO,
                DatabaseContract.RestaurantsColumns.REITING,
                DatabaseContract.RestaurantsColumns.KITCHEN,
                DatabaseContract.RestaurantsColumns.ADDRESS,
        };

        Cursor cursor = db.query(
                DatabaseContract.RestaurantsColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                null, // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        Log.d("size", String.valueOf(cursor.getCount()));
        try {
            // Узнаем индекс каждого столбца
            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.CAPTION);
            int logoColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.LOGO);
            int reitingColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.REITING);
            int kitchenColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.KITCHEN);
            int addressColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.ADDRESS);

            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);
                String currentLogo = cursor.getString(logoColumnIndex);
                String currentReiting = cursor.getString(reitingColumnIndex);
                String currentKitchen = cursor.getString(kitchenColumnIndex);
                String currentAddress = cursor.getString(addressColumnIndex);

                HashMap<String, Object> cur = new HashMap<>();

                cur.put(DatabaseContract.RestaurantsColumns.CAPTION, currentCaption);
                cur.put(DatabaseContract.RestaurantsColumns.LOGO, currentLogo);
                cur.put(DatabaseContract.RestaurantsColumns.REITING, currentReiting);
                cur.put(DatabaseContract.RestaurantsColumns.KITCHEN, currentKitchen);
                cur.put(DatabaseContract.RestaurantsColumns.ADDRESS, currentAddress);

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

    public HashMap<String, Object> make_data_about(Context context, String caption){
        HashMap<String, Object> info = new HashMap<>();
        openbd(context);

        String[] projection = {
                DatabaseContract.RestaurantsColumns.CAPTION,
                DatabaseContract.RestaurantsColumns.URL,
                DatabaseContract.RestaurantsColumns.LOGO,
                DatabaseContract.RestaurantsColumns.REITING,
                DatabaseContract.RestaurantsColumns.TIP,
                DatabaseContract.RestaurantsColumns.KITCHEN,
                DatabaseContract.RestaurantsColumns.MIN_PRICE,
                DatabaseContract.RestaurantsColumns.MAX_PRICE,
                DatabaseContract.RestaurantsColumns.ADDRESS,

        };
        String selection = DatabaseContract.RestaurantsColumns.CAPTION + " = \"" + caption + "\"";
        Cursor cursor = db.query(
                DatabaseContract.RestaurantsColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                selection,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,
                null);
        try {
            // Узнаем индекс каждого столбца

            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.CAPTION);
            int urlColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.URL);
            int logoColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.LOGO);
            int reitingColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.REITING);
            int tipColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.TIP);
            int minPriceColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.MIN_PRICE);
            int maxPriceColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.MAX_PRICE);
            int kitchenColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.KITCHEN);
            int addressColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.ADDRESS);



            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);
                String currentUrl = cursor.getString(urlColumnIndex);
                String currentLogo = cursor.getString(logoColumnIndex);
                String currentReiting = cursor.getString(reitingColumnIndex);
                String currentTip = cursor.getString(tipColumnIndex);
                String currentMinPrice = cursor.getString(minPriceColumnIndex);
                String currentMaxPrice = cursor.getString(maxPriceColumnIndex);
                String currentKitchen = cursor.getString(kitchenColumnIndex);
                String currentAddress = cursor.getString(addressColumnIndex);

                info.put(DatabaseContract.RestaurantsColumns.CAPTION, currentCaption);
                info.put(DatabaseContract.RestaurantsColumns.URL, currentUrl);
                info.put(DatabaseContract.RestaurantsColumns.LOGO, currentLogo);
                info.put(DatabaseContract.RestaurantsColumns.REITING, currentReiting);
                info.put(DatabaseContract.RestaurantsColumns.TIP, currentTip);
                info.put(DatabaseContract.RestaurantsColumns.MIN_PRICE, currentMinPrice);
                info.put(DatabaseContract.RestaurantsColumns.MAX_PRICE, currentMaxPrice);
                info.put(DatabaseContract.RestaurantsColumns.KITCHEN, currentKitchen);
                info.put(DatabaseContract.RestaurantsColumns.ADDRESS, currentAddress);
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        closebd();
        return info;
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
