package com.bestresto.Dish;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.bestresto.Restaurant.RestaurantFilterDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergey on 31.07.17.
 */

public class DishFilterDialog extends DialogFragment {
    FilterListener mCallback;
    Context parentContext;
    CheckBox salads, breakfasts, hotstarters, garnish, soups, starters, grill, maincourses, desserts;

    ArrayList<Pair<CheckBox, String>> cuisines, dishtypes;
    Button dish_type_button, dish_cuisine_button;

    ScrollView dish_cuisine_view, dish_type_view;
    EditText dish_title;
    EditText dish_price;
    LinearLayout dish_cuisine_container;

    public void AttachFragment(Fragment fragment, Context context) {
        try {
            mCallback = (FilterListener) fragment;
            parentContext = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString()
                    + " must implement FilterListener");
        }

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dish_filter, null);


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
        ////////////////////////////////////////////////////////////////////////////////
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
                        DishFilterDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }

    private void SetCuisineCheckboxes(View view){
        cuisines = new ArrayList<>();
        dish_cuisine_container = (LinearLayout)view.findViewById(R.id.dish_cuisine_container);

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
                    CuisineReset(dish_cuisine_button);
                }
            });
            cuisines.add(Pair.create(cb, caption.get(DatabaseContract.KitchenTypesColumns.CAPTION).toString()));
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

    private Bundle CollectUserFilterSettings(){
        Bundle bundle = new Bundle();
        String caption = dish_title.getText().toString();
        bundle.putString("dish_caption", caption);
        int price;
        try {
            price = Integer.parseInt(dish_price.getText().toString());
        }
        catch (NumberFormatException e){
            price=-1;
        }
        bundle.putInt("dish_price", price);
        ArrayList<String> cuisine_params = new ArrayList<String>();
        for (Pair<CheckBox, String> cuisine:cuisines) {
            if (cuisine.first.isChecked()) cuisine_params.add(cuisine.second);
        }
        bundle.putStringArrayList("kitchen_params", cuisine_params);

        return bundle;
    }
}
