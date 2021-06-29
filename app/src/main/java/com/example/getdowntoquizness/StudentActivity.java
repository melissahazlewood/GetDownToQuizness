package com.example.getdowntoquizness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StudentActivity extends OptionsMenuActivityStudent {
    private Data userData;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_layout);

        setScreenTitle();

    }

    public void setScreenTitle(){
        Intent intent = getIntent();
        if(intent.hasExtra("data")){
            userData = (Data) intent.getSerializableExtra("data");
        }
        if(intent.hasExtra("username")){
            name = (String) intent.getSerializableExtra("username");
        }
        setTitle("Student: " + name);
    }


}