package com.bestresto.Restaurant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DatabaseWork;
import com.bestresto.Database.QueryConditions;
import com.bestresto.FilterListener;
import com.bestresto.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergey on 27.07.17.
 */

public class RestaurantFilterDialog extends DialogFragment {

    FilterListener mCallback;
    Context parentContext;

    Button avg_price_button, rest_cuisine_button, rest_type_button,features_button;
    LinearLayout rest_cuisine_container, rest_type_container;
    ArrayList<Pair<CheckBox, String>> cuisines, resttypes, prices, features;
    ScrollView avg_price_view, rest_type_view, rest_cuisine_view, features_view;
    CheckBox price_under1000;
    CheckBox price_from1000;
    CheckBox price_from2000;
    CheckBox price_from3500;
    CheckBox price_over5000;
    CheckBox features_delivery, features_banquet, features_roundtheclock, features_panorama, features_veranda, features_wifi, features_livemusic, features_coffetogo,
            features_breakfast, features_kidsanimation, features_businesslunch, features_hookah, features_karaoke, features_livesports, features_homecuisine;
    EditText rest_caption;

    public void AttachFragment(Fragment fragment, Context context) {
        try {
            mCallback = (FilterListener) fragment;
            parentContext = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString()
                    + " must implement FilterListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.rest_filter, null);

        rest_caption = (EditText)view.findViewById(R.id.restaurant_title);
        //HERE SHOULD BE SETTINGS FOR SPINNER
        SetRestaurantCheckBoxes(view);
        SetCuisineCheckboxes(view);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        prices = new ArrayList<>();
        price_under1000 = (CheckBox)view.findViewById(R.id.price_under1000);
        prices.add(Pair.create(price_under1000, getString(R.string.under1000)));
        price_from1000 = (CheckBox)view.findViewById(R.id.price_from1000);
        prices.add(Pair.create(price_from1000, getString(R.string.from1000)));
        price_from2000 = (CheckBox)view.findViewById(R.id.price_from2000);
        prices.add(Pair.create(price_from2000, getString(R.string.from2000)));
        price_from3500 = (CheckBox)view.findViewById(R.id.price_from3500);
        prices.add(Pair.create(price_from3500, getString(R.string.from3500)));
        price_over5000 = (CheckBox)view.findViewById(R.id.price_over5000);
        prices.add(Pair.create(price_over5000, getString(R.string.over5000)));


        for (Pair<CheckBox, String> price:prices) {
            price.first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    AveragePriceReset(avg_price_button);
                }
            });
        }

        ///////////////////////////////////////////////////////////////////////////////////////////
        features = new ArrayList<>();
        features_delivery = (CheckBox)view.findViewById(R.id.features_delivery);
        features.add(Pair.create(features_delivery, getString(R.string.delivery)));
        features_banquet = (CheckBox)view.findViewById(R.id.features_banquet);
        features.add(Pair.create(features_banquet, getString(R.string.banquet)));
        features_roundtheclock = (CheckBox)view.findViewById(R.id.features_roundtheclock);
        features.add(Pair.create(features_roundtheclock, getString(R.string.roundtheclock)));
        features_panorama = (CheckBox)view.findViewById(R.id.features_panorama);
        features.add(Pair.create(features_panorama, getString(R.string.panorama)));
        features_veranda = (CheckBox)view.findViewById(R.id.features_veranda);
        features.add(Pair.create(features_veranda, getString(R.string.veranda)));
        features_wifi = (CheckBox)view.findViewById(R.id.features_wifi);
        features.add(Pair.create(features_wifi, getString(R.string.wifi)));
        features_livemusic = (CheckBox)view.findViewById(R.id.features_livemusic);
        features.add(Pair.create(features_livemusic, getString(R.string.livemusic)));
        features_coffetogo = (CheckBox)view.findViewById(R.id.features_coffetogo);
        features.add(Pair.create(features_coffetogo, getString(R.string.coffeetogo)));
        features_breakfast = (CheckBox)view.findViewById(R.id.features_breakfast);
        features.add(Pair.create(features_breakfast, getString(R.string.breakfast)));
        features_kidsanimation = (CheckBox)view.findViewById(R.id.features_kidsanimation);
        features.add(Pair.create(features_kidsanimation, getString(R.string.kidsanimation)));
        features_businesslunch = (CheckBox)view.findViewById(R.id.features_businesslunch);
        features.add(Pair.create(features_businesslunch, getString(R.string.businesslunch)));
        features_hookah = (CheckBox)view.findViewById(R.id.features_hookah);
        features.add(Pair.create(features_hookah, getString(R.string.hookah)));
        features_karaoke = (CheckBox)view.findViewById(R.id.features_karaoke);
        features.add(Pair.create(features_karaoke, getString(R.string.karaoke)));
        features_livesports = (CheckBox)view.findViewById(R.id.features_livesports);
        features.add(Pair.create(features_livesports, getString(R.string.livesports)));
        features_homecuisine = (CheckBox)view.findViewById(R.id.features_homecuisine);
        features.add(Pair.create(features_homecuisine, getString(R.string.homecuisine)));

        for (Pair<CheckBox, String> feature:features) {
            feature.first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    FeaturesReset(features_button);
                }
            });
        }


        avg_price_view = (ScrollView)view.findViewById(R.id.avg_price_view);
        rest_type_view = (ScrollView)view.findViewById(R.id.rest_type_view);
        rest_cuisine_view = (ScrollView)view.findViewById(R.id.rest_cuisine_view);
        features_view = (ScrollView)view.findViewById(R.id.features_view);

        avg_price_button = (Button)view.findViewById(R.id.avg_price_button);
        avg_price_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(avg_price_view.getVisibility() == View.GONE){
                    avg_price_view.setVisibility(View.VISIBLE);
                    rest_type_view.setVisibility(View.GONE);
                    rest_cuisine_view.setVisibility(View.GONE);
                    features_view.setVisibility(View.GONE);
                }
                else {
                    avg_price_view.setVisibility(View.GONE);
                }
            }
        });

        rest_type_button = (Button)view.findViewById(R.id.rest_type_button);
        rest_type_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rest_type_view.getVisibility() == View.GONE){
                    avg_price_view.setVisibility(View.GONE);
                    rest_type_view.setVisibility(View.VISIBLE);
                    rest_cuisine_view.setVisibility(View.GONE);
                    features_view.setVisibility(View.GONE);
                }
                else {
                    rest_type_view.setVisibility(View.GONE);
                }
            }
        });

        rest_cuisine_button = (Button)view.findViewById(R.id.rest_cuisine_button);
        rest_cuisine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rest_cuisine_view.getVisibility() == View.GONE){
                    avg_price_view.setVisibility(View.GONE);
                    rest_type_view.setVisibility(View.GONE);
                    rest_cuisine_view.setVisibility(View.VISIBLE);
                    features_view.setVisibility(View.GONE);
                }
                else {
                    rest_cuisine_view.setVisibility(View.GONE);
                }
            }
        });

        features_button = (Button)view.findViewById(R.id.features_button);
        features_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(features_view.getVisibility() == View.GONE){
                    avg_price_view.setVisibility(View.GONE);
                    rest_type_view.setVisibility(View.GONE);
                    rest_cuisine_view.setVisibility(View.GONE);
                    features_view.setVisibility(View.VISIBLE);
                }
                else {
                    features_view.setVisibility(View.GONE);
                }
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Найти", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mCallback.SetNewData(CollectUserFilterSettings(), parentContext);
                    }
                })
                .setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RestaurantFilterDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();


    }

    private void SetCuisineCheckboxes(View view){
        cuisines = new ArrayList<>();
        rest_cuisine_container = (LinearLayout)view.findViewById(R.id.rest_cuisine_container);

        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setTableName(DatabaseContract.KitchenTypesColumns.TABLE_NAME);
        queryConditions.setColumns(new String[] {DatabaseContract.KitchenTypesColumns.CAPTION});
        queryConditions.setWhereCondition(DatabaseContract.KitchenTypesColumns.ACTIVE + " = 1");
        queryConditions.setOrderByCondition(DatabaseContract.KitchenTypesColumns.SORT + " ASC");
        ArrayList<HashMap<String, Object>> captions = DatabaseWork.makeData(queryConditions);
        for (Map<String, Object> caption : captions){
            CheckBox cb = new CheckBox(view.getContext());
            cb.setText(caption.get(DatabaseContract.KitchenTypesColumns.CAPTION).toString());
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CuisineReset(rest_cuisine_button);
                }
            });
            cuisines.add(Pair.create(cb, caption.get(DatabaseContract.KitchenTypesColumns.CAPTION).toString()));
            rest_cuisine_container.addView(cb);
        }
    }

    private  void SetRestaurantCheckBoxes(View view){
        resttypes = new ArrayList<>();
        rest_type_container = (LinearLayout)view.findViewById(R.id.rest_type_container);

        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setTableName(DatabaseContract.RestaurantTypesColumns.TABLE_NAME);
        queryConditions.setColumns(new String[] {DatabaseContract.RestaurantTypesColumns.CAPTION});
        queryConditions.setWhereCondition(DatabaseContract.RestaurantTypesColumns.ACTIVE + " = 1");
        queryConditions.setOrderByCondition(DatabaseContract.RestaurantTypesColumns.SORT + " ASC");
        ArrayList<HashMap<String, Object>> captions = DatabaseWork.makeData(queryConditions);
        for (Map<String, Object> caption : captions){
            CheckBox cb = new CheckBox(view.getContext());
            cb.setText(caption.get(DatabaseContract.RestaurantTypesColumns.CAPTION).toString());
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RestTypeReset(rest_type_button);
                }
            });
            resttypes.add(Pair.create(cb, caption.get(DatabaseContract.RestaurantTypesColumns.CAPTION).toString()));
            rest_type_container.addView(cb);
        }
    }

    private void AveragePriceReset(Button avg_price_button){
        String result = "";
        for (Pair<CheckBox, String> price : prices){
            if (price.first.isChecked()){
                if (!result.equals("")) result += ", ";
                result += price.second;
            }
        }
        if (result.equals("")){
            result = "Средний чек";
        }

        avg_price_button.setText(result);
    }

    private void RestTypeReset(Button rest_type_button) {
        String result = "";
        int count = 0;
        for (Pair<CheckBox, String> resttype: resttypes){
            if (resttype.first.isChecked()){
                if ((count >= 5)){
                    if (!result.endsWith(", ..."))result += ", ...";
                }
                else{
                    count++;
                    if (!result.equals("")) result +=", ";
                    result += resttype.second;
                }
            }
        }
        if (result.equals("")) result = "Тип заведения";

        rest_type_button.setText(result);

    }

    private void CuisineReset(Button dish_cuisine_button){
        int count = 0;
        String result = "";
        for (Pair<CheckBox, String> cuisine:cuisines) {
            if (cuisine.first.isChecked()){
                if (count>=5){
                    if (!result.endsWith(", ..."))result += ", ...";
                }
                else{
                    count++;
                    if(!result.equals("")) result +=", ";
                    result += cuisine.second;
                }
            }

        }
        if (result.equals("")) result = "Выберите кухню";

        dish_cuisine_button.setText(result);
    }

    private void FeaturesReset(Button features_button){
        String result = "";
        int count = 0;
        for (Pair<CheckBox, String> feature : features){
            if (feature.first.isChecked()){
                if ((count >= 5)){
                    if (!result.endsWith(", ..."))result += ", ...";
                }
                else{
                    count++;
                    if (!result.equals("")) result += ", ";
                    result += feature.second;
                }
            }
        }
        if (result.equals("")) result = "Особенности заведения";

        features_button.setText(result);

    }

    private Bundle CollectUserFilterSettings(){
        Bundle bundle = new Bundle();
        String caption = rest_caption.getText().toString();
        bundle.putString("rest_caption", caption);

        ArrayList<String> cuisine_params = new ArrayList<String>();
        for (Pair<CheckBox, String> cuisine:cuisines) {
            if (cuisine.first.isChecked()) cuisine_params.add(cuisine.second);
        }
        bundle.putStringArrayList("kitchen_params", cuisine_params);

        return bundle;
    }

}
