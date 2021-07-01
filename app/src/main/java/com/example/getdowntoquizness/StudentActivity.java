package com.example.getdowntoquizness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.getdowntoquizness.main.SectionsPagerAdapter;
import com.example.getdowntoquizness.databinding.ActivityStudentLayoutBinding;
import com.google.android.material.tabs.TabLayout;

public class StudentActivity extends OptionsMenuActivityStudent {
    private Data userData;
    private String name;
    private ActivityStudentLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_layout);

        setScreenTitle();
        binding = ActivityStudentLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

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