package com.bestresto.Dish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bestresto.R;
import com.bestresto.data.DatabaseContract;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by egor on 01.06.17.
 * View of single dish with description
 */

public class DescriptionDishFragment extends android.support.v4.app.Fragment {

    //public static String EXTRA_DISH_CAPTION = "extra_dish_position";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dish_fragment_description, container, false);

        String name = getArguments().getString(DatabaseContract.DishesColumns.CAPTION);
        HashMap<String, String> whereConditions = new HashMap<>();
        HashMap<String, String> orderByConditions = new HashMap<>();
        whereConditions.put(DatabaseContract.DishesColumns.ACTIVE, "1");
        String[] columns = {
                DatabaseContract.DishesColumns.CAPTION,
                DatabaseContract.DishesColumns.PRICE,
                DatabaseContract.DishesColumns.PICTURE,
                DatabaseContract.DishesColumns.REITING,
                DatabaseContract.DishesColumns.DESC,
                DatabaseContract.DishesColumns.GARANT,
                DatabaseContract.DishesColumns.PARENT_ID
        };
        whereConditions.put(DatabaseContract.DishesColumns.ACTIVE, "1");
        whereConditions.put(DatabaseContract.DishesColumns.CAPTION, name);
        DishManager dishManager = new DishManager();
        dishManager.openDb(view.getContext());
        HashMap<String, Object> info = dishManager.makeData(whereConditions, orderByConditions, columns).get(0);
        dishManager.closeDb();

        ImageView picture = (ImageView) view.findViewById(R.id.singleDish_picture);
        RatingBar reiting = (RatingBar) view.findViewById(R.id.singleDish_rating);
        TextView caption = (TextView) view.findViewById(R.id.singleDish_caption);
        TextView price = (TextView) view.findViewById(R.id.singleDish_price);
        WebView desc = (WebView) view.findViewById(R.id.singleDish_description);

        String url = "http://www.bestresto.ru/" + info.get(DatabaseContract.DishesColumns.PICTURE);
        Picasso.with(view.getContext()).load(url).into(picture);
        String rating = "";
        if (info.containsKey(DatabaseContract.DishesColumns.REITING))
            rating = info.get(DatabaseContract.DishesColumns.REITING).toString();
        if (rating.equals(""))
            reiting.setRating(0);
        else
            reiting.setRating(Float.parseFloat(rating));
        caption.setText(info.get(DatabaseContract.DishesColumns.CAPTION).toString());
        price.append(info.get(DatabaseContract.DishesColumns.PRICE).toString());
        String description = info.get(DatabaseContract.DishesColumns.DESC).toString();
        if (!description.equals(""))
            description += "\n";
        description += info.get(DatabaseContract.DishesColumns.GARANT).toString();
        desc.loadDataWithBaseURL("", description, "text/html", "UTF-8", "");

        return view;
    }

    public static DescriptionDishFragment newInstance(String caption) {
        Bundle args = new Bundle();
        args.putSerializable(DatabaseContract.DishesColumns.CAPTION, caption);
        DescriptionDishFragment fragment = new DescriptionDishFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
