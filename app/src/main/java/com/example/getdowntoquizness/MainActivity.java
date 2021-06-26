package com.example.getdowntoquizness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaring variables
    private Button m_btnSignUp;
    private Button m_btnLogin;
    private EditText mEditUsername, mEditPassword;
    private Data userData;
    private String username_entered, password_entered;
    Context context;
    private int numLoginTries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // wiringUp method gets references to widgets
    public void wiringUp(){
        m_btnLogin = findViewById(R.id.btnLogin);
        m_btnSignUp = findViewById(R.id.btnSignup);
        mEditUsername = findViewById(R.id.txtUsername);
        mEditPassword = findViewById(R.id.txtPassword);
        userData = new Data();
        context = getApplicationContext();
        numLoginTries = 0;                         //To count the number of unsuccessful login tries
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // signUpSelected method sets a listener waiting for the sign Up button to be clicked.
    // It sends the user and the to the data variable to the account sign up page.
    public void signUpSelected(){
        m_btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to move to signupActivity
                Intent intent = new Intent(context, SignupActivity.class);
                intent.putExtra("data", userData);      //Sending data through the intent
                startActivity(intent);                    //Moving to the other activity
            }
        });
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // login Selected method sets a listener waiting for the login button to be clicked.
    // It validates the user's credentials.
    public void loginSelected(){
        m_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username_entered = mEditUsername.getText().toString();
                password_entered = mEditPassword.getText().toString();

                Intent intent = getIntent();     //getting the intent in case user already signed up
                if(intent.hasExtra("data")) {//if there is data variable retrieved through the intent
                    userData = (Data) intent.getSerializableExtra("data");//Assigning data to data
                }

                // Check credentials
                if (userData.CheckCredentials(username_entered, password_entered)) {
                    // Go to welcome screen if authorized
                    if(userData.isAdmin(username_entered)) {
                        Intent I = new Intent(context, AdminActivity.class);
                        I.putExtra("username", username_entered);
                        startActivity(I);
                    }
                    else{
                        Intent I = new Intent(context, StudentActivity.class);
                        I.putExtra("username", username_entered);
                        startActivity(I);
                    }
                }
                //if more than three unsuccessful attempts
                else if (numLoginTries >= 3) {
                    Toast toast = Toast.makeText(context, "Multiple unsuccessful attempts to log in. Sign up!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, Gravity.CENTER,Gravity.CENTER);
                    toast.show();
                }
                else {
                    // Make toast saying incorrect username or password
                    Toast toast = Toast.makeText(context, "Incorrect username or password",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, Gravity.CENTER,Gravity.CENTER);
                    toast.show();
                    numLoginTries++;
                }

            }
        });
    }
}