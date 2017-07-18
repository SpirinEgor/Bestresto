package com.bestresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.bestresto.Dish.DescriptionDishFragment;
import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.DescriptionRestaurantFragment;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.data.DatabaseContract;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Вдадимир on 02.06.2017.
 * swap description of dishes
 */

public class PagerActivity extends FragmentActivity {

    public static final String KEY_TYPE_LIST = "type_list";
    public static final String KEY_DISH_OR_REST = "dish_or_rest";

    public static final int ALL_DISHES_TYPE = 1;
    public static final int FIRST_DISHES_TYPE = 2;
    public static final int RESTAURANTS_TYPE = 3;


    public static final int DISH_CONSTANT = 1111;
    public static final int RESTAURANT_CONSTANT = 2222;



    ViewPager viewPager;
    ArrayList<HashMap<String, Object>> elements;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        int key = getIntent().getIntExtra(KEY_TYPE_LIST, ALL_DISHES_TYPE);
        final int dishOrRest = getIntent().getIntExtra(KEY_DISH_OR_REST, DISH_CONSTANT);

        DishManager dishManager = new DishManager();
        RestaurantManager restaurantManager = new RestaurantManager();
        switch (key) {
            case ALL_DISHES_TYPE:
                dishManager.openDb(this);
                elements = dishManager.make_data_all();
                dishManager.closeDb();
                break;
            case FIRST_DISHES_TYPE:
                dishManager.openDb(this);
                elements = dishManager.make_data_first();
                dishManager.closeDb();
                break;
            case RESTAURANTS_TYPE:
                restaurantManager.openDb(this);
                elements = restaurantManager.make_data_all();
                restaurantManager.closeDb();
                break;
            default:
                elements = new ArrayList<>();
        }

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {

                HashMap<String, Object> element = elements.get(position);

                if (dishOrRest == DISH_CONSTANT)
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
