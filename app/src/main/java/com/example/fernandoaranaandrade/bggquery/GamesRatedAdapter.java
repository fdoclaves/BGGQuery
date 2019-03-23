package com.example.fernandoaranaandrade.bggquery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesRated;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GamesRatedAdapter extends ArrayAdapter<GamesRated> {

    private String[] users;
    private String totalRatingText;

    public GamesRatedAdapter(Context context, List<GamesRated> gamesRateds, String[] users) {
        super(context, 0, gamesRateds);
        totalRatingText = context.getString(R.string.TotalRating).toUpperCase() + ": ";
        this.users = users;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        GamesRated gamesRated = getItem(position);
        if (gamesRated.isTitle()) {
            view = inflater.inflate(R.layout.game_rated_title_layout, parent, false);
            TextView name = view.findViewById(R.id.textViewName);
            name.setText(gamesRated.getName());
        } else {
            List<String> userWhoRated = new ArrayList<>();
            for (String user : users) {
                if (hasAverage(gamesRated.getAverage(user))) {
                    userWhoRated.add(user);
                }
            }
            switch (userWhoRated.size()) {
                case 0:
                    return inflater.inflate(R.layout.game_rated_layout1, parent, false);
                case 1:
                    view = inflater.inflate(R.layout.game_rated_layout1, parent, false);
                    break;
                case 2:
                    view = inflater.inflate(R.layout.game_rated_layout2, parent, false);
                    break;
                case 3:
                    view = inflater.inflate(R.layout.game_rated_layout3, parent, false);
                    break;
                case 4:
                    view = inflater.inflate(R.layout.game_rated_layout4, parent, false);
                    break;
                case 5:
                    view = inflater.inflate(R.layout.game_rated_layout5, parent, false);
                    break;
                case 6:
                    view = inflater.inflate(R.layout.game_rated_layout6, parent, false);
                    break;
            }

            TextView name = view.findViewById(R.id.textViewName);
            name.setText(gamesRated.getName());
            DecimalFormat decimalFormat = new DecimalFormat(".##");
            String format = decimalFormat.format(gamesRated.getAllAverage());
            TextView textView = view.findViewById(R.id.textViewRating);
            String text = totalRatingText + format;
            textView.setText(text);
            TextView rating1 = view.findViewById(R.id.textViewRating1);
            rating1.setText(getText(gamesRated, 0, userWhoRated));
            if (userWhoRated.size() > 1) {
                TextView rating = view.findViewById(R.id.textViewRating2);
                rating.setText(getText(gamesRated, 1, userWhoRated));
            }
            if (userWhoRated.size() > 2) {
                TextView rating = view.findViewById(R.id.textViewRating3);
                rating.setText(getText(gamesRated, 2, userWhoRated));
            }
            if (userWhoRated.size() > 3) {
                TextView rating = view.findViewById(R.id.textViewRating4);
                rating.setText(getText(gamesRated, 3, userWhoRated));
            }
            if (userWhoRated.size() > 4) {
                TextView rating = view.findViewById(R.id.textViewRating5);
                rating.setText(getText(gamesRated, 4, userWhoRated));
            }
            if (userWhoRated.size() > 5) {
                TextView rating = view.findViewById(R.id.textViewRating6);
                rating.setText(getText(gamesRated, 5, userWhoRated));
            }
        }
        return view;
    }

    @NonNull
    private String getText(GamesRated item, int index, List<String> userWhoRated) {
        String average = item.getAverage(userWhoRated.get(index));
        return userWhoRated.get(index) + ": " + average;
    }

    private boolean hasAverage(String average) {
        return average != null && !average.isEmpty() && !average.equalsIgnoreCase("N/A");
    }
}
