package com.example.getdowntoquizness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    // Declaring variables
    private Button m_btnSignMeUp;
    private EditText mName, mUsername, mPassword, mRetypePassword;
    Context context;
    private AwesomeValidation mAwesomeValidation;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);


        context = getApplicationContext();

        Intent intent = getIntent();                    //getting the intent's data
        data = (Data) intent.getSerializableExtra("data"); //assigning data to data

        System.out.println("All students signup: " + data.getAllStudentsArray());


        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);// selecting the validation style
        enableReenterPassword();                        // Calling the enableReenterPassword method
        validate();
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // EnableReenterPassword method sets a listener on the password edit text box.
    // It enables the reenter password edit text box if the there is more than one character
    // in the password edit text box.
    public void enableReenterPassword(){
        final int MIN_NUM = 1;
        mPassword = findViewById(R.id.txtPassword);
        mRetypePassword = findViewById(R.id.txtRetypePassword);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //Listener on text changed (Password text box)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mPassword.getText().toString().length() < MIN_NUM) {
                    mRetypePassword.setEnabled(false);
                }
                else{
                    mRetypePassword.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // validate method validates the user's input on all edit text boxes
    public void validate(){
        m_btnSignMeUp = findViewById(R.id.btnSignMeUp);
        //validates username: no numbers at the beginning
        mAwesomeValidation.addValidation(this, R.id.txtUsername,
                "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z0-9\\s]{0,}$", R.string.name_error);

        //validates password: minimum 8 char, 1 letter, and 1 number
//        mAwesomeValidation.addValidation(this, R.id.txtPassword,
//                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", R.string.password_error);

        //validates retype password: same as password
//        mAwesomeValidation.addValidation(this, R.id.txtRetypePassword,
//                R.id.txtPassword, R.string.rePassword_error);

        //validates email address: email format only
//        mAwesomeValidation.addValidation(this, R.id.txtEmail, Patterns.EMAIL_ADDRESS,
//                R.string.email_error);

        //validates phone number: phone number format only
//        mAwesomeValidation.addValidation(this,R.id.txtPhone, "^[+]?[0-9]{10,13}$",
//                R.string.phone_error);
        m_btnSignMeUp.setOnClickListener(this);//sets listener on sign me up button.
    }
    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // onClick method validates username and password. If username does not exist, it stores
    // the username and password values on the data variable.
    @Override
    public void onClick(View view){
        if(view == m_btnSignMeUp){
            mUsername = findViewById(R.id.txtUsername);
            mPassword = findViewById(R.id.txtPassword);

            // checks if the username already exist  in the hashmap.
            boolean userExist = data.CheckUsername(mUsername.getText().toString());

            // If all fields are valid and username does not exist
            if(mAwesomeValidation.validate() && !userExist) {
                //adds the username and password to the hashmap
                data.AddCredential(mUsername.getText().toString(), mPassword.getText().toString(), "student");
                //creates an intent to the main activity
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("data", data);    // sends data through the intent
                startActivity(intent);                  //starts the main activity
                Toast toast = Toast.makeText(context, "Signup successful.",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, Gravity.CENTER,Gravity.CENTER);
                toast.show();
            }
            // If all fields are valid but the user exists
            else if(mAwesomeValidation.validate() && userExist){
                mUsername.requestFocus();   // set cursor focused on the username edit text box
                mUsername.selectAll();      // selects all text on the username edit text box

                Toast toast = Toast.makeText(context, "Username already exist. Try a different username.",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, Gravity.CENTER,Gravity.CENTER);
                toast.show();
            }
        }
    }


}