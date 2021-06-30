package com.example.getdowntoquizness;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AdminManageMainFragment extends UserFragment {
//    private Data usersData;
//    private CommunicatorManageAcc mCommunicatorManageAcc;

    ArrayList<StudentListItem> studentList;
    ArrayList<HashMap<String, String>> studentNamesUsernamesAndSelectedStatus;
    ArrayList<HashMap<String, String>> selectedStudents;

    public class StudentListItem {
        String studentName;
        boolean isSelected;
        String studentUsername;

        public StudentListItem(String studentName, boolean isSelected, String studentUsername) {
            this.studentName = studentName;
            this.isSelected = isSelected;
            this.studentUsername = studentUsername;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getStudentName() {
            return studentName;
        }

        public boolean getSelected() {
            return isSelected;
        }

        public String getStudentUsername() {
            return studentUsername;
        }
    }

    public AdminManageMainFragment(UserFragment fragment) {
        // Required empty public constructor
        super();
    }

    public static AdminManageMainFragment newInstance(String currentUsername) {
        return new AdminManageMainFragment(UserFragment.newInstance(currentUsername));
    }


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
//
//
//        return view;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_manage_main_layout, container, false);

        studentNamesUsernamesAndSelectedStatus = getStudentsList();
        studentList = new ArrayList<>();
        for (HashMap<String, String> student : studentNamesUsernamesAndSelectedStatus) {
            studentList.add(new StudentListItem(student.get("name"), false, student.get("username")));
        }

        //TODO: why wont my recyclerview show up??
        StudentListAdapter adapter = new StudentListAdapter(this.getContext(), studentList);
        RecyclerView rv = view.findViewById(R.id.RV_studentsList);
        LinearLayoutManager llm =  new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

//        selectedStudents = adapter.getStudentsToDelete(); //TODO: hmmm

        return view;
    }

    public ArrayList<HashMap<String, String>> getStudentsList() {
        ArrayList<HashMap<String, String>> studentsList = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<ArrayList<HashMap<String, String>>> future = executor.submit(() -> {
            DBHandler db = new DBHandler(getActivity());
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

//    public void removeSelectedStudents(View view) {
//        System.out.println(getSelectedStudents());
//    }

    public void onCheckBoxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
    }

//    public ArrayList<String> getSelectedStudents() {
//        return selectedStudents;
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        AdminActivity adminActivity;

        if (context instanceof AdminActivity){
            adminActivity = (AdminActivity) context;

            //TODO: turn this into a listener so it can update as changes are made
//            adminActivity.selectedStudents = getSelectedStudents();
        }
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





}