package com.bestresto;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.Types.RestaurantTypesManager;
import com.bestresto.data.dbHelper;

import java.util.ArrayList;

/**
 * Created by sergey on 20.06.17.
 */

interface AsyncResponse {
    void processFinish(String output);
}

public class SplashActivity extends AppCompatActivity{

    ProgressBar pBar;
    TextView textView;
    final String server = "http://www.bestresto.ru/api/";

    final String dishesAllRequest = "foods/foods.php?all";
    final String restaurantsAllRequest = "rest/rest.php?all";
    final String kitchenTypesRequest = "types/types.php?kitchenTypes";
    final String restaurantTypesRequest = "types/types.php?restTypes";

    final String[] requests = {
            kitchenTypesRequest,
            restaurantTypesRequest,
            dishesAllRequest,
            restaurantsAllRequest
    };

    final AddDbInterface[] manager = {
            new KitchenTypesManager(),
            new RestaurantTypesManager(),
            new DishManager(),
            new RestaurantManager()
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        long startTime = System.currentTimeMillis();
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView)findViewById(R.id.textIndicator);
        dbHelper dbh = new dbHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        try{
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < requests.length; ++i){
                threads.add(new RequestThread(requests[i], manager[i], this));
                manager[i].setDb(db);
            }
            for (Thread thread: threads){
                thread.start();
            }
            for (Thread thread: threads){
                thread.join();
            }
        }
        catch (Exception ignored){}
        db.close();
        long timeSpent = System.currentTimeMillis() - startTime;
        Log.d("time", String.valueOf(timeSpent));
        Intent intent = new Intent(this, MainActivity.class);   //Start MainActivity
        startActivity(intent);
        finish();
    }


}
