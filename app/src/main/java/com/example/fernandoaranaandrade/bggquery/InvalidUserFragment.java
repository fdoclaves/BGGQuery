package com.example.fernandoaranaandrade.bggquery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InvalidUserFragment extends DialogFragment {

    private String invalidUser;

    public InvalidUserFragment() {

    }

    public static InvalidUserFragment newInstance(String title) {
        InvalidUserFragment frag = new InvalidUserFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invalid_user, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.lbl_your_name);
        String text = String.format(getString(R.string.ElUsuarioEsInvalido), invalidUser);
        textView.setText(text);

        Button okButton = view.findViewById(R.id.buttonFrammet);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void setInvalidUser(String invalidUser) {
        this.invalidUser = invalidUser;
    }

}