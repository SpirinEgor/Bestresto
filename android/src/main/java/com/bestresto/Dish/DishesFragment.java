package com.bestresto.Dish;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bestresto.PagerActivity;
import com.bestresto.R;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.data.DatabaseContract;
import com.bestresto.data.QueryConditions;

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
        queryConditions.otherConditions.put(DatabaseContract.DishesColumns.ACTIVE, "1");
        // getting bundle if exist
        // open, then close db
        Bundle bundle = getArguments();
        if (!(bundle == null)){
            String caption = bundle.getString("dish_caption");
            if (!caption.equals("")) queryConditions.otherConditions.put(DatabaseContract.DishesColumns.CAPTION, caption);

            Integer price = bundle.getInt("dish_price");
            if (price != -1) queryConditions.otherConditions.put(DatabaseContract.DishesColumns.PRICE, price.toString());

            ArrayList<String> dish_cuisines = bundle.getStringArrayList("cuisine_params");
            if (dish_cuisines.size() != 0){
                KitchenTypesManager ktm = new KitchenTypesManager();
                ktm.openDb(view.getContext());
                queryConditions.kitchenConditions = ktm.getKitchenNumbersByNames(dish_cuisines);
                ktm.closeDb();
            }
        }
        queryConditions.orderByConditions.put(DatabaseContract.DishesColumns.SORT, "ASC");
        String[] columns = {
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE
        };
        DishManager dishManager = new DishManager();
        dishManager.openDb(view.getContext());
        lv.setAdapter(dishManager.getAdapterWithData(view.getContext(), queryConditions, columns));
        dishManager.closeDb();

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
