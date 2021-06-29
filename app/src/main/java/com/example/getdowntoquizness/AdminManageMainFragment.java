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


public class AdminManageMainFragment extends Fragment {
    private Data usersData;
    private CommunicatorManageAcc mCommunicatorManageAcc;

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
        else{
            System.out.println("No Students data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_manage_main_layout, container, false);

        ArrayList<HashMap<String,String>> studentsList = usersData.getAllStudentsListView();
        ListView lv = view.findViewById(R.id.LV_studentsList);
        ListAdapter adapter = new SimpleAdapter(getContext(), studentsList, R.layout.students_list_layout,
                new String[] {"studentName"}, new int[] {R.id.txtStudent_list_name});
        lv.setAdapter(adapter);



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof CommunicatorManageAcc){
            mCommunicatorManageAcc = (CommunicatorManageAcc) context; //listener gets the context
        }
        else{   // to handle an exception
            throw new RuntimeException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCommunicatorManageAcc = null;
    }
}