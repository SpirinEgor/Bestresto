package com.bestresto.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sergey on 29.05.17.
 */

public class dbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Bestresto.db";

    private static final int DATABASE_VERSION = 1;
    
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDish(db);
        createRestaurant(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DishesColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.RestaurantsColumns.TABLE_NAME);
        onCreate(db);
    }

    public void createDish(SQLiteDatabase db){
        String SQL = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.DishesColumns.TABLE_NAME + " ("
                + DatabaseContract.DishesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.DishesColumns.INDEXID + " INTEGER NOT NULL, "
                + DatabaseContract.DishesColumns.PARENT_ID + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.ACTIVE + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.CAPTION + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.KITCHEN + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.PRICE + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.DESC + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.FIRST_PAGE + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.PICTURE + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.DOPPIC + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.SORT + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.REITING + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.GARANT + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.SEARCHTAGS + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.CREATEDATE +" TEXT NOT NULL);";
        db.execSQL(SQL);
    }

    public void createRestaurant(SQLiteDatabase db){
        String SQL = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.RestaurantsColumns.TABLE_NAME + " ("
                + DatabaseContract.RestaurantsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.RestaurantsColumns.INDEXID + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantsColumns.ACTIVE + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.CAPTION + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.SORT + " TEXT NOT NULL);";
        db.execSQL(SQL);
    }
}
