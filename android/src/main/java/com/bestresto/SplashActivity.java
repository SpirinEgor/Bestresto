package com.bestresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bestresto.Database.DatabaseWork;
import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;

import java.util.ArrayList;

/**
 * Created by sergey on 20.06.17.
 */

public class SplashActivity extends AppCompatActivity{

    ProgressBar pBar;
    TextView textView;
    final String server = "http://www.bestresto.ru/api/";

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        long startTime = System.currentTimeMillis();
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView)findViewById(R.id.textIndicator);

        new DatabaseWork(this);

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
        long timeSpent = System.currentTimeMillis() - startTime;
        Log.d("time", String.valueOf(timeSpent));
        Intent intent = new Intent(this, MainActivity.class);   //Start MainActivity
        startActivity(intent);
        finish();
    }


}
