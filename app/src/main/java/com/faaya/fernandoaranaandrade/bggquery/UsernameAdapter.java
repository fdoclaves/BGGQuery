package com.faaya.fernandoaranaandrade.bggquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.faaya.fernandoaranaandrade.bggquery.beans.Username;

import java.util.List;

public class UsernameAdapter extends ArrayAdapter<Username> {

    private UserListManager userListManager;

    public UsernameAdapter(Context context, List<Username> usernames) {
        super(context, 0, usernames);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Username username = getItem(position);
        view = inflater.inflate(R.layout.username_layout, parent, false);
        TextView name = view.findViewById(R.id.textViewName);
        name.setText(username.getUsername());
        ImageButton imageButton = view.findViewById(R.id.imageButtonDelete);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.animate().setDuration(200).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                userListManager.delete(username);
                                view.setAlpha(1);
                            }
                        });
            }
        });
        return view;
    }

    public void setUserListManager(UserListManager userListManager) {
        this.userListManager = userListManager;
    }

}
