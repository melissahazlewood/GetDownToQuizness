package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AdminManageMainFragment extends UserFragment {
//    private Data usersData;
//    private CommunicatorManageAcc mCommunicatorManageAcc;

    ArrayList<StudentListItem> studentList;
    ArrayList<String> studentNames;

    public class StudentListItem {
        String studentName;
        boolean isSelected;

        public StudentListItem(String studentName, Boolean isSelected) {
            this.studentName = studentName;
            this.isSelected = isSelected;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
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

        studentNames = getStudentsList();
        studentList = new ArrayList<>();
        for (String student : studentNames) {
            studentList.add(new StudentListItem(student, false));
        }
        RecyclerView rv = view.findViewById(R.id.RV_studentsList);
        LinearLayoutManager llm =  new LinearLayoutManager(requireContext().getApplicationContext());
        rv.setLayoutManager(llm);
        StudentListAdapter adapter = new StudentListAdapter(getContext(), studentList);
        rv.setAdapter(adapter);

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


}