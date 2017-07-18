package com.bestresto;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by voudy on 18.07.17.
 */

public class RequestThread extends Thread {

    private String server = "http://www.bestresto.ru/api/";
    private String request = "";
    private AddDbInterface curManager;
    private Context context;

    RequestThread(String request, AddDbInterface curManager, Context context){
        this.request = request;
        this.curManager = curManager;
        this.context = context;
    }

    private synchronized void putToDb(List<HashMap<String, Object>> data){
        curManager.addAllDb(data, context);
    }

    @Override
    public void run() {
        GetRequest client = new GetRequest();
        try {
            String response = client.run(server + request);
            List<HashMap<String, Object>> data = Parser.parserJackson(response);
            Log.d(request, String.valueOf(data.size()));
            putToDb(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class GetRequest{
    private OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
        catch (Exception e){
            return "";
        }

    }
}
