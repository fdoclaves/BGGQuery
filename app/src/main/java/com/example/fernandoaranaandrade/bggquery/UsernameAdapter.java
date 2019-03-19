package com.example.fernandoaranaandrade.bggquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class UsernameAdapter extends ArrayAdapter<String> {

    private UserListManager userListManager;

    public UsernameAdapter(Context context, List<String> usernames, UserListManager userListManager) {
        super(context, 0, usernames);
        this.userListManager = userListManager;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final String username = getItem(position);
        view = inflater.inflate(R.layout.username_layout, parent, false);
        TextView name = view.findViewById(R.id.textViewName);
        name.setText(username);
        ImageButton imageButton = view.findViewById(R.id.imageButtonDelete);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userListManager.delete(username);
            }
        });
        return view;
    }

}
