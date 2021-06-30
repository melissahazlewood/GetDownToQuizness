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
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//TODO: implement user fragment interface

public class AdminManageMainFragment extends UserFragment { //extends Fragment implements UserFragment {
    private Data usersData;
    private CommunicatorManageAcc mCommunicatorManageAcc;

    ArrayList<StudentListItem> studentListItems;
    ListView lv;
    private static StudentListAdapter studentListAdapter;

    public class StudentListItem {
        String studentName;
        CheckBox checkBox;

        public StudentListItem(String studentName, CheckBox checkBox) {
            this.studentName = studentName;
            this.checkBox = checkBox;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public String getStudentName() {
            return studentName;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }

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
//        lv = getActivity().findViewById(R.id.LV_studentsList);
//        ArrayList<String> studentList = getStudentsList();
//        studentListItems = new ArrayList<>();
//        for (String student : studentList) {
//            studentListItems.add(new StudentListItem(student, new CheckBox(getActivity().getApplicationContext())));
//        }
//        studentListAdapter = new StudentListAdapter(studentListItems, getContext());
//        lv.setAdapter(studentListAdapter);
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
//        view.findViewById(R.id.checkBox_studentsList).setVisibility(View.INVISIBLE);
//        ArrayList<HashMap<String, String>> studentsList = getStudentsList();

//        ArrayList<String> studentsList = getStudentsList();
//        ListView lv = view.findViewById(R.id.LV_studentsList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.students_list_layout, studentsList);
//        lv.setAdapter(adapter);


        lv = view.findViewById(R.id.LV_studentsList);
        ArrayList<String> studentList = getStudentsList();
        studentListItems = new ArrayList<>();
        for (String student : studentList) {
            studentListItems.add(new StudentListItem(student, new CheckBox(this.getContext())));
        }
        studentListAdapter = new StudentListAdapter(studentListItems, getContext());
        lv.setAdapter(studentListAdapter);


        return view;
    }

    public ArrayList<String> getStudentsList() {
        ArrayList<String> studentsList = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<ArrayList<String>> future = executor.submit(() -> {
            DBHandler db = new DBHandler(getContext());
            return db.getStudentsListHandler();
        });

        try {
            studentsList = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        return studentsList;
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

//    public ArrayList<HashMap<String, String>> getStudentsList() {
//        ArrayList<HashMap<String, String>> studentsList = new ArrayList<>();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        Future<ArrayList<HashMap<String, String>>> future = executor.submit(() -> {
//            DBHandler db = new DBHandler(getContext());
//            return db.getStudentsListHandler();
//        });
//
//        try {
//            studentsList = future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        executor.shutdown();
//        return studentsList;
//    }

}