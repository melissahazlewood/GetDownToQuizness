package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment {
    private TextView txtViewAbout;
    public AboutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_about_layout, container, false);
        txtViewAbout = view.findViewById(R.id.txtAboutFragment);
        String aboutMessage = "Get Down to Quizness! helps students review, practice, and test their knowledge on skills and concepts related to their courses!\n" +
                "\n" +
                "We have been in business since June 2021 and have a passion for serving the educational community.\n" +
                "\n" +
                "Our creators have been hard at work creating apps in the fields of self care, travel, video games, and anime.\n" +
                "\n" +
                "Contact creators here:\n" +
                "email@email.com\n" +
                "albertemail@email.com ";
        txtViewAbout.setText(aboutMessage);
        return view;
    }
}