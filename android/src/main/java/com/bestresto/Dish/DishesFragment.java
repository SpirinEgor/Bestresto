package com.bestresto.Dish;

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
import com.bestresto.PagerActivity;
import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by egor on 31.05.17.
 * make adapter of all dishes
 */

public class DishesFragment extends Fragment {

    FloatingActionButton fab;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dish_fragment_all, container, false);

        lv = (ListView) view.findViewById(R.id.listDishes);

        QueryConditions queryConditions = new QueryConditions();
        // getting bundle if exist
        Bundle bundle = getArguments();
        String whenCondition = DatabaseContract.DishesColumns.ACTIVE + " = 1";
        if (!(bundle == null)){
            String caption = bundle.getString("dish_caption");
            if (caption != null && !caption.equals("")) {
                whenCondition += " AND " + DatabaseContract.DishesColumns.CAPTION + " = " + DatabaseWork.prepare(caption);
            }
            Integer price = bundle.getInt("dish_price");
            if (price != -1) {
                whenCondition += " AND " + DatabaseContract.DishesColumns.PRICE + " <= " + price;
            }
            ArrayList<String> dish_cuisines = bundle.getStringArrayList("cuisine_params");
            if (dish_cuisines != null && dish_cuisines.size() != 0){
                KitchenTypesManager ktm = new KitchenTypesManager();
                ArrayList<Integer> primeList = ktm.getKitchenNumbersByNames(dish_cuisines);
                whenCondition += " AND (";
                for (int prime: primeList) {
                    whenCondition += DatabaseContract.DishesColumns.KITCHEN + " % " + prime + " = 0";
                    if (prime != primeList.get(primeList.size() - 1)) {
                        whenCondition += " OR ";
                    }
                }
                whenCondition += ")";
            }
        }
        queryConditions.setWhereCondition(whenCondition);
        queryConditions.setOrderByCondition(DatabaseContract.DishesColumns.CREATEDATE + " DESC");
        queryConditions.setColumns(new String[]{
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE
        });
        queryConditions.setTableName(DatabaseContract.DishesColumns.TABLE_NAME);
        lv.setAdapter(DishManager.getAdapterWithData(view.getContext(), queryConditions));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, Object> dish = (HashMap) lv.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), PagerActivity.class);
                i.putExtra(DatabaseContract.DishesColumns.INDEXID,
                        position);
                i.putExtra(PagerActivity.KEY_TYPE_LIST, PagerActivity.ALL_DISHES_TYPE);
                startActivityForResult(i, 1);
            }
        });
        final FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DishFilter filter = new DishFilter();
                fTrans.replace(R.id.container, filter);
                fTrans.addToBackStack(null);
                fTrans.commit();
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
        getActivity().setTitle("Блюда");
    }

}
