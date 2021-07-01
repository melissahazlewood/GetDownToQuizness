package com.example.getdowntoquizness;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizQuestionViewHolder extends RecyclerView.ViewHolder {

    EditText mQuestionText;
    EditText mAnswer1Text;
    EditText mAnswer2Text;
    EditText mAnswer3Text;
    EditText mAnswer4Text;
    CheckBox mCheckBoxAnswer1;
    CheckBox mCheckBoxAnswer2;
    CheckBox mCheckBoxAnswer3;
    CheckBox mCheckBoxAnswer4;
    ArrayList<CheckBox> mCheckBoxes;


    public QuizQuestionViewHolder(View itemView)
    {
        super(itemView);

        EditText mQuestionText = itemView.findViewById(R.id.questionText);
        EditText mAnswer1Text = itemView.findViewById(R.id.txtAnswer1);
        EditText mAnswer2Text = itemView.findViewById(R.id.txtAnswer2);
        EditText mAnswer3Text = itemView.findViewById(R.id.txtAnswer3);
        EditText mAnswer4Text = itemView.findViewById(R.id.txtAnswer4);
        CheckBox mCheckBoxAnswer1 = itemView.findViewById(R.id.checkBox_answer1);
        CheckBox mCheckBoxAnswer2 = itemView.findViewById(R.id.checkBox_answer2);
        CheckBox mCheckBoxAnswer3 = itemView.findViewById(R.id.checkBox_answer3);
        CheckBox mCheckBoxAnswer4 = itemView.findViewById(R.id.checkBox_answer4);

        mCheckBoxes = new ArrayList<>();
        mCheckBoxes.add(mCheckBoxAnswer1);
        mCheckBoxes.add(mCheckBoxAnswer2);
        mCheckBoxes.add(mCheckBoxAnswer3);
        mCheckBoxes.add(mCheckBoxAnswer4);
    }

    public void bindData(QuizQuestion viewModel) {
        mQuestionText.setText(viewModel.getQuestionText());
        mAnswer1Text.setText(viewModel.getAnswer1Text());
        mAnswer2Text.setText(viewModel.getAnswer2Text());
        mAnswer3Text.setText(viewModel.getAnswer3Text());
        mAnswer4Text.setText(viewModel.getAnswer4Text());

        int numCheckboxes = viewModel.checkBoxes.size();
        for (int i = 0; i < numCheckboxes; i++) {
            // Set checkbox to checked if it is one of the correct answers and to unchecked if not
            mCheckBoxes.get(i).setChecked(viewModel.getCorrectAnswerChoices().contains(i));
        }
    }
}
