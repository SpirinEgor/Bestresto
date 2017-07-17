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

        lv = (ListView) view.findViewById(R.id.listDishes);

        Bundle extBundle = this.getArguments();
        if (extBundle != null){
            ArrayList<String> quisine_params = extBundle.getStringArrayList("quisine_params");
            ArrayList<String> dish_params = extBundle.getStringArrayList("dish_params");
            String dishtitle = extBundle.getString("dishtitle");
            int dishprice = extBundle.getInt("price");
            lv.setAdapter(new DishManager().getFilteredAdapter(view.getContext(), dishtitle));
        }
        else{
            lv.setAdapter(new DishManager().getAdapter(view.getContext()));
        }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        int position = data.getIntExtra("position", 0);
        lv.smoothScrollToPosition(position);
    }

    private String CuisineParamsDecode(ArrayList<String> cuisine_params){
        String result="";
        for (String current:
             cuisine_params) {
            if (current.equals(getString(R.string.kor))) result += "18,";
            if (current.equals(getString(R.string.arm))) result += "10,";
            if (current.equals(getString(R.string.ara))) result += "9,";
            if (current.equals(getString(R.string.eas))) result += "12,";
            if (current.equals(getString(R.string.gre))) result += "13,";
            if (current.equals(getString(R.string.geo))) result += "14,";
            if (current.equals(getString(R.string.ind))) result += "15,";
            if (current.equals(getString(R.string.spa))) result += "16,";
            if (current.equals(getString(R.string.chi))) result += "17,";
            if (current.equals(getString(R.string.veg))) result += "11,";
            if (current.equals(getString(R.string.ita))) result += "1,";
            if (current.equals(getString(R.string.rus))) result += "2,";
            if (current.equals(getString(R.string.jew))) result += "3,";
            if (current.equals(getString(R.string.eur))) result += "4,";
            if (current.equals(getString(R.string.aut))) result += "5,";
            if (current.equals(getString(R.string.aze))) result += "6,";
            if (current.equals(getString(R.string.asi))) result += "7,";
            if (current.equals(getString(R.string.usa))) result += "8,";
            if (current.equals(getString(R.string.jap))) result += "19,";
            if (current.equals(getString(R.string.pan))) result += "20,";
            if (current.equals(getString(R.string.fra))) result += "21,";
            if (current.equals(getString(R.string.ady))) result += "22,";
            if (current.equals(getString(R.string.kav))) result += "23,";
            if (current.equals(getString(R.string.med))) result += "24,";
            if (current.equals(getString(R.string.per))) result += "25,";
            if (current.equals(getString(R.string.tur))) result += "26,";
            if (current.equals(getString(R.string.ska))) result += "27,";
            if (current.equals(getString(R.string.irl))) result += "28,";
            if (current.equals(getString(R.string.sco))) result += "29,";
            if (current.equals(getString(R.string.ger))) result += "30,";
            if (current.equals(getString(R.string.fus))) result += "31,";
            if (current.equals(getString(R.string.mex))) result += "32,";
            if (current.equals(getString(R.string.mar))) result += "33,";
            if (current.equals(getString(R.string.ukr))) result += "34,";
            if (current.equals(getString(R.string.eng))) result += "35,";

        }
        return result;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Блюда");
    }

}
