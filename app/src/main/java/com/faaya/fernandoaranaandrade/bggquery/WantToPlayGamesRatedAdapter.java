package com.faaya.fernandoaranaandrade.bggquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.faaya.fernandoaranaandrade.bggquery.selectBussines.GamesRated;

import java.util.ArrayList;
import java.util.List;

public class WantToPlayGamesRatedAdapter extends ArrayAdapter<GamesRated> {

    private String[] users;

    public WantToPlayGamesRatedAdapter(Context context, List<GamesRated> gamesRateds, String[] users) {
        super(context, 0, gamesRateds);
        this.users = users;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        GamesRated item = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        List<String> usersWhoWantoToPlay = new ArrayList<>();
        for (String user : users) {
            if (wantToPlay(item, user)) {
                usersWhoWantoToPlay.add(user);
            }
        }
        switch (usersWhoWantoToPlay.size()) {
            case 1:
                view = inflater.inflate(R.layout.game_wtp_layout1, parent, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.game_wtp_layout2, parent, false);
                break;
            case 3:
                view = inflater.inflate(R.layout.game_wtp_layout3, parent, false);
                break;
            case 4:
                view = inflater.inflate(R.layout.game_wtp_layout4, parent, false);
                break;
            case 5:
                view = inflater.inflate(R.layout.game_wtp_layout5, parent, false);
                break;
            case 6:
                view = inflater.inflate(R.layout.game_wtp_layout6, parent, false);
                break;
        }
        TextView name = view.findViewById(R.id.textViewName);
        name.setText(item.getName());
        TextView rating1 = view.findViewById(R.id.textViewRating1);
        rating1.setText(getText(0, item, usersWhoWantoToPlay));
        if (usersWhoWantoToPlay.size() > 1) {
            TextView rating = view.findViewById(R.id.textViewRating2);
            rating.setText(getText(1, item, usersWhoWantoToPlay));
        }
        if (usersWhoWantoToPlay.size() > 2) {
            TextView rating = view.findViewById(R.id.textViewRating3);
            rating.setText(getText(2, item, usersWhoWantoToPlay));
        }
        if (usersWhoWantoToPlay.size() > 3) {
            TextView rating = view.findViewById(R.id.textViewRating4);
            rating.setText(getText(3, item, usersWhoWantoToPlay));
        }
        if (usersWhoWantoToPlay.size() > 4) {
            TextView rating = view.findViewById(R.id.textViewRating5);
            rating.setText(getText(4, item, usersWhoWantoToPlay));
        }
        if (usersWhoWantoToPlay.size() > 5) {
            TextView rating = view.findViewById(R.id.textViewRating6);
            rating.setText(getText(5, item, usersWhoWantoToPlay));
        }
        return view;
    }

    private boolean wantToPlay(GamesRated item, String user) {
        String wantToPlay = item.getWantToPlay(user);
        return wantToPlay != null && !wantToPlay.isEmpty();
    }

    private String getText(int index, GamesRated item, List<String> usersWhoWantoToPlay) {
        return usersWhoWantoToPlay.get(index) + ": " + item.getWantToPlay(usersWhoWantoToPlay.get(index));
    }

}
