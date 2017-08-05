package com.bestresto.Dish;

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
import com.bestresto.Types.RestaurantTypesManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by egor on 30.05.17.
 * make bd, make adapters, get data
 */

public class DishManager implements ManagerInterface {

    @Override
    public void addAllDb(final ArrayList<HashMap<String, Object>> data) {
        DatabaseWork.cleanTable(DatabaseContract.DishesColumns.TABLE_NAME);
        StringBuilder SQLScript = new StringBuilder("INSERT INTO " + DatabaseContract.DishesColumns.TABLE_NAME + " (" +
                DatabaseContract.DishesColumns.INDEXID + ", " +
                DatabaseContract.DishesColumns.PARENT_ID + ", " +
                DatabaseContract.DishesColumns.ACTIVE+ ", " +
                DatabaseContract.DishesColumns.CAPTION + ", " +
                DatabaseContract.DishesColumns.KITCHEN + ", " +
                DatabaseContract.DishesColumns.PRICE + ", " +
                DatabaseContract.DishesColumns.DESC + ", " +
                DatabaseContract.DishesColumns.FIRST_PAGE + ", " +
                DatabaseContract.DishesColumns.PICTURE + ", " +
                DatabaseContract.DishesColumns.DOPPIC + ", " +
                DatabaseContract.DishesColumns.SORT + ", " +
                DatabaseContract.DishesColumns.REITING + ", " +
                DatabaseContract.DishesColumns.GARANT + ", " +
                DatabaseContract.DishesColumns.SEARCHTAGS + ", " +
                DatabaseContract.DishesColumns.CREATEDATE + ") VALUES ");
        for (HashMap<String, Object> dish: data) {
            SQLScript.append(createStringForSQLScript(dish));
            if (dish.equals(data.get(data.size() - 1))) {
                SQLScript.append(";");
            } else {
                SQLScript.append(", ");
            }
        }
        DatabaseWork.execSQL(SQLScript.toString());
    }

    @Override
    public String createStringForSQLScript(HashMap<String, Object> dish) {
        KitchenTypesManager kitchenTypesManager = new KitchenTypesManager();
        RestaurantTypesManager restaurantTypesManager = new RestaurantTypesManager();
        String result = "(";
        result += (dish.get(DatabaseContract.DishesColumns.INDEXID) == null ? 0 :
                Integer.parseInt(dish.get(DatabaseContract.DishesColumns.INDEXID).toString())) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.PARENT_ID) == null ? -1 :
                restaurantTypesManager.getRestaurantNumber(dish.get(DatabaseContract.DishesColumns.PARENT_ID).toString())) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.ACTIVE) == null ? 0 :
                dish.get(DatabaseContract.DishesColumns.ACTIVE).toString().equals("yes") ? 1: 0) + ", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.CAPTION) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.CAPTION).toString()) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.KITCHEN) == null ? -1 :
                kitchenTypesManager.getKitchenNumber(dish.get(DatabaseContract.DishesColumns.KITCHEN).toString())) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.PRICE) == null ? 0 :
                Integer.parseInt(dish.get(DatabaseContract.DishesColumns.PRICE).toString())) + ", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.DESC) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.DESC).toString()) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.FIRST_PAGE) == null ? 0 :
                dish.get(DatabaseContract.DishesColumns.FIRST_PAGE).toString().equals("yes") ? 1: 0) + ", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.PICTURE) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.PICTURE).toString()) + ", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.DOPPIC) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.DOPPIC).toString()) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.SORT) == null ? 0 :
                Integer.parseInt(dish.get(DatabaseContract.DishesColumns.SORT).toString())) + ", ";
        result += (dish.get(DatabaseContract.DishesColumns.REITING) == null || dish.get(DatabaseContract.DishesColumns.REITING).equals("")
                ? 0.0 : Float.parseFloat(dish.get(DatabaseContract.DishesColumns.REITING).toString())) + ", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.GARANT) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.GARANT).toString()) + ", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.SEARCHTAGS) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.SEARCHTAGS).toString()) +", ";
        result += DatabaseWork.prepare(dish.get(DatabaseContract.DishesColumns.CREATEDATE) == null ? "" :
                dish.get(DatabaseContract.DishesColumns.CREATEDATE).toString());
        result += ")";
        return result;
    }

    @Override
    public  Object getValue(Cursor cursor, String column, int index){
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
                return kitchenTypesManager.getKitchensName(cursor.getInt(index));
            case DatabaseContract.DishesColumns.PARENT_ID:
                RestaurantTypesManager restaurantTypesManager = new RestaurantTypesManager();
                return restaurantTypesManager.getRestaurants(cursor.getInt(index));
            default:
                return cursor.getString(index);
        }
    }

    static ArrayAdapter getAdapterWithData(Context context, QueryConditions queryConditions){
        return new CustomAdapter(context, DatabaseWork.makeData(queryConditions));
    }

    private static class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {

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

