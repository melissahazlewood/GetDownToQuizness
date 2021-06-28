package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class AdminManageMainFragment extends Fragment {
    private Data usersData;

    public AdminManageMainFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usersData = (Data) getArguments().getSerializable("data");
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_manage_main_layout, container, false);

        String[] studentsList = usersData.getAllStudents();
        RecyclerView rv = view.findViewById(R.id.RV_studentsList);

        //Adapter aa = new Adapter(getContext(), android.R.layout.simple_list_item_1, studentsList);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Setting the ArrayAdapter data on the Spinner
        //rv.setAdapter(aa);


        return view;
    }
}