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
import android.widget.ScrollView;
import android.widget.Spinner;

import com.bestresto.R;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sergey on 30.06.17.
 */

public class RestaurantFilter extends Fragment {
    Spinner rating_from;
    Spinner rating_to;
    private ArrayList<Integer> spinner_array;
    Button avg_price_button, rest_type_button, rest_cuisine_button, features_button;
    ScrollView avg_price_view, rest_type_view, rest_cuisine_view, features_view;

    CheckBox price_under1000;
    CheckBox price_from1000;
    CheckBox price_from2000;
    CheckBox price_from3500;
    CheckBox price_over5000;
    CheckBox chi, arm, ara, eas, gre, geo, ind, spa, kor, veg, ita, rus, jew, eur, aut, aze, asi, usa, jap, pan, fra, ady, kav, med, per,
            tur, ska, irl, sco, ger, fus, mex, mar, ukr, eng;
    CheckBox rest_restaurant, rest_cafe, rest_bar, rest_pub, rest_gastropub, rest_vinotheque, rest_fastfood, rest_streetfood,
            rest_beerrest, rest_steakhouse, rest_beerbar, rest_gastrobar, rest_confectionary, rest_coffehouse, rest_bakery, rest_fishrest, rest_cookery;
    CheckBox features_delivery, features_banquet, features_roundtheclock, features_panorama, features_veranda, features_wifi, features_livemusic, features_coffetogo,
            features_breakfast, features_kidsanimation, features_businesslunch, features_hookah, features_karaoke, features_livesports, features_homecuisine;
    ArrayList<Pair<CheckBox, String>> cuisines, resttypes, prices, features;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.restaurant_filter, container, false);

        rating_from = (Spinner)view.findViewById(R.id.restaurant_rating_from);
        rating_to = (Spinner)view.findViewById(R.id.restaurant_rating_to);
        spinner_array = new ArrayList<Integer>();
        spinner_array.add(1);//WTF???
        spinner_array.add(2);
        spinner_array.add(3);
        spinner_array.add(4);
        spinner_array.add(5);
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(view.getContext(),
                android.R.layout.simple_spinner_item, spinner_array);
        rating_from.setAdapter(adapter);
        rating_to.setAdapter(adapter);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cuisines = new ArrayList<>();
        chi = (CheckBox)view.findViewById(R.id.cuisine_chi);
        cuisines.add(Pair.create(chi, getString(R.string.chi)));
        arm = (CheckBox)view.findViewById(R.id.cuisine_arm);
        cuisines.add(Pair.create(arm, getString(R.string.arm)));
        ara = (CheckBox)view.findViewById(R.id.cuisine_ara);
        cuisines.add(Pair.create(ara, getString(R.string.ara)));
        eas = (CheckBox)view.findViewById(R.id.cuisine_eas);
        cuisines.add(Pair.create(eas, getString(R.string.eas)));
        gre = (CheckBox)view.findViewById(R.id.cuisine_gre);
        cuisines.add(Pair.create(gre, getString(R.string.gre)));
        geo = (CheckBox)view.findViewById(R.id.cuisine_geo);
        cuisines.add(Pair.create(geo, getString(R.string.geo)));
        ind = (CheckBox)view.findViewById(R.id.cuisine_ind);
        cuisines.add(Pair.create(ind, getString(R.string.ind)));
        spa = (CheckBox)view.findViewById(R.id.cuisine_spa);
        cuisines.add(Pair.create(spa, getString(R.string.spa)));
        kor = (CheckBox)view.findViewById(R.id.cuisine_kor);
        cuisines.add(Pair.create(kor, getString(R.string.kor)));
        veg = (CheckBox)view.findViewById(R.id.cuisine_veg);
        cuisines.add(Pair.create(veg, getString(R.string.veg)));
        ita = (CheckBox)view.findViewById(R.id.cuisine_ita);
        cuisines.add(Pair.create(ita, getString(R.string.ita)));
        rus = (CheckBox)view.findViewById(R.id.cuisine_rus);
        cuisines.add(Pair.create(rus, getString(R.string.rus)));
        jew = (CheckBox)view.findViewById(R.id.cuisine_jew);
        cuisines.add(Pair.create(jew, getString(R.string.jew)));
        eur = (CheckBox)view.findViewById(R.id.cuisine_eur);
        cuisines.add(Pair.create(eur, getString(R.string.eur)));
        aut = (CheckBox)view.findViewById(R.id.cuisine_aut);
        cuisines.add(Pair.create(aut, getString(R.string.aut)));
        aze = (CheckBox)view.findViewById(R.id.cuisine_aze);
        cuisines.add(Pair.create(aze, getString(R.string.aze)));
        asi = (CheckBox)view.findViewById(R.id.cuisine_asi);
        cuisines.add(Pair.create(asi, getString(R.string.asi)));
        usa = (CheckBox)view.findViewById(R.id.cuisine_usa);
        cuisines.add(Pair.create(usa, getString(R.string.usa)));
        jap = (CheckBox)view.findViewById(R.id.cuisine_jap);
        cuisines.add(Pair.create(jap, getString(R.string.jap)));
        pan = (CheckBox)view.findViewById(R.id.cuisine_pan);
        cuisines.add(Pair.create(pan, getString(R.string.pan)));
        fra = (CheckBox)view.findViewById(R.id.cuisine_fra);
        cuisines.add(Pair.create(fra, getString(R.string.fra)));
        ady = (CheckBox)view.findViewById(R.id.cuisine_ady);
        cuisines.add(Pair.create(ady, getString(R.string.ady)));
        kav = (CheckBox)view.findViewById(R.id.cuisine_kav);
        cuisines.add(Pair.create(kav, getString(R.string.kav)));
        med = (CheckBox)view.findViewById(R.id.cuisine_med);
        cuisines.add(Pair.create(med, getString(R.string.med)));
        per = (CheckBox)view.findViewById(R.id.cuisine_per);
        cuisines.add(Pair.create(per, getString(R.string.per)));
        tur = (CheckBox)view.findViewById(R.id.cuisine_tur);
        cuisines.add(Pair.create(tur, getString(R.string.tur)));
        ska = (CheckBox)view.findViewById(R.id.cuisine_ska);
        cuisines.add(Pair.create(ska, getString(R.string.ska)));
        irl = (CheckBox)view.findViewById(R.id.cuisine_irl);
        cuisines.add(Pair.create(irl, getString(R.string.irl)));
        sco = (CheckBox)view.findViewById(R.id.cuisine_sco);
        cuisines.add(Pair.create(sco, getString(R.string.sco)));
        ger = (CheckBox)view.findViewById(R.id.cuisine_ger);
        cuisines.add(Pair.create(ger, getString(R.string.ger)));
        fus = (CheckBox)view.findViewById(R.id.cuisine_fus);
        cuisines.add(Pair.create(fus, getString(R.string.fus)));
        mex = (CheckBox)view.findViewById(R.id.cuisine_mex);
        cuisines.add(Pair.create(mex, getString(R.string.mex)));
        mar = (CheckBox)view.findViewById(R.id.cuisine_mar);
        cuisines.add(Pair.create(mar, getString(R.string.mar)));
        ukr = (CheckBox)view.findViewById(R.id.cuisine_ukr);
        cuisines.add(Pair.create(ukr, getString(R.string.ukr)));
        eng = (CheckBox)view.findViewById(R.id.cuisine_eng);
        cuisines.add(Pair.create(eng, getString(R.string.eng)));

        //OnCheckedListeners for cuisine section
        for (Pair<CheckBox, String> cuisine:cuisines) {
            cuisine.first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CuisineReset(rest_cuisine_button);
                }
            });
        }

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

        /////////////////////////////////////////////////////////////////////////////////
        resttypes = new ArrayList<>();
        rest_restaurant = (CheckBox)view.findViewById(R.id.rest_restaurant);
        resttypes.add(Pair.create(rest_restaurant, getString(R.string.restaurant)));
        rest_cafe = (CheckBox)view.findViewById(R.id.rest_cafe);
        resttypes.add(Pair.create(rest_cafe, getString(R.string.cafe)));
        rest_bar = (CheckBox)view.findViewById(R.id.rest_bar);
        resttypes.add(Pair.create(rest_bar, getString(R.string.bar)));
        rest_pub = (CheckBox)view.findViewById(R.id.rest_pub);
        resttypes.add(Pair.create(rest_pub, getString(R.string.pub)));
        rest_gastropub = (CheckBox)view.findViewById(R.id.rest_gastropub);
        resttypes.add(Pair.create(rest_gastropub, getString(R.string.gastropub)));
        rest_vinotheque = (CheckBox)view.findViewById(R.id.rest_vinotheque);
        resttypes.add(Pair.create(rest_vinotheque, getString(R.string.vinotheque)));
        rest_fastfood = (CheckBox)view.findViewById(R.id.rest_fastfood);
        resttypes.add(Pair.create(rest_fastfood, getString(R.string.fastfood)));
        rest_streetfood = (CheckBox)view.findViewById(R.id.rest_streetfood);
        resttypes.add(Pair.create(rest_streetfood, getString(R.string.streetfood)));
        rest_beerrest = (CheckBox)view.findViewById(R.id.rest_beerrest);
        resttypes.add(Pair.create(rest_beerrest, getString(R.string.beerrest)));
        rest_steakhouse = (CheckBox)view.findViewById(R.id.rest_steakhouse);
        resttypes.add(Pair.create(rest_steakhouse, getString(R.string.steakhouse)));
        rest_beerbar = (CheckBox)view.findViewById(R.id.rest_beerbar);
        resttypes.add(Pair.create(rest_beerbar, getString(R.string.beerbar)));
        rest_gastrobar = (CheckBox)view.findViewById(R.id.rest_gastrobar);
        resttypes.add(Pair.create(rest_gastrobar, getString(R.string.gastrobar)));
        rest_confectionary = (CheckBox)view.findViewById(R.id.rest_confectionary);
        resttypes.add(Pair.create(rest_confectionary, getString(R.string.confectionary)));
        rest_coffehouse = (CheckBox)view.findViewById(R.id.rest_coffehouse);
        resttypes.add(Pair.create(rest_coffehouse, getString(R.string.coffeehouse)));
        rest_bakery = (CheckBox)view.findViewById(R.id.rest_bakery);
        resttypes.add(Pair.create(rest_bakery, getString(R.string.bakery)));
        rest_fishrest = (CheckBox)view.findViewById(R.id.rest_fishrest);
        resttypes.add(Pair.create(rest_fishrest, getString(R.string.fishrest)));
        rest_cookery = (CheckBox)view.findViewById(R.id.rest_cookery);
        resttypes.add(Pair.create(rest_cookery, getString(R.string.cookery)));

        for (Pair<CheckBox, String> resttype:resttypes) {
            resttype.first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RestTypeReset(rest_type_button);
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
