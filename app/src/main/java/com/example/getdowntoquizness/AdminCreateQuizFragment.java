package com.example.getdowntoquizness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class AdminCreateQuizFragment extends UserFragment implements AdapterView.OnItemSelectedListener, NewTopicDialogFragment.NewTopicDialogListener {

    // Views
    EditText mQuizName;
    Spinner mTopicSpinner;
    EditText mQuizTimeLimit;
    ArrayAdapter<String> topicAdapter;

//    public String quizName;
//    public int quizTimeLimit;
    private RecyclerView rv;
    private Button mBtn_DoneCreatingQuiz;

    public ArrayList<QuizQuestion> questionList = new ArrayList<>();
    private String quizName;
    private int quizTimeLimit;
    private ArrayList<String> topicList;
    private String chosenTopic;

    final static String ARG_QUIZ_QUESTION_LIST = "quizQuestionList";
    final static String ARG_QUIZ_NAME = "quizName";
    final static String ARG_QUIZ_TIME_LIMIT = "quizTimeLimit";
    final static String ARG_QUIZ_TOPIC = "quizTopic";
    final static String ARG_QUIZ_TOPIC_LIST = "quizTopicList";

    public AdminCreateQuizFragment(UserFragment fragment) {
        // Required empty public constructor
        super();
    }

    public static AdminCreateQuizFragment newInstance(String currentUsername) {
        AdminCreateQuizFragment fragment = new AdminCreateQuizFragment(UserFragment.newInstance(currentUsername));
        return fragment;
    }

    public static  AdminCreateQuizFragment newInstance(String currentUsername, ArrayList<String> topicList) {
        AdminCreateQuizFragment fragment = new AdminCreateQuizFragment(UserFragment.newInstance(currentUsername));
        fragment.topicList = topicList;
        System.out.println("TOPICLIST: " + topicList);

        return fragment;
    }

    public static AdminCreateQuizFragment newInstance(String currentUsername, String quizName, int quizTimeLimit,
                                                      ArrayList<QuizQuestion> questionList, String chosenTopic,
                                                      ArrayList<String> topicList) {
        AdminCreateQuizFragment fragment = new AdminCreateQuizFragment(UserFragment.newInstance(currentUsername));
        Bundle args = new Bundle();
        args.putString(ARG_QUIZ_NAME, quizName);
        args.putInt(ARG_QUIZ_TIME_LIMIT, quizTimeLimit);
        args.putSerializable(ARG_QUIZ_QUESTION_LIST, questionList); //TODO: is this serializable
        args.putString(ARG_QUIZ_TOPIC, chosenTopic);
        args.putStringArrayList(ARG_QUIZ_TOPIC_LIST, topicList);
//        args.putString(ARG_QUIZ_NAME, fragment.quizName);
//        args.putInt(ARG_QUIZ_TIME_LIMIT, fragment.quizTimeLimit);
//        args.putSerializable(ARG_QUIZ_QUESTION_LIST, fragment.questionList); //TODO: is this serializable
//        args.putString(ARG_QUIZ_TOPIC, fragment.chosenTopic);
//        args.putStringArrayList(ARG_QUIZ_TOPIC_LIST, fragment.topicList);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_CURRENT_USERNAME, currentUsername);
        outState.putString(ARG_QUIZ_NAME, quizName);
        outState.putInt(ARG_QUIZ_TIME_LIMIT, quizTimeLimit);
        outState.putSerializable(ARG_QUIZ_QUESTION_LIST, questionList);
        outState.putString(ARG_QUIZ_TOPIC, chosenTopic);
        outState.putStringArrayList(ARG_QUIZ_TOPIC_LIST, topicList);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentUsername = savedInstanceState.getString(ARG_CURRENT_USERNAME);
            quizName = savedInstanceState.getString(ARG_QUIZ_NAME);
            quizTimeLimit = savedInstanceState.getInt(ARG_QUIZ_TIME_LIMIT);
            questionList = (ArrayList<QuizQuestion>) savedInstanceState.getSerializable(ARG_QUIZ_QUESTION_LIST);
            chosenTopic = savedInstanceState.getString(ARG_QUIZ_TOPIC);
            topicList = savedInstanceState.getStringArrayList(ARG_QUIZ_TOPIC_LIST);
        }

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

        if (savedInstanceState == null) {
            questionList = new ArrayList<>();
            questionList.add(new QuizQuestion());

            topicList = new ArrayList<String>();
            topicList.add("New Topic");
            if(NewTopicDialogFragment.topic != null){
                topicAdapter.add(NewTopicDialogFragment.topic);
            }
            System.out.println("getArguments(): " + getArguments());
            System.out.println("savedInstanceState: " + savedInstanceState);
        } else {
            questionList.addAll(savedInstanceState.getParcelableArrayList(ARG_QUIZ_QUESTION_LIST));
            topicList.addAll(savedInstanceState.getStringArrayList(ARG_QUIZ_TOPIC_LIST));
            System.out.println("topicList: " + topicList);
        }

        mQuizName = view.findViewById(R.id.txtQuizTitle);
        mQuizTimeLimit = view.findViewById(R.id.txtTimeLimit);

        mTopicSpinner = view.findViewById(R.id.topicSpinner); //TODO: get the topics and also make the last one a button
        mTopicSpinner.setOnItemSelectedListener(this);


        topicAdapter = new ArrayAdapter<>(this.getActivity().getBaseContext(), android.R.layout.simple_spinner_dropdown_item, topicList);
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTopicSpinner.setAdapter(topicAdapter);

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

//        if (savedInstanceState == null) {
//            quizName = mQuizName.getText().toString();
//            quizTimeLimit = Integer.parseInt(mQuizTimeLimit.getText().toString());
//        } else {
        if (savedInstanceState != null) {
            quizName = savedInstanceState.getString(ARG_QUIZ_NAME);
            quizTimeLimit = savedInstanceState.getInt(ARG_QUIZ_TIME_LIMIT);
            chosenTopic = savedInstanceState.getString(ARG_QUIZ_TOPIC);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.RV_QuizQuestions);

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

//        if (savedInstanceState == null) {
//            quizName = mQuizName.getText().toString();
//            quizTimeLimit = Integer.parseInt(mQuizTimeLimit.getText().toString());
//        } else {
//            quizName = savedInstanceState.getString(ARG_QUIZ_NAME);
//            quizTimeLimit = savedInstanceState.getInt(ARG_QUIZ_TIME_LIMIT);
//            chosenTopic = savedInstanceState.getString(ARG_QUIZ_TOPIC);
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

//        quizName = mQuizName.getText().toString();
//        quizTimeLimit = Integer.parseInt(mQuizTimeLimit.getText().toString());
    }

    public String getQuizName() {
        return this.quizName;
    }

    public int getQuizTimeLimit() {
        return this.quizTimeLimit;
    }

    //For the topic spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TODO: fill in
        String topic = parent.getItemAtPosition(position).toString();
        if (topic.equals("New Topic")) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ((AdminActivity) getActivity()).showNewTopicDialog();
                    showNewTopicDialog();
                }
            });
        } else {
            chosenTopic = topic;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // For the new topic dialog box
//    public void showNewTopicDialog() {
//        // Create an instance of the dialog fragment and show it
//        DialogFragment dialog = NewTopicDialogFragment.newInstance();
//
////        DialogFragment dialog = new NewTopicDialogFragment();
//        dialog.show(getParentFragmentManager(), "NewTopicDialogFragment");
////        DialogFragment dialog = (DialogFragment) DialogFragment.instantiate(getActivity(), "NewTopicDialogFragment");
////        dialog.show(getFragmentManager(), "dialog");
////        dialog.show(getActivity().getSupportFragmentManager(), "NewTopicDialogFragment");
//    }

//    public void askForNewTopic() {
//        DialogFragment newFragment = new NewTopicDialogFragment();
//        newFragment.show(getActivity().getSupportFragmentManager(), "missiles");
//    }
//
//
//    // For the new topic dialog box
//    public void showNewTopicDialog() {
//        // Create an instance of the dialog fragment and show it
//        DialogFragment dialog = NewTopicDialogFragment.newInstance();
//
////        DialogFragment dialog = new NewTopicDialogFragment();
//        dialog.show(getParentFragmentManager(), "NewTopicDialogFragment");
////        DialogFragment dialog = (DialogFragment) DialogFragment.instantiate(getActivity(), "NewTopicDialogFragment");
////        dialog.show(getFragmentManager(), "dialog");
////        dialog.show(getActivity().getSupportFragmentManager(), "NewTopicDialogFragment");
//    }
//
////    public void askForNewTopic() {
////        DialogFragment newFragment = new NewTopicDialogFragment();
////        newFragment.show(getActivity().getSupportFragmentManager(), "missiles");
////    }
//
//    @Override
//    public void onDialogPositiveClick(DialogFragment dialog) {//}, String newTopic) {
//        String newTopic = getActivity().findViewById(R.id.txtNewTopic).toString();
//        this.topicsList.add(newTopic);
//    }
//
//    @Override
//    public void onDialogNegativeClick(DialogFragment dialog) {
//        dialog.dismiss();
//    }

    //TODO: somehow get what's in the create quiz view text boxes and such
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        AdminActivity activity = new AdminActivity();
//        if (context instanceof AdminActivity) {
//            activity = (AdminActivity) context;
//        }
//
//        activity.setQuizQuestionList(questionList);
//        activity.setQuizName(findViewById(R.id.));
//    }

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
    public void showNewTopicDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NewTopicDialogFragment();
//        dialog.show(getActivity().getSupportFragmentManager(), "NoticeDialogFragment");
        dialog.show(getParentFragmentManager(), "NoticeDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {//}, String newTopic) {
        String newTopic = ((EditText) dialog.getDialog().findViewById(R.id.txtNewTopic)).getText().toString();
        System.out.println("NEW TOPIC: " + newTopic);
        this.topicList.add(newTopic);
        topicAdapter.clear();
        topicAdapter.addAll(topicList);
//        AdminCreateQuizFragment fragment = (AdminCreateQuizFragment) fm.findFragmentByTag("createQuizFrag");
//        fm.beginTransaction().detach(fragment).commit();
//        fm.executePendingTransactions();
//        fm.beginTransaction().attach(fragment).commit();

//        startCreateQuizFragment(dialog.getView().getRootView());

//        fm.beginTransaction().replace(R.id.fragment_container, AdminCreateQuizFragment.newInstance(currentUsername,
//                quizName, quizTimeLimit, quizQuestionList, chosenTopic, topicList)).commit();
//        AdminCreateQuizFragment frag = AdminCreateQuizFragment.newInstance(currentUsername,
//                topicList);
//        fm.beginTransaction().replace(R.id.fragment_container, frag).commit();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }
}