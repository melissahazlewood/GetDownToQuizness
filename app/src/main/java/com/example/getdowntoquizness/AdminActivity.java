package com.example.getdowntoquizness;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AdminActivity extends OptionsMenuActivityAdmin implements CommunicatorManageAcc {
    private Data userData;
    private String name;
    private Button btnManageAcc, btnCreateQuiz, btnAssignQuiz;

    FragmentManager fm = getSupportFragmentManager();

    ArrayList<String> selectedStudents;
    private String currentUsername;

    private ArrayList<QuizQuestion> quizQuestionList;
    private String quizName;
    private int quizTimeLimit;
    private String chosenTopic;
    private ArrayList<String> topicList;

    final static String ARG_QUIZ_QUESTION_LIST = "quizQuestionList";
    final static String ARG_QUIZ_NAME = "quizName";
    final static String ARG_QUIZ_TIME_LIMIT = "quizTimeLimit";
    final static String ARG_QUIZ_TOPIC = "quizTopic";
    final static String ARG_QUIZ_TOPIC_LIST = "quizTopicList";

    private final static String ARG_CURRENT_USERNAME = "currentUsername";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_layout);

//        setScreenTitle();
//        setBtnManageAccListener();
//        btnCreateQuiz = findViewById(R.id.btnCreateQuiz);
//        btnAssignQuiz = findViewById(R.id.btnAssignQuiz);

        Intent intent = getIntent(); //TODO: put this code block into a function like setScreenTitle() ?
        if (intent.hasExtra(ARG_CURRENT_USERNAME)) {
            this.currentUsername = intent.getStringExtra(ARG_CURRENT_USERNAME);
            setTitle("Admin: " + currentUsername);
        }

        startHomeFragment();

        quizQuestionList = new ArrayList<>();
        topicList = new ArrayList<>();
        if (savedInstanceState != null) {
            setQuizQuestionList(savedInstanceState.getParcelableArrayList(ARG_QUIZ_QUESTION_LIST));
            quizName = savedInstanceState.getString(ARG_QUIZ_NAME);
            quizTimeLimit = savedInstanceState.getInt(ARG_QUIZ_TIME_LIMIT);
            chosenTopic = savedInstanceState.getString(ARG_QUIZ_TOPIC);
            topicList = savedInstanceState.getStringArrayList(ARG_QUIZ_TOPIC_LIST);
        }
    }


//    public void setBtnManageAccListener(){
//        btnManageAcc = findViewById(R.id.btnManageAcc);
//        btnManageAcc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                passDataAdminToManageAcc(userData);
//            }
//        });
//    }

    public void setScreenTitle(){
        Intent intent = getIntent();
        if(intent.hasExtra("data")){
            userData = (Data) intent.getSerializableExtra("data");
        }
        if(intent.hasExtra("username")){
            name = (String) intent.getSerializableExtra("username");
        }

        setTitle("Admin: " + name);
    }

//    @Override
//<<<<<<< HEAD
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.menu_home:
                // Toast ....
                //return true;
                startHomeFragment(findViewById(R.id.fragment_container));
                break;
            case R.id.menu_admin_logout:
                //return true;
                showMessage("You logged out successfully");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_admin_help:
                //return true;
                showMessage("Help");
                Fragment fragmentTemp = new HelpFragment();
                fm.beginTransaction().replace(R.id.fragment_container, fragmentTemp)
                        .addToBackStack(null).commit();
                break;
            case R.id.menu_admin_about:
                showMessage("About");
                fragmentTemp = new AboutFragment();
                fm.beginTransaction().replace(R.id.fragment_container, fragmentTemp)
                        .addToBackStack(null).commit();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu_layout, menu);
        return true;
    }
