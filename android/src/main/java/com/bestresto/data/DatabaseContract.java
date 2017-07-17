package com.bestresto.data;

import android.provider.BaseColumns;

/**
 * Created by sergey on 29.05.17.
 */

public final class DatabaseContract {

    public static final class DishesColumns implements BaseColumns {
        public final static String TABLE_NAME = "Dishes";

        public final static String _ID = BaseColumns._ID;
        public final static String INDEXID = "indexid";
        public final static String PARENT_ID = "parent_id";
        public final static String ACTIVE = "active";
        public final static String CAPTION = "caption";
        public final static String KITCHEN = "kithen";
        public final static String PRICE = "price";
        public final static String DESC = "desc";
        public final static String FIRST_PAGE = "first_page";
        public final static String PICTURE = "picture";
        public final static String DOPPIC = "doppic";
        public final static String SORT = "sort";
        public final static String REITING = "reiting";
        public final static String GARANT = "garant";
        public final static String SEARCHTAGS = "searchtags";
        public final static String CREATEDATE = "createdate";
    }

    public static final class RestaurantsColumns implements BaseColumns{
        public final static String TABLE_NAME = "Restaurants";

        public final static String _ID = BaseColumns._ID;
        public final static String CAPTION = "caption";
        public final static String URL = "url";
        public final static String LOGO = "logo";
        public final static String REITING = "reit";
        public final static String TIP = "tip";
        public final static String MIN_PRICE = "minPrice";
        public final static String MAX_PRICE = "maxPrice";
        public final static String KITCHEN = "kitchen";
        public final static String ADDRESS = "adress";
    }

    public static final class KitchenTypesColumns implements BaseColumns{
        public final static String TABLE_NAME = "KitchenTypes";

        public final static String _ID = BaseColumns._ID;
        public final static String CAPTION = "caption";
        public final static String INDEXID = "indexid";
        public final static String SORT = "sort";
        public final static String ACTIVE = "active";
        public final static String PRIMEID = "primeId";
    }

    public static final class RestaurantTypesColumns implements BaseColumns{
        public final static String TABLE_NAME = "RestaurantTypes";

        public final static String _ID = BaseColumns._ID;
        public final static String CAPTION = "caption";
        public final static String INDEXID = "indexid";
        public final static String SORT = "sort";
        public final static String ACTIVE = "active";
        public final static String PRIMEID = "primeId";
    }
}
