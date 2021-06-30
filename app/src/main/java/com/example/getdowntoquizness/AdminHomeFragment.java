package com.example.getdowntoquizness;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdminHomeFragment extends UserFragment {

    public AdminHomeFragment(UserFragment fragment) {
        // Required empty public constructor
        super();
    }

    public static AdminHomeFragment newInstance(String currentUsername) {
        return new AdminHomeFragment(UserFragment.newInstance(currentUsername));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_home, container, false);
    }
}