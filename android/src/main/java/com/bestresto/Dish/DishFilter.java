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
import com.bestresto.Types.KitchenTypesManager;

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

    ScrollView dish_cuisine_view, dish_type_view;
    EditText dish_title;
    EditText dish_price;
    LinearLayout dish_cuisine_container;

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

        SetCuisineCheckboxes(view);

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
                String caption = dish_title.getText().toString();
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

                mCallback.onFilterSet(caption, price, cuisine_params, dish_params);
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

    private void SetCuisineCheckboxes(View view){
        cuisines = new ArrayList<>();
        ArrayList<String> captions = (new KitchenTypesManager()).make_data_cuisines_sorted(view.getContext());
        for (String caption : captions){
            CheckBox cb = new CheckBox(view.getContext());
            cb.setText(caption);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CuisineReset(dish_cuisine_button);
                }
            });
            cuisines.add(Pair.create(cb, caption));
            dish_cuisine_container = (LinearLayout)view.findViewById(R.id.dish_cuisine_container);
            dish_cuisine_container.addView(cb);
        }
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