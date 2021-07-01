package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminCreateQuizFragment extends UserFragment {

    private ArrayList<QuizQuestion> questionList = new ArrayList<>();

    private Button mBtn_DoneCreatingQuiz;

    public AdminCreateQuizFragment(UserFragment fragment) {
        // Required empty public constructor
        super();
    }

    public static AdminCreateQuizFragment newInstance(String currentUsername) {
        return new AdminCreateQuizFragment(UserFragment.newInstance(currentUsername));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //TODO comment this one out and the other one back in
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_admin_create_quiz, container, false);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_create_quiz, container, false);


        questionList = new ArrayList<>();
        questionList.add(new QuizQuestion());

//        studentNamesUsernamesAndSelectedStatus = getStudentsList();
//        studentList = new ArrayList<>();
//        for (HashMap<String, String> student : studentNamesUsernamesAndSelectedStatus) {
//            studentList.add(new AdminManageMainFragment.StudentListItem(student.get("name"), false, student.get("username")));
//        }

        QuizQuestionsAdapter adapter = new QuizQuestionsAdapter(this.getContext(), questionList);
        RecyclerView rv = view.findViewById(R.id.RV_QuizQuestions);
        LinearLayoutManager llm =  new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.RV_QuizQuestions);

        LinearLayoutManager llm =  new LinearLayoutManager(getContext().getApplicationContext());
        rv.setLayoutManager(llm);
        QuizQuestionsAdapter ca = new QuizQuestionsAdapter(getContext().getApplicationContext(), questionList);
        rv.setAdapter(ca);

        view.findViewById(R.id.fab_addQuestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int questionListSize = questionList.size();
                // Add a new question to the questionList
                questionList.add(questionListSize -1, new QuizQuestion());
                // TODO: change the question text to say the question number if possible
                // Notify the adapter that the data has changed
                rv.getAdapter().notifyItemInserted(questionListSize);
                // Scroll to the bottom
                rv.smoothScrollToPosition(questionListSize);
            }
        });

        view.findViewById(R.id.fab_subtractQuestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int questionListSize = questionList.size();
                if (questionListSize > 1) {
                    // Remove the last question from questionList
                    questionList.remove(questionListSize - 1); //TODO: make this into an option instead of hard coded to be the last one added
                    rv.getAdapter().notifyItemRemoved(questionListSize - 1); //TODO: also make this variable (must match the number above though)
                    rv.smoothScrollToPosition(questionListSize - 2);
                } else {
                    Toast.makeText(getContext(), "Quiz must have at least one question.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}