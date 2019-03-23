package com.example.fernandoaranaandrade.bggquery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fernandoaranaandrade.bggquery.beans.Username;
import com.example.fernandoaranaandrade.bggquery.database.Queries;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesRated;

import java.util.ArrayList;
import java.util.List;

public class AddOldOrNewUserFragment extends DialogFragment {

    private static NuevoUser nuevoUser;

    private List<Username> usernames = new ArrayList<>();

    public AddOldOrNewUserFragment() {

    }

    public static AddOldOrNewUserFragment newInstance(String title, NuevoUser nuevoUser, List<Username> usernames) {
        AddOldOrNewUserFragment.nuevoUser = nuevoUser;
        AddOldOrNewUserFragment frag = new AddOldOrNewUserFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        frag.setUsernames(usernames);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_old_or_new_user, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button buttonNuevo = view.findViewById(R.id.buttonNuevoOld);
        buttonNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                nuevoUser.add();
            }
        });

        ListView listView = view.findViewById(R.id.listViewNuevoOld);
        listView.setAdapter(new UsernameViewAdapter(view.getContext(),usernames));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Username username = (Username) parent.getItemAtPosition(position);
                System.out.println("click:"+username.getUsername());
                nuevoUser.add(username);
                dismiss();
            }
        });


        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void setUsernames(List<Username> usernames) {
        this.usernames = usernames;
    }
}