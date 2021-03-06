package com.faaya.fernandoaranaandrade.bggquery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InvalidDataFragment extends DialogFragment {

    private static OkAction okAction;
    private String text;

    public InvalidDataFragment() {

    }

    public static InvalidDataFragment newInstance(String title, OkAction okAction) {
        InvalidDataFragment fragment = newInstance(title);
        InvalidDataFragment.okAction = okAction;
        return fragment;
    }

    public static InvalidDataFragment newInstance(String title) {
        InvalidDataFragment frag = new InvalidDataFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        okAction = new OkAction() {
            @Override
            public void action() {

            }
        };
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
        textView.setText(text);

        Button okButton = view.findViewById(R.id.buttonFrammet);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                okAction.action();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void setText(String text) {
        this.text = text;
    }

}