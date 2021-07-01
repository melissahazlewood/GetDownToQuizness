package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HelpFragment extends UserFragment {
    private TextView txtViewHelp;
    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_layout, container, false);

        String txtHelp = "Hello Valued User,\n" +
                "\n" +
                "If you encounter any issues, please email the developers at email@email.com and anotheremail@email.com. \n" +
                "\n" +
                "\n" +
                "We thank you for your patience!\n" +
                "\n" +
                "\n" +
                "\n" +
                "Melissa Hazlewood\n" +
                "Albert Toscano\n";
        txtViewHelp = view.findViewById(R.id.txtHelpFragment);
        txtViewHelp.setText(txtHelp);


        return view;
    }
}