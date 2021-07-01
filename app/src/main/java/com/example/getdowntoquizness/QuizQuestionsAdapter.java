package com.example.getdowntoquizness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuizQuestionsAdapter extends RecyclerView.Adapter<QuizQuestionViewHolder> {

    Context context;
    private LayoutInflater inflater;
    private ArrayList<QuizQuestion> quizQuestionList;


    public QuizQuestionsAdapter(Context context, ArrayList<QuizQuestion> quizQuestionsList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.quizQuestionList = quizQuestionsList;
    }

    @Override
    public QuizQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(viewType, parent, false);

        return new QuizQuestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(QuizQuestionViewHolder holder, int position)
    {
        //TODO: fill out
        try {

//            // Clear previous selections (in DB, student list item object, and UI [which can be done with bindData()])
//            setSelected(false, studentList.get(position).getStudentUsername()); //DB
//            studentList.get(position).setSelected(false); //list item object
//            holder.bindData(studentList.get(position)); //UI

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (holder.checkBox.isChecked()) {
//                        // if UI checkbox is checked, uncheck it and make sure it's unchecked in list object and in DB
//                        studentList.get(position).setSelected(false);
//                        setSelected(false, studentList.get(position).getStudentUsername());
//                    } else {
//                        // otherwise, check it and make sure it's checked in list object and DB
//                        studentList.get(position).setSelected(true);
//                        setSelected(true, studentList.get(position).getStudentUsername());
//                    }
//                    //then update the UI
//                    holder.bindData(studentList.get(position));
//                }
//            });

            //then also make sure to do the above steps for when just the checkbox is clicked too
//            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    studentList.get(position).setSelected(isChecked);
//                    setSelected(isChecked, studentList.get(position).getStudentUsername());
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.quiz_question_layout;
    }

    //TODO:  look below for differences to change
//    public boolean isSelected(String username) {
//        boolean result = false;
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        Future<Boolean> future = executor.submit(() -> {
//            DBHandler db = new DBHandler(context.getApplicationContext());
//            return db.isSelectedHandler(username);
//        });
//
//        try {
//            result = future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        executor.shutdown();
//        return result;
//    }
//
//    public void setSelected(boolean isNowSelected, String username) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        executor.execute(() -> {
//            DBHandler db = new DBHandler(context.getApplicationContext());
//            db.setSelectedHandler(isNowSelected, username);
//        });
//
//        executor.shutdown();
//    }

    @Override
    public int getItemCount() {
        return quizQuestionList.size();
    }
}
