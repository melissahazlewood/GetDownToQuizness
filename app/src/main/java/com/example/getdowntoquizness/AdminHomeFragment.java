package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//TODO: refactor/rename this fragment to "AdminManageAccountsFragment" and extend from user fragment interface

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeFragment extends UserFragment {

//    private String currentUsername;
//
//    private final static String ARG_CURRENT_USERNAME = "currentUsername";

    public AdminHomeFragment(UserFragment fragment) {
        // Required empty public constructor
        super();
    }

//    public static AdminHomeFragment newInstance(String currentUsername) {
//        AdminHomeFragment fragment = new AdminHomeFragment();
//        fragment.currentUsername = currentUsername;
//
//        Bundle args = new Bundle();
//        args.putString(ARG_CURRENT_USERNAME, currentUsername);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static AdminHomeFragment newInstance(String currentUsername) {
        return new AdminHomeFragment(UserFragment.newInstance(currentUsername));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//            currentUsername = getArguments().getString(ARG_CURRENT_USERNAME);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        return view;
    }
}