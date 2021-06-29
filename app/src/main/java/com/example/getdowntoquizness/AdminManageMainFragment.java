package com.example.getdowntoquizness;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: implement user fragment interface

public class AdminManageMainFragment extends UserFragment { //extends Fragment implements UserFragment {
    private Data usersData;
    private CommunicatorManageAcc mCommunicatorManageAcc;

//    private String currentUsername;
//
//    private final static String ARG_CURRENT_USERNAME = "currentUsername";

    public AdminManageMainFragment(UserFragment fragment) {
        // Required empty public constructor
        super();
    }

//    public static AdminManageMainFragment newInstance(String currentUsername) {
//        AdminManageMainFragment fragment = new AdminManageMainFragment();
//        fragment.currentUsername = currentUsername;
//
//        Bundle args = new Bundle();
//        args.putString(ARG_CURRENT_USERNAME, currentUsername);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static AdminManageMainFragment newInstance(String currentUsername) {
        return new AdminManageMainFragment(UserFragment.newInstance(currentUsername));
    }

//    @Override
//    public static AdminHomeFragment newInstance(String currentUsername) {
//        AdminManageMainFragment fragment = new AdminManageMainFragment();
//        fragment.currentUsername = currentUsername;
//
//        Bundle args = new Bundle();
//        args.putString(ARG_CURRENT_USERNAME, currentUsername);
//        fragment.setArguments(args);
//        return (AdminHomeFragment) fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            usersData = (Data) getArguments().getSerializable("data");
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
//            currentUsername = getArguments().getString(ARG_CURRENT_USERNAME);
//        }
//        else{
//            System.out.println("No Students data");
//        }
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: show "no student data" if no student users in table
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_admin_manage_main_layout, container, false);
//
////        ArrayList<HashMap<String,String>> studentsList = usersData.getAllStudentsListView();
////        ListView lv = view.findViewById(R.id.LV_studentsList);
////        ListAdapter adapter = new SimpleAdapter(getContext(), studentsList, R.layout.students_list_layout,
////                new String[] {"studentName"}, new int[] {R.id.txtStudent_list_name});
////        lv.setAdapter(adapter);
//
//        //TODO: set student list adapter
//
//
//        return view;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_manage_main_layout, container, false);

//        ArrayList<HashMap<String,String>> studentsList = usersData.getAllStudentsListView();
//        ListView lv = view.findViewById(R.id.LV_studentsList);
//        ListAdapter adapter = new SimpleAdapter(getContext(), studentsList, R.layout.students_list_layout,
//                new String[] {"studentName"}, new int[] {R.id.txtStudent_list_name});
//        lv.setAdapter(adapter);

        //TODO: set student list adapter


        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        if(context instanceof CommunicatorManageAcc){
//            mCommunicatorManageAcc = (CommunicatorManageAcc) context; //listener gets the context
//        }
//        else{   // to handle an exception
//            throw new RuntimeException(context.toString() + " must implement FragmentListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mCommunicatorManageAcc = null;
//    }
}