package com.example.fernandoaranaandrade.bggquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fernandoaranaandrade.bggquery.beans.Username;

import java.util.List;

public class UsernameViewAdapter extends ArrayAdapter<Username> {

    public UsernameViewAdapter(Context context, List<Username> usernames) {
        super(context, 0, usernames);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Username username = getItem(position);
        view = inflater.inflate(R.layout.username_view_layout, parent, false);
        TextView name = view.findViewById(R.id.textViewName);
        name.setText(username.getUsername());
        return view;
    }

}
