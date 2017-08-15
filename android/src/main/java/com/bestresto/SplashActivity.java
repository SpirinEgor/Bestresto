package com.bestresto;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bestresto.Database.DatabaseWork;
import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DatabaseWork(this);
        long startTime = System.currentTimeMillis();

        if (isOnline()) {
            getData();
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
        }
        long timeSpent = System.currentTimeMillis() - startTime;
        Log.d("time", String.valueOf(timeSpent));
        Intent intent = new Intent(this, MainActivity.class);   //Start MainActivity
        startActivity(intent);
        finish();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void getData() {
        final String dishesAllRequest = "foods/foods.php?all";
        final String restaurantsAllRequest = "rest/rest.php?all";
        final String kitchenTypesRequest = "types/types.php?kitchenTypes";
        final String restaurantTypesRequest = "types/types.php?restTypes";
        final String[] requestsTypes = {
                kitchenTypesRequest,
                restaurantTypesRequest,
        };
        final ManagerInterface[] managerTypes = {
                new KitchenTypesManager(),
                new RestaurantTypesManager(),
        };
        final String[] requestsData = {
                dishesAllRequest,
                restaurantsAllRequest,
        };
        final ManagerInterface[] managerData = {
                new DishManager(),
                new RestaurantManager(),
        };
        try{
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < requestsTypes.length; ++i){
                threads.add(new RequestThread(requestsTypes[i], managerTypes[i]));
            }
            for (Thread thread: threads){
                thread.start();
            }
            for (Thread thread: threads){
                thread.join();
            }
            threads = new ArrayList<>();
            for (int i = 0; i < requestsData.length; ++i){
                threads.add(new RequestThread(requestsData[i], managerData[i]));
            }
            for (Thread thread: threads){
                thread.start();
            }
            for (Thread thread: threads){
                thread.join();
            }
        }
        catch (Exception ignored){}
    }
}
