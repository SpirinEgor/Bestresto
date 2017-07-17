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
        createKitchenTypes(db);
        createRestaurantTypes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DishesColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.RestaurantsColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.KitchenTypesColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.RestaurantTypesColumns.TABLE_NAME);
        onCreate(db);
    }

    public void createDish(SQLiteDatabase db){
        String SQL = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.DishesColumns.TABLE_NAME + " ("
                + DatabaseContract.DishesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.DishesColumns.INDEXID + " INTEGER NOT NULL, "
                + DatabaseContract.DishesColumns.PARENT_ID + " INTEGER NOT NULL, "
                + DatabaseContract.DishesColumns.ACTIVE + " TINYINT NOT NULL, "
                + DatabaseContract.DishesColumns.CAPTION + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.KITCHEN + " INTEGER NOT NULL, "
                + DatabaseContract.DishesColumns.PRICE + " INTEGER NOT NULL, "
                + DatabaseContract.DishesColumns.DESC + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.FIRST_PAGE + " TINYINT NOT NULL, "
                + DatabaseContract.DishesColumns.PICTURE + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.DOPPIC + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.SORT + " INTEGER NOT NULL, "
                + DatabaseContract.DishesColumns.REITING + " FLOAT NOT NULL, "
                + DatabaseContract.DishesColumns.GARANT + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.SEARCHTAGS + " TEXT NOT NULL, "
                + DatabaseContract.DishesColumns.CREATEDATE +" TEXT NOT NULL);";
        db.execSQL(SQL);
    }

    public void createRestaurant(SQLiteDatabase db){
        String SQL = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.RestaurantsColumns.TABLE_NAME + " ("
                + DatabaseContract.RestaurantsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.RestaurantsColumns.CAPTION + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.URL + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.LOGO + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.REITING + " FLOAT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.TIP + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantsColumns.MIN_PRICE + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantsColumns.MAX_PRICE + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantsColumns.KITCHEN + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantsColumns.ADDRESS + " TEXT NOT NULL);";
        db.execSQL(SQL);
    }

    public void createKitchenTypes(SQLiteDatabase db){
        String SQL = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.KitchenTypesColumns.TABLE_NAME + " ("
                + DatabaseContract.KitchenTypesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.KitchenTypesColumns.CAPTION + " TEXT NOT NULL, "
                + DatabaseContract.KitchenTypesColumns.INDEXID + " INTEGER NOT NULL, "
                + DatabaseContract.KitchenTypesColumns.SORT + " INTEGER NOT NULL, "
                + DatabaseContract.KitchenTypesColumns.PRIMEID + " INTEGER NOT NULL, "
                + DatabaseContract.KitchenTypesColumns.ACTIVE + " TINYINT NOT NULL);";
        db.execSQL(SQL);
    }

    public void createRestaurantTypes(SQLiteDatabase db){
        String SQL = "CREATE TABLE IF NOT EXISTS " + DatabaseContract.RestaurantTypesColumns.TABLE_NAME + " ("
                + DatabaseContract.RestaurantTypesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.RestaurantTypesColumns.CAPTION + " TEXT NOT NULL, "
                + DatabaseContract.RestaurantTypesColumns.INDEXID + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantTypesColumns.SORT + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantTypesColumns.PRIMEID + " INTEGER NOT NULL, "
                + DatabaseContract.RestaurantTypesColumns.ACTIVE + " TINYINT NOT NULL);";
        db.execSQL(SQL);
    }
}
