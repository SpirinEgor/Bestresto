package com.bestresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DatabaseWork;
import com.bestresto.Database.QueryConditions;
import com.bestresto.Dish.DescriptionDishFragment;
import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.DescriptionRestaurantFragment;
import com.bestresto.Restaurant.RestaurantManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Вдадимир on 02.06.2017.
 * swap description of dishes
 */

public class PagerActivity extends FragmentActivity {

    private final String TAG = "PagerActivity";

    private ViewPager viewPager;
    private ArrayList<HashMap<String, Object>> elements;
    private QueryConditions queryConditions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        queryConditions = getIntent().getParcelableExtra(QueryConditions.TAG);
        elements = DatabaseWork.makeData(queryConditions);

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {

                HashMap<String, Object> element = elements.get(position);

                if (queryConditions.getTableName().compareTo(DatabaseContract.DishesColumns.TABLE_NAME) == 0)
                    return DescriptionDishFragment.newInstance
                        (element.get(DatabaseContract.DishesColumns.CAPTION).toString());

                return DescriptionRestaurantFragment.newInstance
                        (element.get(DatabaseContract.RestaurantsColumns.CAPTION).toString());
            }

            @Override
            public int getCount() {
                return elements.size();
            }
        });

        viewPager.setCurrentItem(getIntent().getIntExtra(DatabaseContract.DishesColumns.INDEXID, 0));

        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pager_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home :
                Intent intent = new Intent();
                intent.putExtra("position", viewPager.getCurrentItem());
                setResult(RESULT_OK, intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
