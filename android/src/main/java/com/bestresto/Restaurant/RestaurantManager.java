package com.bestresto.Restaurant;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DatabaseWork;
import com.bestresto.Database.QueryConditions;
import com.bestresto.ManagerInterface;
import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantManager implements ManagerInterface {

    @Override
    public void addAllDb(ArrayList<HashMap<String, Object>> data){
        DatabaseWork.cleanTable(DatabaseContract.RestaurantsColumns.TABLE_NAME);
        StringBuilder SQLScript = new StringBuilder("INSERT INTO " + DatabaseContract.RestaurantsColumns.TABLE_NAME + " (" +
                DatabaseContract.RestaurantsColumns.CAPTION + ", " +
                DatabaseContract.RestaurantsColumns.URL + ", " +
                DatabaseContract.RestaurantsColumns.LOGO + ", " +
                DatabaseContract.RestaurantsColumns.REITING + ", " +
                DatabaseContract.RestaurantsColumns.TIP + ", " +
                DatabaseContract.RestaurantsColumns.MIN_PRICE + ", " +
                DatabaseContract.RestaurantsColumns.MAX_PRICE + ", " +
                DatabaseContract.RestaurantsColumns.KITCHEN + ", " +
                DatabaseContract.RestaurantsColumns.ADDRESS + ") VALUES ");
        for (HashMap<String, Object> rest: data) {
            SQLScript.append(createStringForSQLScript(rest));
            if (rest.equals(data.get(data.size() - 1))) {
                SQLScript.append(";");
            } else {
                SQLScript.append(", ");
            }
        }
        DatabaseWork.execSQL(SQLScript.toString());
    }

    @Override
    public String createStringForSQLScript(HashMap<String, Object> rest) {
        KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
        String result = "(";
        result += DatabaseWork.prepare(rest.get(DatabaseContract.RestaurantsColumns.CAPTION) == null ?
                "" : rest.get(DatabaseContract.RestaurantsColumns.CAPTION).toString()) + ", ";
        result += DatabaseWork.prepare(rest.get(DatabaseContract.RestaurantsColumns.URL) == null ?
                "" : rest.get(DatabaseContract.RestaurantsColumns.URL).toString()) + ", ";
        result += DatabaseWork.prepare(rest.get(DatabaseContract.RestaurantsColumns.LOGO) == null ?
                "" : rest.get(DatabaseContract.RestaurantsColumns.LOGO).toString()) + ", ";
        result += (rest.get(DatabaseContract.RestaurantsColumns.REITING) == null ?
                0.0 : Float.parseFloat(rest.get(DatabaseContract.RestaurantsColumns.REITING).toString())) + ", ";
        result += DatabaseWork.prepare(rest.get(DatabaseContract.RestaurantsColumns.TIP) == null ?
                "" : rest.get(DatabaseContract.RestaurantsColumns.TIP).toString()) + ", ";
        result += (rest.get(DatabaseContract.RestaurantsColumns.MIN_PRICE) == null ?
                0 : Integer.parseInt(rest.get(DatabaseContract.RestaurantsColumns.MIN_PRICE).toString())) + ", ";
        result += (rest.get(DatabaseContract.RestaurantsColumns.MAX_PRICE) == null ?
                0 : Integer.parseInt(rest.get(DatabaseContract.RestaurantsColumns.MAX_PRICE).toString())) + ", ";
        result += (rest.get(DatabaseContract.RestaurantsColumns.KITCHEN) == null ?
                0 : kitchenTypesManager.getKitchenNumber(rest.get(DatabaseContract.RestaurantsColumns.KITCHEN).toString())) + ", ";
        result += DatabaseWork.prepare(rest.get(DatabaseContract.RestaurantsColumns.ADDRESS) == null ?
                "" : rest.get(DatabaseContract.RestaurantsColumns.ADDRESS).toString());
        result += ")";
        return result;
    }

    @Override
    public Object getValue(Cursor cursor, String column, int index){
        switch (column) {
            case DatabaseContract.RestaurantsColumns.MIN_PRICE:
            case DatabaseContract.RestaurantsColumns.MAX_PRICE:
                return cursor.getInt(index);
            case DatabaseContract.RestaurantsColumns.REITING:
                return cursor.getFloat(index);
            case DatabaseContract.RestaurantsColumns.KITCHEN:
                KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
                return kitchenTypesManager.getKitchensName(cursor.getInt(index));
            default:
                return cursor.getString(index);
        }
    }

    static ArrayAdapter getAdapterWithData(Context context, QueryConditions queryConditions){
        return new RestaurantManager.CustomAdapter(context, DatabaseWork.makeData(queryConditions));
    }

    private static class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {

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
