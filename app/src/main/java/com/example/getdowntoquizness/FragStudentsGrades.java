package com.example.getdowntoquizness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLEngineResult;


public class FragStudentsGrades extends Fragment {
    private ExpandableListView mExpandableListView;
    private ArrayList<String> gradesList;
    private HashMap<String, ArrayList<String>> subList;
    private MainAdapter adapter;

    public FragStudentsGrades() {
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
        View view = inflater.inflate(R.layout.fragment_frag_students_grades, container, false);

        mExpandableListView = view.findViewById(R.id.expandable_gradesList);
        gradesList = new ArrayList<>();
        subList = new HashMap<>();

        for(int index = 0; index < 10; index++){
            gradesList.add("Group " + (index + 1));
            ArrayList<String> arrayList = new ArrayList<>();

            for(int i = 0; i < 5; i++){
                arrayList.add("Subgroup " + (i + 1));
            }
            subList.put(gradesList.get(index), arrayList);
        }

        adapter = new MainAdapter(gradesList, subList);
        mExpandableListView.setAdapter(adapter);

        return view;
    }
}