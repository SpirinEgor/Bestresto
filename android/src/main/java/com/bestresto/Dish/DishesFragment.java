package com.bestresto.Dish;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bestresto.PagerActivity;
import com.bestresto.R;
import com.bestresto.data.DatabaseContract;

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


        Bundle extBundle = this.getArguments();
        if (extBundle != null){
            ArrayList<String> quisine_params = extBundle.getStringArrayList("quisine_params");
            ArrayList<String> dish_params = extBundle.getStringArrayList("dish_params");
            String dishtitle = extBundle.getString("dishtitle");
            int dishprice = extBundle.getInt("price");
            Log.d("TAG", quisine_params.toString());
            Log.d("TAG", dish_params.toString());
            Log.d("TAG", dishtitle);
        }

        lv = (ListView) view.findViewById(R.id.listDishes);
        lv.setAdapter(new DishManager().getAdapter(view.getContext()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, Object> dish = (HashMap) lv.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), PagerActivity.class);
                i.putExtra(DatabaseContract.DishesColumns.INDEXID,
                        position);
                i.putExtra(PagerActivity.KEY_TYPE_LIST, PagerActivity.ALL_DISHES_TYPE);
                startActivity(i);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        int position = data.getIntExtra("position", 0);
        lv.smoothScrollToPosition(position);
    }
}
