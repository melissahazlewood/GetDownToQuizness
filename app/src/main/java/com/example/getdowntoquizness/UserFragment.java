package com.example.getdowntoquizness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class UserFragment extends Fragment {
    String currentUsername = null;
    final static String ARG_CURRENT_USERNAME = "currentUsername";

    public static UserFragment newInstance(String currentUsername) {
        UserFragment fragment = new UserFragment();
        fragment.currentUsername = currentUsername;

        Bundle args = new Bundle();
        args.putString(ARG_CURRENT_USERNAME, currentUsername);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentUsername = getArguments().getString(ARG_CURRENT_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO: force override?

        View view = null;


        return view;
    }
}
