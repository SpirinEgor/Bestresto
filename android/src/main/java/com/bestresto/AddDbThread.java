package com.bestresto;

import java.util.ArrayList;
import java.util.HashMap;

public class AddDbThread extends Thread {

    private ArrayList<HashMap<String, Object>> data;
    private ManagerInterface manager;

    public AddDbThread(ArrayList<HashMap<String, Object>> data, ManagerInterface manager){
        this.data = data;
        this.manager = manager;
    }

    @Override
    public void run(){
        for (HashMap<String, Object> cur: data){
            manager.addDb(cur);
        }
    }

}
