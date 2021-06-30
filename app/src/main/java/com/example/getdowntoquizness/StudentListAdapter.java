package com.example.getdowntoquizness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StudentListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    private LayoutInflater inflater;
    private ArrayList<AdminManageMainFragment.StudentListItem> studentList;
    ArrayList<String> usersToDelete;
    TextView mListItemName;
    CheckBox mCheckBox;


    public StudentListAdapter(Context context, ArrayList<AdminManageMainFragment.StudentListItem> studentList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.studentList = studentList;
        this.usersToDelete = new ArrayList<>(); //TODO: do I still need this?
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(viewType, parent, false);

//        mListItemName = v.findViewById(R.id.txtStudent_list_name);
//        mCheckBox = v.findViewById(R.id.checkBox_studentsList);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        try {
            // Clear previous selections (in DB, student list item object, and UI [which can be done with bindData()])
            setSelected(false, studentList.get(position).getStudentUsername()); //DB
            studentList.get(position).setSelected(false); //list item object
            holder.bindData(studentList.get(position)); //UI

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.checkBox.isChecked()) {
                        // if UI checkbox is checked, uncheck it and make sure it's unchecked in list object and in DB
                        studentList.get(position).setSelected(false);
                        setSelected(false, studentList.get(position).getStudentUsername());
                    } else {
                        // otherwise, check it and make sure it's checked in list object and DB
                        studentList.get(position).setSelected(true);
                        setSelected(true, studentList.get(position).getStudentUsername());
                    }
                    //then update the UI
                    holder.bindData(studentList.get(position));
                }
            });

            //then also make sure to do the above steps for when just the checkbox is clicked too
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    studentList.get(position).setSelected(isChecked);
                    setSelected(isChecked, studentList.get(position).getStudentUsername());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.students_list_layout;
    }

    public boolean isSelected(String username) {
        boolean result = false;
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Boolean> future = executor.submit(() -> {
            DBHandler db = new DBHandler(context.getApplicationContext());
            return db.isSelectedHandler(username);
        });

        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        return result;
    }

    public void setSelected(boolean isNowSelected, String username) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            DBHandler db = new DBHandler(context.getApplicationContext());
            db.setSelectedHandler(isNowSelected, username);
        });

        executor.shutdown();
    }

    @Override
    public int getItemCount()
    {
        return studentList.size();
    }

    public ArrayList<String> getStudentsToDelete() {
        return usersToDelete;
    }
}
