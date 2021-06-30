package com.example.getdowntoquizness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<MyViewHolder> {
//    ArrayList<AdminManageMainFragment.StudentListItem> studentList;

    private LayoutInflater inflater;
//    ArrayList<String> studentNames;
//    ArrayList<CheckBox> checkBoxes;
    Context context;
    private ArrayList<AdminManageMainFragment.StudentListItem> studentList;


    public StudentListAdapter(Context context, ArrayList<AdminManageMainFragment.StudentListItem> studentList) {
        this.studentList = studentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
//        this.checkBoxes = new ArrayList<>();
//        for (String ignored : studentNames)
//            this.checkBoxes.add(new CheckBox(context));
    }

//    public StudentListAdapter(Context context, ArrayList<String> studentNames, ArrayList<CheckBox> checkBoxes) {
//        this.studentNames = studentNames;
////        this.checkBoxes = checkBoxes;
//        this.context = context;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.students_list_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        try {
            holder.tvStudentName.setText(studentList.get(position).getStudentName());
            holder.checkBox.setChecked(studentList.get(position).getSelected());
//        holder.checkBox = checkBoxes.get(position); //TODO: is this the right context?

//        holder.checkBox.setOnCheckedChangeListener(null);
//        holder.checkBox.setChecked(.isSelected());

            holder.checkBox.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer position = (Integer) holder.checkBox.getTag();

                    Toast.makeText(context, studentList.get(position).getStudentName(), Toast.LENGTH_LONG).show();

                    if (studentList.get(position).getSelected()) { //TODO: is the logic right here?
                        studentList.get(position).setSelected(false);
                    } else {
                        studentList.get(position).setSelected(true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return studentList.size();
    }
}
