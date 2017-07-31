package com.bestresto.Restaurant;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DatabaseWork;
import com.bestresto.Database.QueryConditions;
import com.bestresto.FilterListener;
import com.bestresto.PagerActivity;
import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantsFragment extends Fragment
                                    implements FilterListener{

    FloatingActionButton fab;
    ListView lv;
    Fragment currentFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rest_fragment_all, container, false);
        currentFragment = this;
        lv = (ListView) view.findViewById(R.id.listRestaurants);
        SetNewData(null, view.getContext());
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantFilterDialog restaurantFilterDialog = new RestaurantFilterDialog();
                restaurantFilterDialog.AttachFragment(currentFragment, view.getContext());
                restaurantFilterDialog.show(getFragmentManager(), "tag");

            }

        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        int position = data.getIntExtra("position", 0);
        lv.smoothScrollToPosition(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Рестораны");
    }

    @Override
    public void SetNewData(Bundle bundle, Context context) {
        QueryConditions queryConditions= new QueryConditions();
        queryConditions.setTableName(DatabaseContract.RestaurantsColumns.TABLE_NAME);
        queryConditions.setColumns(new String[]{
                DatabaseContract.RestaurantsColumns.CAPTION,
                DatabaseContract.RestaurantsColumns.LOGO,
                DatabaseContract.RestaurantsColumns.REITING,
                DatabaseContract.RestaurantsColumns.KITCHEN,
                DatabaseContract.RestaurantsColumns.ADDRESS,
        });
        String whenCondition = "";
        if (bundle != null){
            String caption = bundle.getString("rest_caption");
            if (caption != null && !caption.equals("")) {
                whenCondition += DatabaseContract.DishesColumns.CAPTION + " = " + DatabaseWork.prepare(caption);
            }

            ArrayList<String> dish_cuisines = bundle.getStringArrayList("kitchen_params");
            if (dish_cuisines != null && dish_cuisines.size() != 0) {
                ArrayList<String> primeList = KitchenTypesManager.getKitchenNumbersByNames(dish_cuisines);
                if (!whenCondition.equals("")){
                    whenCondition += " AND (";
                }
                else{
                    whenCondition += "(";
                }
                for (String prime : primeList) {
                    whenCondition += DatabaseContract.DishesColumns.KITCHEN + " % " + prime + " = 0";
                    if (!prime.equals(primeList.get(primeList.size() - 1))) {
                        whenCondition += " OR ";
                    }
                }
                whenCondition += ")";
            }
            queryConditions.setWhereCondition(whenCondition);
        }

        lv.setAdapter(RestaurantManager.getAdapterWithData(context, queryConditions));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, Object> rest = (HashMap) lv.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), PagerActivity.class);

                i.putExtra(DatabaseContract.DishesColumns.INDEXID, position);
                i.putExtra(PagerActivity.KEY_TYPE_LIST, PagerActivity.RESTAURANTS_TYPE);
                i.putExtra(PagerActivity.KEY_DISH_OR_REST, PagerActivity.RESTAURANT_CONSTANT);

                startActivityForResult(i, 1);
            }
        });
    }
}
