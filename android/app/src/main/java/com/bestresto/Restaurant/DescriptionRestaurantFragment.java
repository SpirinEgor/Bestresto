package com.bestresto.Restaurant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bestresto.Dish.DescriptionDishFragment;
import com.bestresto.Dish.DishManager;
import com.bestresto.R;
import com.bestresto.data.DatabaseContract;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class DescriptionRestaurantFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rest_fragment_description, container, false);

        String name = getArguments().getString(DatabaseContract.DishesColumns.CAPTION);
        HashMap<String, Object> info = new RestaurantManager().make_data_about(view.getContext(), name);

//        ImageView picture = (ImageView) view.findViewById(R.id.singleDish_picture);
//        RatingBar reiting = (RatingBar) view.findViewById(R.id.singleDish_rating);
        TextView caption = (TextView) view.findViewById(R.id.singleRestaurant_caption);
//        TextView price = (TextView) view.findViewById(R.id.singleDish_price);
//        WebView desc = (WebView) view.findViewById(R.id.singleDish_description);

//        String url = "http://www.bestresto.ru/" + info.get(DatabaseContract.DishesColumns.PICTURE);
//        Picasso.with(view.getContext()).load(url).into(picture);
//        String rating = info.get(DatabaseContract.DishesColumns.REITING).toString();
//        if (rating.equals(""))
//            reiting.setRating(0);
//        else
//            reiting.setRating(Float.parseFloat(rating));
        caption.setText(info.get(DatabaseContract.DishesColumns.CAPTION).toString());
//        price.append(info.get(DatabaseContract.DishesColumns.PRICE).toString());
//        String description = info.get(DatabaseContract.DishesColumns.DESC).toString();
//        if (!description.equals(""))
//            description += "\n";
//        description += info.get(DatabaseContract.DishesColumns.GARANT).toString();
//        desc.loadDataWithBaseURL("", description, "text/html", "UTF-8", "");

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
