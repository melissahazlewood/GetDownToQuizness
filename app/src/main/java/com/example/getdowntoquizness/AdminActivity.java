package com.example.getdowntoquizness;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends OptionsMenuActivityAdmin implements CommunicatorManageAcc{

    private Data userData;
    private String name;
    private Button btnManageAcc, btnCreateQuiz, btnAssignQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_layout);

        setScreenTitle();
        setBtnManageAccListener();
        btnCreateQuiz = findViewById(R.id.btnCreateQuiz);
        btnAssignQuiz = findViewById(R.id.btnAssignQuiz);


    }

    public void setBtnManageAccListener(){
        btnManageAcc = findViewById(R.id.btnManageAcc);
        btnManageAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passDataAdminToManageAcc(userData);
            }
        });
    }

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

    @Override
    public void passDataAdminToManageAcc(Data data) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentAdminMA = fm.findFragmentById(R.id.fragContAdminManageAcc);
        if(fragmentAdminMA == null){
            btnManageAcc.setVisibility(View.INVISIBLE);
            btnCreateQuiz.setVisibility(View.INVISIBLE);
            btnAssignQuiz.setVisibility(View.INVISIBLE);
            fragmentAdminMA = new AdminManageMainFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", userData);
            fragmentAdminMA.setArguments(bundle);
            fm.beginTransaction().replace(R.id.fragContAdminManageAcc, fragmentAdminMA)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        btnManageAcc.setVisibility(View.VISIBLE);
        btnCreateQuiz.setVisibility(View.VISIBLE);
        btnAssignQuiz.setVisibility(View.VISIBLE);
    }
}