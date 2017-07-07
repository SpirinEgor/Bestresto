package com.bestresto;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestresto.data.DatabaseContract;
import com.bestresto.Dish.DishManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        LinearLayout dishes = (LinearLayout) view.findViewById(R.id.main_dishes_list);
        ArrayList<HashMap<String, Object>> first_dishes_list = (new DishManager()).make_data_first(view.getContext());

        int position = 0;
        for (final HashMap<String, Object> dish : first_dishes_list) {
            final int current_position = position;
            final View current = inflater.inflate(R.layout.dish_listitem, null, false);

            final TextView caption = (TextView) current.findViewById(R.id.dish_caption);
            caption.setText(dish.get(DatabaseContract.DishesColumns.CAPTION).toString());

            TextView price = (TextView) current.findViewById(R.id.dish_price);
            price.setText(dish.get(DatabaseContract.DishesColumns.PRICE).toString());

            ImageView picture = (ImageView) current.findViewById(R.id.dish_picture);
            String url = "http://www.bestresto.ru/" + dish.get(DatabaseContract.DishesColumns.PICTURE);
            Picasso.with(view.getContext()).load(url).into(picture);

            current.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), PagerActivity.class);
                    i.putExtra(DatabaseContract.DishesColumns.INDEXID,
                            current_position).toString();
                    i.putExtra(PagerActivity.KEY_TYPE_LIST, PagerActivity.FIRST_DISHES_TYPE);
                    startActivity(i);
                }
            });

            dishes.addView(current);
            position ++;
        }
        return view;
    }
}
