package com.bestresto.Dish;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bestresto.R;

import java.util.ArrayList;

/**
 * Created by sergey on 02.06.17.
 * Filter
 */

public class DishFilter extends Fragment {
    FilterListener mCallback;
    CheckBox salads, breakfasts, hotstarters, garnish;
    CheckBox soups;
    CheckBox starters;
    CheckBox grill;
    CheckBox maincourses;
    CheckBox desserts;
    Button dish_type_button, dish_cuisine_button;
    CheckBox chi, arm, ara, wes, gre, geo, ind, spa, kor, veg, ita, rus, jew, eur, aut, aze, asi, usa, jap, pan, fra, ady, kav, med, per,
            tur, ska, irl, sco, ger, fus, mex, mar;

    ScrollView dish_cuisine_view, dish_type_view;
    EditText dish_title;
    EditText dish_price;

    public interface FilterListener{
        public void onFilterSet(String dishtitle, int dishprice, ArrayList<String> cuisine_params, ArrayList<String> dish_params);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (FilterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FilterListener");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dish_filter, container, false);

        dish_title = (EditText)view.findViewById(R.id.dish_title);
        dish_price = (EditText)view.findViewById(R.id.dish_price);
        dish_cuisine_view = (ScrollView)view.findViewById(R.id.dish_cuisine_view);
        dish_type_view = (ScrollView)view.findViewById(R.id.dish_type_view); 
        ///////////////////////////////////////////////////////////////////////////
        dish_cuisine_button = (Button)view.findViewById(R.id.dish_cuisine_button);
        dish_cuisine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dish_cuisine_view.getVisibility() == View.GONE){
                    dish_cuisine_view.setVisibility(View.VISIBLE);
                    dish_type_view.setVisibility(View.GONE);
                }
                else {
                    dish_cuisine_view.setVisibility(View.GONE);
                }
            }
        });
        
        dish_type_button = (Button)view.findViewById(R.id.dish_type_button);
        dish_type_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dish_type_view.getVisibility() == View.GONE){
                    dish_cuisine_view.setVisibility(View.GONE);
                    dish_type_view.setVisibility(View.VISIBLE);
                }
                else {
                    dish_type_view.setVisibility(View.GONE);
                }
            }
        });
        

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

        //OnCheckedListeners for cuisine section
        chi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        arm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ara.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        wes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        gre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        geo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        spa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        kor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        veg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        rus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        jew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        eur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        aut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        aze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        asi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        usa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        jap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        pan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        fra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ady.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        kav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        med.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        per.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        tur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ska.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        irl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        sco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        fus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        mex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        mar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CuisineReset(dish_cuisine_button);
            }
        });
        ///////////////////////////////////////////////////////////////////////


        breakfasts = (CheckBox)view.findViewById(R.id.breakfasts);
        hotstarters = (CheckBox)view.findViewById(R.id.hotstarters);
        garnish = (CheckBox)view.findViewById(R.id.garnish);
        soups = (CheckBox)view.findViewById(R.id.soups);
        starters = (CheckBox)view.findViewById(R.id.starters);
        salads = (CheckBox)view.findViewById(R.id.salads);
        desserts = (CheckBox)view.findViewById(R.id.desserts);
        maincourses = (CheckBox)view.findViewById(R.id.maincourses);
        grill = (CheckBox)view.findViewById(R.id.grill);

        Button b2 = (Button)view.findViewById(R.id.filter_button_find);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = dish_title.getText().toString();
