package com.bestresto.Restaurant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bestresto.Database.DatabaseContract;
import com.bestresto.Database.DatabaseGetter;
import com.bestresto.Database.QueryConditions;
import com.bestresto.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class DescriptionRestaurantFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rest_fragment_description, container, false);

        String name = getArguments().getString(DatabaseContract.RestaurantsColumns.CAPTION);

        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setWhenCondition(DatabaseContract.RestaurantsColumns.CAPTION + " = " + DatabaseGetter.prepare(name));
        queryConditions.setColumns(new String[]{
                DatabaseContract.RestaurantsColumns.CAPTION,
                DatabaseContract.RestaurantsColumns.URL,
                DatabaseContract.RestaurantsColumns.LOGO,
                DatabaseContract.RestaurantsColumns.REITING,
                DatabaseContract.RestaurantsColumns.TIP,
                DatabaseContract.RestaurantsColumns.KITCHEN,
                DatabaseContract.RestaurantsColumns.MIN_PRICE,
                DatabaseContract.RestaurantsColumns.MAX_PRICE,
                DatabaseContract.RestaurantsColumns.ADDRESS,
        });
        queryConditions.setTableName(DatabaseContract.RestaurantsColumns.TABLE_NAME);
        HashMap<String, Object> info = new DatabaseGetter(view.getContext()).makeData(queryConditions).get(0);

        ImageView logo = (ImageView) view.findViewById(R.id.singleRestaurant_logo);
        RatingBar rating = (RatingBar) view.findViewById(R.id.singleRestaurant_rating);
        TextView caption = (TextView) view.findViewById(R.id.singleRestaurant_caption);
        TextView price = (TextView) view.findViewById(R.id.singleRestaurant_price);
        TextView address = (TextView) view.findViewById(R.id.singleRestaurant_address);


        String url = "http://www.bestresto.ru/" + info.get(DatabaseContract.RestaurantsColumns.LOGO);
        Picasso.with(view.getContext()).load(url).into(logo);

        String ratingAb = info.get(DatabaseContract.RestaurantsColumns.REITING).toString();
        if (ratingAb.equals(""))
            rating.setRating(0);
        else
            rating.setRating(Float.parseFloat(ratingAb));

        caption.setText(info.get(DatabaseContract.RestaurantsColumns.CAPTION).toString());

        String price_range = "диапозон цен: " +
                info.get(DatabaseContract.RestaurantsColumns.MIN_PRICE) +
                "р - " +
                info.get(DatabaseContract.RestaurantsColumns.MAX_PRICE) + "р";
        price.setText(price_range);

        address.setText("" + info.get(DatabaseContract.RestaurantsColumns.ADDRESS));

        return view;
    }

    public static DescriptionRestaurantFragment newInstance(String caption) {
        Bundle args = new Bundle();
        args.putSerializable(DatabaseContract.RestaurantsColumns.CAPTION, caption);
        DescriptionRestaurantFragment fragment = new DescriptionRestaurantFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
