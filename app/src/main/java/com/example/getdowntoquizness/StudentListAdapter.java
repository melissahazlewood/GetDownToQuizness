package com.example.getdowntoquizness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<AdminManageMainFragment.StudentListItem> implements View.OnClickListener {
    ArrayList<AdminManageMainFragment.StudentListItem> studentList;
    Context context;

    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
    }

    public StudentListAdapter(ArrayList<AdminManageMainFragment.StudentListItem> studentList, Context context) {
        super(context, R.layout.students_list_layout, studentList);
        this.studentList = studentList;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, "Clicked a student", Toast.LENGTH_SHORT);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AdminManageMainFragment.StudentListItem studentListItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.students_list_layout, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtStudent_list_name);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox_studentsList);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(studentListItem.getStudentName());

        return convertView;
    }
}
