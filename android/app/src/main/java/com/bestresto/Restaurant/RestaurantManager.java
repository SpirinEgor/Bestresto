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
        values.put(DatabaseContract.RestaurantsColumns.INDEXID,
                (rest.get(DatabaseContract.RestaurantsColumns.INDEXID) == null ? "" : rest.get(DatabaseContract.RestaurantsColumns.INDEXID).toString()));
        values.put(DatabaseContract.RestaurantsColumns.ACTIVE,
                (rest.get(DatabaseContract.RestaurantsColumns.ACTIVE) == null ? "" : rest.get(DatabaseContract.RestaurantsColumns.ACTIVE).toString()));
        values.put(DatabaseContract.RestaurantsColumns.CAPTION,
                (rest.get(DatabaseContract.RestaurantsColumns.CAPTION) == null ? "" : rest.get(DatabaseContract.RestaurantsColumns.CAPTION).toString()));
        values.put(DatabaseContract.RestaurantsColumns.SORT,
                (rest.get(DatabaseContract.RestaurantsColumns.SORT) == null ? "" : rest.get(DatabaseContract.RestaurantsColumns.SORT).toString()));
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
                DatabaseContract.RestaurantsColumns.CAPTION
        };
        Cursor cursor = db.query(
                DatabaseContract.RestaurantsColumns.TABLE_NAME,   // таблица
                projection,            // столбцы
                DatabaseContract.RestaurantsColumns.ACTIVE + " = \"1\"",                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        Log.d("size", String.valueOf(cursor.getCount()));
        try {
            // Узнаем индекс каждого столбца
            int captionColumnIndex = cursor.getColumnIndex(DatabaseContract.RestaurantsColumns.CAPTION);

            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);

                HashMap<String, Object> cur = new HashMap<>();
                cur.put(DatabaseContract.RestaurantsColumns.CAPTION, currentCaption);
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
                DatabaseContract.RestaurantsColumns.CAPTION
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

            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                String currentCaption = cursor.getString(captionColumnIndex);

                info.put(DatabaseContract.RestaurantsColumns.CAPTION, currentCaption);
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

            return convertView;
        }
    }
}
