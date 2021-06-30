package com.example.getdowntoquizness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StudentActivity extends AppCompatActivity {
    private Data userData;
    private String name;
    private String currentUsername;

    private final static String ARG_CURRENT_USERNAME = "currentUsername";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_layout);

        Intent intent = getIntent(); //TODO: put this code block into a function like setScreenTitle() ?
        if (intent.hasExtra(ARG_CURRENT_USERNAME)) {
            this.currentUsername = intent.getStringExtra(ARG_CURRENT_USERNAME);
            setTitle("Student: " + currentUsername);
        }

//        setScreenTitle();
    }

    public void setScreenTitle(){
//        Intent intent = getIntent();
//        if(intent.hasExtra("data")){
//            userData = (Data) intent.getSerializableExtra("data");
//        }
//        if(intent.hasExtra("username")){
//            name = (String) intent.getSerializableExtra("username");
//        }

        setTitle("Student: " + name);

        //TODO: figure out how to best represent and send data between activities
//        DBHandler db = new DBHandler(this);
//        String username = db.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_student_home:

            case R.id.menu_student_name:

            case R.id.menu_student_logout:

            case R.id.menu_student_editProfile:

            case R.id.menu_student_changePassword:

            case R.id.menu_student_help:

            case R.id.menu_student_about:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.student_menu_layout, menu);
        return true;
    }
}