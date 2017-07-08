package com.bestresto.Restaurant;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    CheckBox chi, arm, ara, wes, gre, geo, ind, spa, kor, veg, ita, rus, jew, eur, aut, aze, asi, usa, jap, pan, fra, ady, kav, med, per,
            tur, ska, irl, sco, ger, fus, mex, mar, ukr, eng;
    CheckBox rest_restaurant, rest_cafe, rest_bar, rest_pub, rest_gastropub, rest_vinotheque, rest_fastfood, rest_streetfood,
            rest_beerrest, rest_steakhouse, rest_beerbar, rest_gastrobar, rest_confectionary, rest_coffehouse, rest_bakery, rest_fishrest, rest_cookery;
    CheckBox features_delivery, features_banquet, features_roundtheclock, features_panorama, features_veranda, features_wifi, features_livemusic, features_coffetogo,
            features_breakfast, features_kidsanimation, features_businesslunch, features_hookah, features_karaoke, features_livesports, features_homecuisine;


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
        chi = (CheckBox)view.findViewById(R.id.cuisine_chi);
        arm = (CheckBox)view.findViewById(R.id.cuisine_arm);
        ara = (CheckBox)view.findViewById(R.id.cuisine_ara);
        wes = (CheckBox)view.findViewById(R.id.cuisine_wes);
        gre = (CheckBox)view.findViewById(R.id.cuisine_gre);
        geo = (CheckBox)view.findViewById(R.id.cuisine_geo);
        ind = (CheckBox)view.findViewById(R.id.cuisine_ind);
        spa = (CheckBox)view.findViewById(R.id.cuisine_spa);
        kor = (CheckBox)view.findViewById(R.id.cuisine_kor);
        veg = (CheckBox)view.findViewById(R.id.cuisine_veg);
        ita = (CheckBox)view.findViewById(R.id.cuisine_ita);
        rus = (CheckBox)view.findViewById(R.id.cuisine_rus);
        jew = (CheckBox)view.findViewById(R.id.cuisine_jew);
        eur = (CheckBox)view.findViewById(R.id.cuisine_eur);
        aut = (CheckBox)view.findViewById(R.id.cuisine_aut);
        aze = (CheckBox)view.findViewById(R.id.cuisine_aze);
        asi = (CheckBox)view.findViewById(R.id.cuisine_asi);
        usa = (CheckBox)view.findViewById(R.id.cuisine_usa);
        jap = (CheckBox)view.findViewById(R.id.cuisine_jap);
        pan = (CheckBox)view.findViewById(R.id.cuisine_pan);
        fra = (CheckBox)view.findViewById(R.id.cuisine_fra);
        ady = (CheckBox)view.findViewById(R.id.cuisine_ady);
        kav = (CheckBox)view.findViewById(R.id.cuisine_kav);
        med = (CheckBox)view.findViewById(R.id.cuisine_med);
        per = (CheckBox)view.findViewById(R.id.cuisine_per);
        tur = (CheckBox)view.findViewById(R.id.cuisine_tur);
        ska = (CheckBox)view.findViewById(R.id.cuisine_ska);
        irl = (CheckBox)view.findViewById(R.id.cuisine_irl);
        sco = (CheckBox)view.findViewById(R.id.cuisine_sco);
        ger = (CheckBox)view.findViewById(R.id.cuisine_ger);
        fus = (CheckBox)view.findViewById(R.id.cuisine_fus);
        mex = (CheckBox)view.findViewById(R.id.cuisine_mex);
        mar = (CheckBox)view.findViewById(R.id.cuisine_mar);
        ukr = (CheckBox)view.findViewById(R.id.cuisine_mar);
        eng = (CheckBox)view.findViewById(R.id.cuisine_mar);

        //OnCheckedListeners for cuisine section
        chi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        arm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ara.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        wes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        gre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        geo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        spa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        kor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        veg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        rus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        jew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        eur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        aut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        aze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        asi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        usa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        jap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        pan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        fra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ady.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        kav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        med.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        per.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        tur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ska.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        irl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        sco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        fus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        mex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        mar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        ukr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });
        eng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(rest_cuisine_button);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////
        price_under1000 = (CheckBox)view.findViewById(R.id.price_under1000);
        price_from1000 = (CheckBox)view.findViewById(R.id.price_from1000);
        price_from2000 = (CheckBox)view.findViewById(R.id.price_from2000);
        price_from3500 = (CheckBox)view.findViewById(R.id.price_from3500);
        price_over5000 = (CheckBox)view.findViewById(R.id.price_over5000);

        price_under1000.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AveragePriceReset(avg_price_button);
            }
        });
        price_from1000.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AveragePriceReset(avg_price_button);
            }
        });
        price_from2000.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AveragePriceReset(avg_price_button);
            }
        });
        price_from3500.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AveragePriceReset(avg_price_button);
            }
        });
        price_over5000.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AveragePriceReset(avg_price_button);
            }
        });

        /////////////////////////////////////////////////////////////////////////////////
        rest_restaurant = (CheckBox)view.findViewById(R.id.rest_restaurant);
        rest_cafe = (CheckBox)view.findViewById(R.id.rest_cafe);
        rest_bar = (CheckBox)view.findViewById(R.id.rest_bar);
        rest_pub = (CheckBox)view.findViewById(R.id.rest_pub);
        rest_gastropub = (CheckBox)view.findViewById(R.id.rest_gastropub);
        rest_vinotheque = (CheckBox)view.findViewById(R.id.rest_vinotheque);
        rest_fastfood = (CheckBox)view.findViewById(R.id.rest_fastfood);
        rest_streetfood = (CheckBox)view.findViewById(R.id.rest_streetfood);
        rest_beerrest = (CheckBox)view.findViewById(R.id.rest_beerrest);
        rest_steakhouse = (CheckBox)view.findViewById(R.id.rest_steakhouse);
        rest_beerbar = (CheckBox)view.findViewById(R.id.rest_beerbar);
        rest_gastrobar = (CheckBox)view.findViewById(R.id.rest_gastrobar);
        rest_confectionary = (CheckBox)view.findViewById(R.id.rest_confectionary);
        rest_coffehouse = (CheckBox)view.findViewById(R.id.rest_coffehouse);
        rest_bakery = (CheckBox)view.findViewById(R.id.rest_bakery);
        rest_fishrest = (CheckBox)view.findViewById(R.id.rest_fishrest);
        rest_cookery = (CheckBox)view.findViewById(R.id.rest_cookery);

        rest_restaurant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_cafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_bar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_pub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_gastropub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_vinotheque.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_fastfood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_streetfood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_beerrest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_steakhouse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_beerbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_gastrobar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_confectionary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_coffehouse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_bakery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_fishrest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        rest_cookery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RestTypeReset(rest_type_button);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////

        features_delivery = (CheckBox)view.findViewById(R.id.features_delivery);
        features_banquet = (CheckBox)view.findViewById(R.id.features_banquet);
        features_roundtheclock = (CheckBox)view.findViewById(R.id.features_roundtheclock);
        features_panorama = (CheckBox)view.findViewById(R.id.features_panorama);
        features_veranda = (CheckBox)view.findViewById(R.id.features_veranda);
        features_wifi = (CheckBox)view.findViewById(R.id.features_wifi);
        features_livemusic = (CheckBox)view.findViewById(R.id.features_livemusic);
        features_coffetogo = (CheckBox)view.findViewById(R.id.features_coffetogo);
        features_breakfast = (CheckBox)view.findViewById(R.id.features_breakfast);
        features_kidsanimation = (CheckBox)view.findViewById(R.id.features_kidsanimation);
        features_businesslunch = (CheckBox)view.findViewById(R.id.features_businesslunch);
        features_hookah = (CheckBox)view.findViewById(R.id.features_hookah);
        features_karaoke = (CheckBox)view.findViewById(R.id.features_karaoke);
        features_livesports = (CheckBox)view.findViewById(R.id.features_livesports);
        features_homecuisine = (CheckBox)view.findViewById(R.id.features_homecuisine);

        features_delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_banquet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_roundtheclock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_panorama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_veranda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_livemusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_coffetogo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_breakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_kidsanimation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_businesslunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_hookah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_karaoke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_livesports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });
        features_homecuisine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FeaturesReset(features_button);
            }
        });

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
        if (price_under1000.isChecked()){
            result += (getString(R.string.under1000));
        }
        if (price_from1000.isChecked()){
            if (!result.equals("")) result += ", ";
            result += (getString(R.string.from1000));
        }
        if (price_from2000.isChecked()){
            if (!result.equals("")) result += ", ";
            result += (getString(R.string.from2000));
        }
        if (price_from3500.isChecked()){
            if (!result.equals("")) result += ", ";
            result += (getString(R.string.from3500));
        }
        if (price_over5000.isChecked()){
            if (!result.equals("")) result += ", ";
            result += (getString(R.string.over5000));
        }
        if (result.equals("")){
            result = "Средний чек";
        }

        avg_price_button.setText(result);
    }

    private void RestTypeReset(Button rest_type_button) {
        String result = "";
        int count = 0;
        if (rest_restaurant.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                result += getString(R.string.restaurant);
            }
        }
        if (rest_cafe.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.cafe);
            }
        }
        if (rest_bar.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.bar);
            }
        }
        if (rest_pub.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.pub);
            }
        }
        if (rest_gastropub.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.gastropub);
            }
        }
        if (rest_vinotheque.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.vinotheque);
            }
        }
        if (rest_fastfood.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.fastfood);
            }
        }
        if (rest_streetfood.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.streetfood);
            }
        }
        if (rest_beerrest.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.beerrest);
            }
        }
        if (rest_steakhouse.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.steakhouse);
            }
        }
        if (rest_beerbar.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.beerbar);
            }
        }
        if (rest_gastrobar.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.gastrobar);
            }
        }
        if (rest_confectionary.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.confectionary);
            }
        }
        if (rest_coffehouse.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.coffeehouse);
            }
        }
        if (rest_bakery.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.bakery);
            }
        }
        if (rest_fishrest.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.fishrest);
            }
        }
        if (rest_cookery.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result +=", ";
                result +=getString(R.string.cookery);
            }
        }
        if (result.equals("")) result = "Тип заведения";

        rest_type_button.setText(result);

    }

    private void CuisineReset(Button rest_cuisine_button){
        int count = 0;
        String result = "";
        if (chi.isChecked()){
            count++;
            result += getString(R.string.chi);
        }
        if (arm.isChecked()){
            count++;
            if(!result.equals("")) result +=", ";
            result += getString(R.string.arm);
        }
        if (ara.isChecked()){
            count++;
            if(!result.equals("")) result +=", ";
            result += getString(R.string.ara);
        }
        if (wes.isChecked()){
            count++;
            if(!result.equals("")) result +=", ";
            result += getString(R.string.wes);
        }
        if (gre.isChecked()){
            count++;
            if(!result.equals("")) result +=", ";
            result += getString(R.string.gre);
        }
        if (geo.isChecked()){
            count++;
            if(!result.equals("")) result +=", ";
            result += getString(R.string.geo);
        }
        if (ind.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.ind);
            }
        }
        if (spa.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.spa);
            }
        }
        if (kor.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.kor);
            }
        }
        if (veg.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.veg);
            }
        }
        if (ita.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.ita);
            }
        }
        if (rus.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.rus);
            }
        }
        if (jew.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.jew);
            }
        }
        if (eur.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.eur);
            }
        }
        if (aut.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.aut);
            }
        }
        if (aze.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.aze);
            }
        }
        if (asi.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.asi);
            }
        }
        if (usa.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.usa);
            }
        }
        if (jap.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.jap);
            }
        }
        if (pan.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.pan);
            }
        }
        if (fra.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.fra);
            }
        }
        if (ady.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.ady);
            }
        }
        if (kav.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.kav);
            }
        }
        if (med.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.med);
            }
        }
        if (per.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.per);
            }
        }
        if (tur.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.tur);
            }
        }
        if (ska.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.ska);
            }
        }
        if (irl.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.irl);
            }
        }
        if (sco.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.sco);
            }
        }
        if (ger.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.ger);
            }
        }
        if (tur.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.tur);
            }
        }
        if (mex.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.mex);
            }
        }
        if (mar.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.mar);
            }
        }
        if (ukr.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.ukr);
            }
        }
        if (eng.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else {
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.eng);
            }
        }
        if (result.equals("")) result = "Выберите кухню";

        rest_cuisine_button.setText(result);
    }

    private void FeaturesReset(Button features_button){
        String result = "";
        int count = 0;
        if (features_delivery.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                result += getString(R.string.delivery);
            }
        }
        if (features_banquet.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.banquet);
            }
        }
        if (features_roundtheclock.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.roundtheclock);
            }
        }
        if (features_panorama.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.panorama);
            }
        }
        if (features_veranda.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.veranda);
            }
        }
        if (features_wifi.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.wifi);
            }
        }
        if (features_livemusic.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.livemusic);
            }
        }
        if (features_coffetogo.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.coffeetogo);
            }
        }
        if (features_breakfast.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.breakfast);
            }
        }
        if (features_kidsanimation.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.kidsanimation);
            }
        }
        if (features_businesslunch.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.businesslunch);
            }
        }
        if (features_hookah.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.hookah);
            }
        }
        if (features_karaoke.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.karaoke);
            }
        }
        if (features_livesports.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.livesports);
            }
        }
        if (features_homecuisine.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if (!result.equals("")) result += ", ";
                result += getString(R.string.homecuisine);
            }
        }
        if (result.equals("")) result = "Особенности заведения";

        features_button.setText(result);

    }

}
