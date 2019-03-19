package com.example.fernandoaranaandrade.bggquery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddUserFragment extends DialogFragment {

    private static UserListManager userListManager;

    public AddUserFragment() {

    }

    public static AddUserFragment newInstance(String title, UserListManager userListManager) {
        AddUserFragment.userListManager = userListManager;
        AddUserFragment frag = new AddUserFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Button okButton = view.findViewById(R.id.buttonFrammet);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.editTextAddUser);
                userListManager.add(editText.getText().toString());
                dismiss();
            }
        });

        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}