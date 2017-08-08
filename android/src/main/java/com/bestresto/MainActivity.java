package com.bestresto;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bestresto.Dish.DishesFragment;
import com.bestresto.Restaurant.RestaurantsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.container, mainFragment);
        fTrans.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_main) {

            MainFragment mainFragment = new MainFragment();
            fTrans.replace(R.id.container, mainFragment);
        }
        else if (id == R.id.nav_dishes) {

            DishesFragment dishesFragment = new DishesFragment();
            fTrans.replace(R.id.container, dishesFragment, "DISH_FRAGMENT_TAG");
            fTrans.addToBackStack(null);

        } else if (id == R.id.nav_restaurants) {

            RestaurantsFragment restsFragment = new RestaurantsFragment();
            fTrans.replace(R.id.container, restsFragment, "RESTAURANT_FRAGMENT_TAG");
            fTrans.addToBackStack(null);

        } else if (id == R.id.nav_redaction) {
        } else if (id == R.id.nav_blog) {
        } else if (id == R.id.nav_contacts) {
        }

        fTrans.commit();
        //Mark menu's item
        item.setChecked(true);
        if (id == R.id.nav_main)
            setTitle(R.string.app_name);
        else
            setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
