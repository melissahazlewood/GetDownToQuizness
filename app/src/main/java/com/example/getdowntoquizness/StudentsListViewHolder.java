package com.example.getdowntoquizness;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StudentsListViewHolder extends RecyclerView.ViewHolder {

    TextView tvStudentName;
    CheckBox checkBox;

    public StudentsListViewHolder(View itemView)
    {
        super(itemView);

        tvStudentName = itemView.findViewById(R.id.txtStudent_list_name);
        checkBox = itemView.findViewById(R.id.checkBox_studentsList);
    }

    public void bindData(AdminManageMainFragment.StudentListItem viewModel) {
        tvStudentName.setText(viewModel.getStudentName());
        checkBox.setChecked(viewModel.getSelected());
    }

}