//                int price;
//                try {
//                   price = Integer.parseInt(dish_price.getText().toString());
//                }
//                catch (NumberFormatException e){
//                    price=-1;
//                }
//                ArrayList<String> cuisine_params = new ArrayList<String>();
//                if (chi.isChecked()) cuisine_params.add(getString(R.string.chi));
//                if (arm.isChecked()) cuisine_params.add(getString(R.string.arm));
//                if (ara.isChecked()) cuisine_params.add(getString(R.string.ara));
//                if (wes.isChecked()) cuisine_params.add(getString(R.string.wes));
//                if (gre.isChecked()) cuisine_params.add(getString(R.string.gre));
//                if (geo.isChecked()) cuisine_params.add(getString(R.string.geo));
//                if (ind.isChecked()) cuisine_params.add(getString(R.string.ind));
//                if (spa.isChecked()) cuisine_params.add(getString(R.string.spa));
//                if (kor.isChecked()) cuisine_params.add(getString(R.string.kor));
//                if (veg.isChecked()) cuisine_params.add(getString(R.string.veg));
//                if (ita.isChecked()) cuisine_params.add(getString(R.string.ita));
//                if (rus.isChecked()) cuisine_params.add(getString(R.string.rus));
//                if (jew.isChecked()) cuisine_params.add(getString(R.string.jew));
//                if (eur.isChecked()) cuisine_params.add(getString(R.string.eur));
//                if (aut.isChecked()) cuisine_params.add(getString(R.string.aut));
//                if (aze.isChecked()) cuisine_params.add(getString(R.string.aze));
//                if (asi.isChecked()) cuisine_params.add(getString(R.string.asi));
//                if (usa.isChecked()) cuisine_params.add(getString(R.string.usa));
//                if (jap.isChecked()) cuisine_params.add(getString(R.string.jap));
//                if (pan.isChecked()) cuisine_params.add(getString(R.string.pan));
//                if (fra.isChecked()) cuisine_params.add(getString(R.string.fra));
//                if (ady.isChecked()) cuisine_params.add(getString(R.string.ady));
//                if (kav.isChecked()) cuisine_params.add(getString(R.string.kav));
//                if (med.isChecked()) cuisine_params.add(getString(R.string.med));
//                if (per.isChecked()) cuisine_params.add(getString(R.string.per));
//                if (tur.isChecked()) cuisine_params.add(getString(R.string.tur));
//                if (ska.isChecked()) cuisine_params.add(getString(R.string.ska));
//                if (irl.isChecked()) cuisine_params.add(getString(R.string.irl));
//                if (sco.isChecked()) cuisine_params.add(getString(R.string.sco));
//                if (ger.isChecked()) cuisine_params.add(getString(R.string.ger));
//                if (fus.isChecked()) cuisine_params.add(getString(R.string.fus));
//                if (mex.isChecked()) cuisine_params.add(getString(R.string.mex));
//                if (mar.isChecked()) cuisine_params.add(getString(R.string.mar));
//
//                ArrayList<String> dish_params = new ArrayList<String>();
//                if (salads.isChecked()){
//                    CheckBox salads_vegetables = (CheckBox)view.findViewById(R.id.salads_vegetables);
//                    CheckBox salads_ofmeat = (CheckBox)view.findViewById(R.id.salads_ofmeat);
//                    CheckBox salads_fish = (CheckBox)view.findViewById(R.id.salads_fish);
//                    CheckBox salads_bird = (CheckBox)view.findViewById(R.id.salads_bird);
//                    CheckBox salads_seaproducts = (CheckBox)view.findViewById(R.id.salads_seaproducts);
//
//                }
//
//                if (starters.isChecked()){
//                    CheckBox starters_other = (CheckBox)view.findViewById(R.id.starters_other);
//                    CheckBox starters_cheese = (CheckBox)view.findViewById(R.id.starters_cheese);
//                    CheckBox starters_meat = (CheckBox)view.findViewById(R.id.starters_meat);
//                }
//
//                if (soups.isChecked()){
//                    CheckBox soups_fish = (CheckBox)view.findViewById(R.id.soups_fish);
//                    CheckBox soups_vegetables = (CheckBox)view.findViewById(R.id.soups_vegetables);
//                    CheckBox soups_ofmeat = (CheckBox)view.findViewById(R.id.soups_ofmeat);
//                    CheckBox soups_seaproducts = (CheckBox)view.findViewById(R.id.soups_seaproducts);
//                }
//
//                if (maincourses.isChecked()){
//                    CheckBox maincourses_meat = (CheckBox)view.findViewById(R.id.maincourses_meat);
//                    CheckBox maincourses_seaproducts = (CheckBox)view.findViewById(R.id.maincourses_seaproducts);
//                    CheckBox maincourses_bird = (CheckBox)view.findViewById(R.id.maincourses_bird);
//                    CheckBox maincourses_fish = (CheckBox)view.findViewById(R.id.maincourses_fish);
//
//                }
//
//                if (grill.isChecked()){
//                    CheckBox grill_meat = (CheckBox)view.findViewById(R.id.grill_meat);
//                    CheckBox grill_fish = (CheckBox)view.findViewById(R.id.grill_fish);
//                    CheckBox grill_seaproducts = (CheckBox)view.findViewById(R.id.grill_seaproducts);
//                    CheckBox grill_bird = (CheckBox)view.findViewById(R.id.grill_bird);
//                }
//
//                if (desserts.isChecked()){
//                    CheckBox desserts_jelly = (CheckBox)view.findViewById(R.id.desserts_jelly);
//                    CheckBox desserts_icecream = (CheckBox)view.findViewById(R.id.desserts_icecream);
//                    CheckBox desserts_cakes = (CheckBox)view.findViewById(R.id.desserts_cakes);
//                    CheckBox desserts_others = (CheckBox)view.findViewById(R.id.desserts_other);
//
//                }
//
//
//                mCallback.onFilterSet(title, price, cuisine_params, dish_params);
//            }
//        });


        breakfasts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TypeReset(dish_type_button);
            }
        });

        hotstarters.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TypeReset(dish_type_button);
            }
        });

        garnish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TypeReset(dish_type_button);
            }
        });

        final LinearLayout salads_const = (LinearLayout) view.findViewById(R.id.salads_constituents);
        salads.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (salads.isChecked()){
                    salads_const.setVisibility(View.VISIBLE);
                }
                else {
                    CheckBox checkBox;
                    checkBox = (CheckBox) view.findViewById(R.id.salads_bird);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.salads_fish);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.salads_ofmeat);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.salads_seaproducts);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.salads_vegetables);
                    checkBox.setChecked(false);
                    salads_const.setVisibility(View.GONE);
                }
                TypeReset(dish_type_button);
            }
        });

        final LinearLayout soups_const = (LinearLayout) view.findViewById(R.id.soups_constituents);
        soups.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (soups.isChecked()){
                    soups_const.setVisibility(View.VISIBLE);
                }
                else {
                    CheckBox checkBox;
                    checkBox = (CheckBox) view.findViewById(R.id.soups_fish);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.soups_ofmeat);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.soups_seaproducts);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.soups_vegetables);
                    checkBox.setChecked(false);
                    soups_const.setVisibility(View.GONE);
                }
                TypeReset(dish_type_button);
            }
        });

        final LinearLayout starters_const = (LinearLayout) view.findViewById(R.id.starters_constituents);
        starters.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (starters.isChecked()){
                    starters_const.setVisibility(View.VISIBLE);
                }
                else {
                    CheckBox checkBox;
                    checkBox = (CheckBox) view.findViewById(R.id.starters_cheese);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.starters_meat);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.starters_other);
                    checkBox.setChecked(false);
                    starters_const.setVisibility(View.GONE);
                }
                TypeReset(dish_type_button);
            }
        });

        final LinearLayout maincourses_const = (LinearLayout) view.findViewById(R.id.maincourses_constituents);
        maincourses.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (maincourses.isChecked()){
                    maincourses_const.setVisibility(View.VISIBLE);
                }
                else {
                    CheckBox checkBox;
                    checkBox = (CheckBox) view.findViewById(R.id.maincourses_bird);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.maincourses_fish);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.maincourses_meat);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.maincourses_seaproducts);
                    checkBox.setChecked(false);
                    maincourses_const.setVisibility(View.GONE);
                }
                TypeReset(dish_type_button);
            }
        });

        final LinearLayout desserts_const = (LinearLayout) view.findViewById(R.id.desserts_constituents);
        desserts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (desserts.isChecked()){
                    desserts_const.setVisibility(View.VISIBLE);
                }
                else {
                    CheckBox checkBox;
                    checkBox = (CheckBox) view.findViewById(R.id.desserts_cakes);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.desserts_icecream);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.desserts_jelly);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.desserts_other);
                    checkBox.setChecked(false);
                    desserts_const.setVisibility(View.GONE);
                }
                TypeReset(dish_type_button);
            }
        });

        final LinearLayout grill_const = (LinearLayout) view.findViewById(R.id.grill_constituents);
        grill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (grill.isChecked()){
                    grill_const.setVisibility(View.VISIBLE);
                }
                else {
                    CheckBox checkBox;
                    checkBox = (CheckBox) view.findViewById(R.id.grill_bird);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.grill_seaproducts);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.grill_fish);
                    checkBox.setChecked(false);
                    checkBox = (CheckBox) view.findViewById(R.id.grill_meat);
                    checkBox.setChecked(false);
                    grill_const.setVisibility(View.GONE);
                }
                TypeReset(dish_type_button);
            }
        });


        return view;
    }

    private void CuisineReset(Button dish_cuisine_button){
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
        if (result.equals("")) result = "Выберите кухню";

        dish_cuisine_button.setText(result);
    }

    private void TypeReset(Button dish_type_button){
        String result = "";
        int count = 0;
        if (soups.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.soups);
            }
        }
        if (salads.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.salads);
            }
        }
        if (starters.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.starters);
            }
        }
        if (maincourses.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.maincourses);
            }
        }
        if (grill.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.grill);
            }
        }
        if (breakfasts.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.breakfasts);
            }
        }
        if (desserts.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.desserts);
            }
        }
        if (hotstarters.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.hotstarters);
            }
        }
        if (garnish.isChecked()){
            if ((count >= 5)){
                if (!result.endsWith(", ..."))result += ", ...";
            }
            else{
                count++;
                if(!result.equals("")) result +=", ";
                result += getString(R.string.garnish);
            }
        }
        if (result.equals("")) result="Тип блюда";
        dish_type_button.setText(result);
    }
}
