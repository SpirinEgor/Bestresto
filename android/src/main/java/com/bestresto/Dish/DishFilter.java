package com.bestresto.Dish;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
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
    CheckBox salads, breakfasts, hotstarters, garnish, soups, starters, grill, maincourses, desserts;

    ArrayList<Pair<CheckBox, String>> cuisines, dishtypes;
    Button dish_type_button, dish_cuisine_button;
    CheckBox chi, arm, ara, eas, gre, geo, ind, spa, kor, veg, ita, rus, jew, eur, aut, aze, asi, usa, jap, pan, fra, ady, kav, med, per,
            tur, ska, irl, sco, ger, fus, mex, mar, ukr, eng;

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
                    CuisineReset(dish_cuisine_button);
                }
            });
        }




        ///////////////////////////////////////////////////////////////////////

        dishtypes = new ArrayList<>();
        breakfasts = (CheckBox)view.findViewById(R.id.breakfasts);
        dishtypes.add(Pair.create(breakfasts, getString(R.string.breakfasts)));
        hotstarters = (CheckBox)view.findViewById(R.id.hotstarters);
        dishtypes.add(Pair.create(hotstarters, getString(R.string.hotstarters)));
        garnish = (CheckBox)view.findViewById(R.id.garnish);
        dishtypes.add(Pair.create(garnish, getString(R.string.garnish)));
        soups = (CheckBox)view.findViewById(R.id.soups);
        dishtypes.add(Pair.create(soups, getString(R.string.soups)));
        starters = (CheckBox)view.findViewById(R.id.starters);
        dishtypes.add(Pair.create(starters, getString(R.string.starters)));
        salads = (CheckBox)view.findViewById(R.id.salads);
        dishtypes.add(Pair.create(salads, getString(R.string.salads)));
        desserts = (CheckBox)view.findViewById(R.id.desserts);
        dishtypes.add(Pair.create(desserts, getString(R.string.desserts)));
        maincourses = (CheckBox)view.findViewById(R.id.maincourses);
        dishtypes.add(Pair.create(maincourses, getString(R.string.maincourses)));
        grill = (CheckBox)view.findViewById(R.id.grill);
        dishtypes.add(Pair.create(grill, getString(R.string.grill)));

        Button b2 = (Button)view.findViewById(R.id.filter_button_find);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = dish_title.getText().toString();
                int price;
                try {
                    price = Integer.parseInt(dish_price.getText().toString());
                }
                catch (NumberFormatException e){
                    price=-1;
                }

                ArrayList<String> cuisine_params = new ArrayList<String>();
                for (Pair<CheckBox, String> cuisine:cuisines) {
                    if (cuisine.first.isChecked()) cuisine_params.add(cuisine.second);
                }

                ArrayList<String> dish_params = new ArrayList<String>();
                if (salads.isChecked()){
                    dish_params.add(getString(R.string.salads));
                    CheckBox salads_vegetables = (CheckBox)view.findViewById(R.id.salads_vegetables);
                    if (salads_vegetables.isChecked()) dish_params.add(getString(R.string.vegetables));
                    CheckBox salads_ofmeat = (CheckBox)view.findViewById(R.id.salads_ofmeat);
                    if (salads_ofmeat.isChecked()) dish_params.add(getString(R.string.ofmeat));
                    CheckBox salads_fish = (CheckBox)view.findViewById(R.id.salads_fish);
                    if (salads_fish.isChecked()) dish_params.add(getString(R.string.fish));
                    CheckBox salads_bird = (CheckBox)view.findViewById(R.id.salads_bird);
                    if (salads_bird.isChecked()) dish_params.add(getString(R.string.bird));
                    CheckBox salads_seaproducts = (CheckBox)view.findViewById(R.id.salads_seaproducts);
                    if (salads_seaproducts.isChecked()) dish_params.add(getString(R.string.seaproducts));
                }

                if (starters.isChecked()){
                    dish_params.add(getString(R.string.starters));
                    CheckBox starters_other = (CheckBox)view.findViewById(R.id.starters_other);
                    if (starters_other.isChecked()) dish_params.add(getString(R.string.other));
                    CheckBox starters_cheese = (CheckBox)view.findViewById(R.id.starters_cheese);
                    if (starters_cheese.isChecked()) dish_params.add(getString(R.string.cheese));
                    CheckBox starters_meat = (CheckBox)view.findViewById(R.id.starters_meat);
                    if (starters_meat.isChecked()) dish_params.add(getString(R.string.meat));
                }

                if (soups.isChecked()){
                    dish_params.add(getString(R.string.soups));
                    CheckBox soups_fish = (CheckBox)view.findViewById(R.id.soups_fish);
                    if (soups_fish.isChecked()) dish_params.add(getString(R.string.fish));
                    CheckBox soups_vegetables = (CheckBox)view.findViewById(R.id.soups_vegetables);
                    if (soups_vegetables.isChecked()) dish_params.add(getString(R.string.vegetables));
                    CheckBox soups_ofmeat = (CheckBox)view.findViewById(R.id.soups_ofmeat);
                    if (soups_ofmeat.isChecked()) dish_params.add(getString(R.string.ofmeat));
                    CheckBox soups_seaproducts = (CheckBox)view.findViewById(R.id.soups_seaproducts);
                    if (soups_seaproducts.isChecked()) dish_params.add(getString(R.string.seaproducts));
                }

                if (maincourses.isChecked()){
                    dish_params.add(getString(R.string.maincourses));
                    CheckBox maincourses_meat = (CheckBox)view.findViewById(R.id.maincourses_meat);
                    if (maincourses_meat.isChecked()) dish_params.add(getString(R.string.meat));
                    CheckBox maincourses_seaproducts = (CheckBox)view.findViewById(R.id.maincourses_seaproducts);
                    if (maincourses_seaproducts.isChecked()) dish_params.add(getString(R.string.seaproducts));
                    CheckBox maincourses_bird = (CheckBox)view.findViewById(R.id.maincourses_bird);
                    if (maincourses_bird.isChecked()) dish_params.add(getString(R.string.bird));
                    CheckBox maincourses_fish = (CheckBox)view.findViewById(R.id.maincourses_fish);
                    if (maincourses_fish.isChecked()) dish_params.add(getString(R.string.fish));

                }

                if (grill.isChecked()){
                    dish_params.add(getString(R.string.grill));
                    CheckBox grill_meat = (CheckBox)view.findViewById(R.id.grill_meat);
                    if (grill_meat.isChecked()) dish_params.add(getString(R.string.meat));
                    CheckBox grill_fish = (CheckBox)view.findViewById(R.id.grill_fish);
                    if (grill_fish.isChecked()) dish_params.add(getString(R.string.fish));
                    CheckBox grill_seaproducts = (CheckBox)view.findViewById(R.id.grill_seaproducts);
                    if (grill_seaproducts.isChecked()) dish_params.add(getString(R.string.seaproducts));
                    CheckBox grill_bird = (CheckBox)view.findViewById(R.id.grill_bird);
                    if (grill_bird.isChecked()) dish_params.add(getString(R.string.bird));
                }

                if (desserts.isChecked()){
                    dish_params.add(getString(R.string.desserts));
                    CheckBox desserts_jelly = (CheckBox)view.findViewById(R.id.desserts_jelly);
                    if (desserts_jelly.isChecked()) dish_params.add(getString(R.string.jelly));
                    CheckBox desserts_icecream = (CheckBox)view.findViewById(R.id.desserts_icecream);
                    if (desserts_icecream.isChecked()) dish_params.add(getString(R.string.icecream));
                    CheckBox desserts_cakes = (CheckBox)view.findViewById(R.id.desserts_cakes);
                    if (desserts_cakes.isChecked()) dish_params.add(getString(R.string.cakes));
                    CheckBox desserts_others = (CheckBox)view.findViewById(R.id.desserts_other);
                    if (desserts_others.isChecked()) dish_params.add(getString(R.string.other));

                }

                if (breakfasts.isChecked()) dish_params.add(getString(R.string.breakfasts));
                if (hotstarters.isChecked()) dish_params.add(getString(R.string.hotstarters));
                if (garnish.isChecked()) dish_params.add(getString(R.string.garnish));

                mCallback.onFilterSet(title, price, cuisine_params, dish_params);
            }
        });


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

    private void TypeReset(Button dish_type_button){
        String result = "";
        int count = 0;
        for (Pair<CheckBox, String> dishtype:dishtypes) {
            if (dishtype.first.isChecked()){
                if (count>=5){
                    if (!result.endsWith(", ..."))result += ", ...";
                }
                else{
                    count++;
                    if(!result.equals("")) result +=", ";
                    result += dishtype.second;
                }
            }

        }
        if (result.equals("")) result="Тип блюда";
        dish_type_button.setText(result);
    }
}