//
//    @Override
//=======
//>>>>>>> AlBranch
    public void passDataAdminToManageAcc(Data data) {
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragmentAdminMA = fm.findFragmentById(R.id.fragContAdminManageAcc);
//        if(fragmentAdminMA == null){
//            btnManageAcc.setVisibility(View.INVISIBLE);
//            btnCreateQuiz.setVisibility(View.INVISIBLE);
//            btnAssignQuiz.setVisibility(View.INVISIBLE);
//            fragmentAdminMA = new AdminManageMainFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("data", userData);
//            fragmentAdminMA.setArguments(bundle);
//            fm.beginTransaction().replace(R.id.fragContAdminManageAcc, fragmentAdminMA)
//                    .addToBackStack(null).commit();
//        }
    }

    public void startHomeFragment() {
        fm.beginTransaction()
                .add(R.id.fragment_container, AdminHomeFragment.newInstance(currentUsername), "homeFrag")
                .commit();
    }

    public void startHomeFragment(View view) {
        fm.beginTransaction()
                .replace(R.id.fragment_container, AdminHomeFragment.newInstance(currentUsername), null)
                .commit();
    }

    public void startManageAccountsFragment(View view) {
        fm.beginTransaction()
                .replace(R.id.fragment_container, AdminManageMainFragment.newInstance(currentUsername), "accountManagerFrag")
                .commit();
    }

    public void startCreateQuizFragment(View view) {
        fm.beginTransaction()
                .replace(R.id.fragment_container, AdminCreateQuizFragment.newInstance(currentUsername), "createQuizFrag")
                .commit();
    }

    public void removeSelectedStudents(View view) {
        selectedStudents = getSelectedStudents();
        if (selectedStudents != null) {
            System.out.println(selectedStudents);

            //remove selected students from DB and update UI
            removeSelectedStudentsFromDB(selectedStudents);
            startManageAccountsFragment(view.getRootView());
        }
    }

    public ArrayList<String> getSelectedStudents() {
        ArrayList<String> selectedStudentsList = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<ArrayList<String>> future = executor.submit(() -> {
            DBHandler db = new DBHandler(getApplicationContext());
            return db.getSelectedHandler();
        });

        try {
            selectedStudentsList = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        return selectedStudentsList;
    }

    public void removeSelectedStudentsFromDB(ArrayList<String> selectedStudents) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            DBHandler db = new DBHandler(getApplicationContext());
            db.removeSelectedStudentsHandler(selectedStudents);
        });

        executor.shutdown();
    }

    public void doneCreatingQuiz(View view) {
        // TODO: Store quiz details in database
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            DBHandler db = new DBHandler(getApplicationContext());
            db.addQuizHandler(chosenTopic, quizName, quizTimeLimit, quizQuestionList);
        });

        executor.shutdown();

        // Return to home screen
        startHomeFragment(view.getRootView());
    }

    public void setQuizQuestionList(ArrayList<QuizQuestion> questionList) {
        this.quizQuestionList = questionList;
    }

//    public void setQuizName(String name) {
//        this.quizName = name;
//    }
//
//    public void setQuizTimeLimit(int timeLimit) {
//        this.quizTimeLimit = timeLimit;
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        btnManageAcc.setVisibility(View.VISIBLE);
        btnCreateQuiz.setVisibility(View.VISIBLE);
        btnAssignQuiz.setVisibility(View.VISIBLE);
    }

//    //For the topic spinner
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //TODO: fill in
//        String topic = parent.getItemAtPosition(position).toString();
//        if (topic.equals("New Topic")) {
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showNewTopicDialog();
//                }
//            });
//        } else {
//            chosenTopic = topic;
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }


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
//    public void showNewTopicDialog() {
//        // Create an instance of the dialog fragment and show it
//        DialogFragment dialog = new NewTopicDialogFragment();
//        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
//    }
//
//    @Override
//    public void onDialogPositiveClick(DialogFragment dialog) {//}, String newTopic) {
//        String newTopic = ((EditText) dialog.getDialog().findViewById(R.id.txtNewTopic)).getText().toString();
//        System.out.println("NEW TOPIC: " + newTopic);
//        this.topicList.add(newTopic);
////        AdminCreateQuizFragment fragment = (AdminCreateQuizFragment) fm.findFragmentByTag("createQuizFrag");
////        fm.beginTransaction().detach(fragment).commit();
////        fm.executePendingTransactions();
////        fm.beginTransaction().attach(fragment).commit();
//
////        startCreateQuizFragment(dialog.getView().getRootView());
//
////        fm.beginTransaction().replace(R.id.fragment_container, AdminCreateQuizFragment.newInstance(currentUsername,
////                quizName, quizTimeLimit, quizQuestionList, chosenTopic, topicList)).commit();
//        AdminCreateQuizFragment frag = AdminCreateQuizFragment.newInstance(currentUsername,
//                topicList);
//        fm.beginTransaction().replace(R.id.fragment_container, frag).commit();
//    }
//
//    @Override
//    public void onDialogNegativeClick(DialogFragment dialog) {
//        dialog.dismiss();
//    }
}