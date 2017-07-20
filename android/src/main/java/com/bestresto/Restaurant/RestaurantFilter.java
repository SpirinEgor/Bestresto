package com.bestresto.Restaurant;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sergey on 30.06.17.
 */

public class RestaurantFilter extends Fragment {
    Spinner rating_from;
    Spinner rating_to;
    Button avg_price_button, rest_type_button, rest_cuisine_button, features_button;
    ScrollView avg_price_view, rest_type_view, rest_cuisine_view, features_view;

    CheckBox price_under1000;
    CheckBox price_from1000;
    CheckBox price_from2000;
    CheckBox price_from3500;
    CheckBox price_over5000;
    CheckBox features_delivery, features_banquet, features_roundtheclock, features_panorama, features_veranda, features_wifi, features_livemusic, features_coffetogo,
            features_breakfast, features_kidsanimation, features_businesslunch, features_hookah, features_karaoke, features_livesports, features_homecuisine;
    ArrayList<Pair<CheckBox, String>> cuisines, resttypes, prices, features;
    LinearLayout rest_cuisine_container, rest_type_container;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.restaurant_filter, container, false);

        rating_from = (Spinner)view.findViewById(R.id.restaurant_rating_from);
        rating_to = (Spinner)view.findViewById(R.id.restaurant_rating_to);
        ArrayList<Integer> spinner_array = new ArrayList<Integer>();
        spinner_array.add(1);//WTF???
        spinner_array.add(2);
        spinner_array.add(3);
        spinner_array.add(4);
        spinner_array.add(5);
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(),
                android.R.layout.simple_spinner_item, spinner_array);
        rating_from.setAdapter(adapter);
        rating_to.setAdapter(adapter);
        SetCuisineCheckboxes(view);
        SetRestaurantCheckBoxes(view);


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


        ////////////////////////////////////////////////////////////////////////////////////////////
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





        return view;
    }


    private void SetCuisineCheckboxes(View view){
        cuisines = new ArrayList<>();
        ArrayList<String> captions = (new KitchenTypesManager()).make_data_cuisines_sorted(view.getContext());
        rest_cuisine_container = (LinearLayout)view.findViewById(R.id.rest_cuisine_container);
        for (String caption : captions){
            CheckBox cb = new CheckBox(view.getContext());
            cb.setText(caption);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CuisineReset(rest_cuisine_button);
                }
            });
            cuisines.add(Pair.create(cb, caption));
            rest_cuisine_container.addView(cb);
        }
    }

    private  void SetRestaurantCheckBoxes(View view){
        resttypes = new ArrayList<>();
        ArrayList<String> captions = (new RestaurantTypesManager()).make_data_restaurants_sorted(view.getContext());
        rest_type_container = (LinearLayout)view.findViewById(R.id.rest_type_container);
        for (String caption : captions){
            CheckBox cb = new CheckBox(view.getContext());
            cb.setText(caption);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RestTypeReset(rest_type_button);
                }
            });
            resttypes.add(Pair.create(cb, caption));
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

}
