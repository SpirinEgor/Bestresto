package com.bestresto;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bestresto.Dish.DishManager;
import com.bestresto.Restaurant.RestaurantManager;
import com.bestresto.Types.KitchenTypesManager;
import com.bestresto.data.dbHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sergey on 20.06.17.
 */

interface AsyncResponse {
    void processFinish(String output);
}

public class SplashActivity extends AppCompatActivity
        implements AsyncResponse{
    dbHelper dbh;
    ProgressBar pBar;
    TextView textView;
    final String SERVER = "http://www.bestresto.ru/api/";

    final String DISHES_ALL_REQUEST = "foods/foods.php?all";
    final String RESTAURANTS_ALL_REQUEST = "rest/rest.php?all";
    final String KITCHEN_TYPES_REQUEST = "types/types.php?kitchenTypes";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView)findViewById(R.id.textIndicator);
        pBar.setIndeterminate(true);
        dbh = new dbHelper(this);
        DownloadTask task = new DownloadTask(this);
        task.delegate = this;
        task.execute(KITCHEN_TYPES_REQUEST, DISHES_ALL_REQUEST, RESTAURANTS_ALL_REQUEST);
    }

    @Override
    public void processFinish(String output) {
//        Toast.makeText(this, output, Toast.LENGTH_LONG).show();
    }

    private class DownloadTask extends AsyncTask<String, Integer, String>{
        private PowerManager.WakeLock mWakeLock;
        private Context context;
        private String METHOD;

        AsyncResponse delegate = null;

        DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pBar.setIndeterminate(false);
            pBar.setMax(100);
            pBar.setProgress(values[0]);
            if (values[0]==0){
                if (METHOD.equals(DISHES_ALL_REQUEST))
                    textView.setText(R.string.refresh_dishes);
                else if (METHOD.equals(RESTAURANTS_ALL_REQUEST))
                    textView.setText(R.string.refresh_restaurants);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(context, MainActivity.class);   //Start MainActivity
            startActivity(intent);
            finish();
        }

        @Override
        protected String doInBackground(String... params) {
            for (String method: params) {
                long start = System.currentTimeMillis();
                METHOD = method;
                URL url = null;
                try {
                    url = new URL(SERVER + METHOD);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                HttpURLConnection urlConnection;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    InputStream inputStream;
                    inputStream = urlConnection.getInputStream();
                    StringBuilder buffer = new StringBuilder();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    String resultJson = buffer.toString();
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    dbh.onUpgrade(db, 1, 1);
                    publishProgress(0);
                    List<HashMap<String, Object>> data = Parser.parserJackson(resultJson);
                    if (METHOD.equals(DISHES_ALL_REQUEST)) {
                        DishManager dish = new DishManager();
                        dish.openbd(context);
                        dish.cleantable();
                        int progress, i = 0;
                        for (HashMap<String, Object> curDish: data) {
                            dish.addDB(curDish, context);
                            progress = (int) ((1. * (i + 1)) / data.size() * 100);
                            publishProgress(progress);
                            ++i;
                        }
                        dish.closebd();
                    } else if (METHOD.equals(RESTAURANTS_ALL_REQUEST)) {
                        RestaurantManager rest = new RestaurantManager();
                        rest.openbd(context);
                        rest.cleantable();
                        int progress, i = 0;
                        for (HashMap<String, Object> curRest: data) {
                            rest.addDB(curRest, context);
                            progress = (int) ((1. * (i + 1)) / data.size() * 100);
                            publishProgress(progress);
                            ++i;
                        }
                        rest.closebd();
                    } else if (METHOD.equals(KITCHEN_TYPES_REQUEST)){
                        KitchenTypesManager types = new KitchenTypesManager();
                        types.openbd(context);
                        types.cleantable();
                        types.addDB(data);
                        types.closebd();
                    }
                    long finish = System.currentTimeMillis();
                    Log.d("time: " + method, Long.toString(finish - start));
                } catch (ProtocolException e) {
                    e.printStackTrace();
                    delegate.processFinish("ProtocolException");
                } catch (IOException e) {
                    delegate.processFinish("IOException");
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}
