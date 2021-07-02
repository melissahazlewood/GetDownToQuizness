package com.example.getdowntoquizness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class QuizTakingActivity extends AppCompatActivity {
    private TextView txtQuestion1, txtQuestion2, txtQuestion3, txtQuestion4;
    private CheckBox checkBoxQues1Box1, checkBoxQues1Box2, checkBoxQues1Box3, checkBoxQues1Box4;
    private CheckBox checkBoxQues2Box1, checkBoxQues2Box2, checkBoxQues2Box3, checkBoxQues2Box4;
    private CheckBox checkBoxQues3Box1, checkBoxQues3Box2, checkBoxQues3Box3, checkBoxQues3Box4;
    private CheckBox checkBoxQues4Box1, checkBoxQues4Box2, checkBoxQues4Box3, checkBoxQues4Box4;
    private String question1, question2, question3, question4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taking);

        wiringUp();
        setQuestions();

    }
    public void setQuestions(){
        txtQuestion1.setText(question1);
        txtQuestion2.setText(question2);
        txtQuestion3.setText(question3);
        txtQuestion4.setText(question4);

        checkBoxQues1Box1.setText("80");
        checkBoxQues1Box2.setText("60");
        checkBoxQues1Box3.setText("40");
        checkBoxQues1Box4.setText("100");

        checkBoxQues2Box1.setText("11");
        checkBoxQues2Box2.setText("10");
        checkBoxQues2Box3.setText("9");
        checkBoxQues2Box4.setText("15");

        checkBoxQues3Box1.setText("20");
        checkBoxQues3Box2.setText("18");
        checkBoxQues3Box3.setText("21");
        checkBoxQues3Box4.setText("22");

        checkBoxQues4Box1.setText("10");
        checkBoxQues4Box2.setText("15");
        checkBoxQues4Box3.setText("11");
        checkBoxQues4Box4.setText("9");

    }
    public void wiringUp(){
        txtQuestion1 = findViewById(R.id.txtQuestion1);
        txtQuestion2 = findViewById(R.id.txtQuestion2);
        txtQuestion3 = findViewById(R.id.txtQuestion3);
        txtQuestion4 = findViewById(R.id.txtQuestion4);

        checkBoxQues1Box1 = findViewById(R.id.checkbox1Quest1);
        checkBoxQues1Box2 = findViewById(R.id.checkbox1Quest2);
        checkBoxQues1Box3 = findViewById(R.id.checkbox1Quest3);
        checkBoxQues1Box4 = findViewById(R.id.checkbox1Quest4);

        checkBoxQues2Box1 = findViewById(R.id.checkbox2Quest1);
        checkBoxQues2Box2 = findViewById(R.id.checkbox2Quest2);
        checkBoxQues2Box3 = findViewById(R.id.checkbox2Quest3);
        checkBoxQues2Box4 = findViewById(R.id.checkbox2Quest4);

        checkBoxQues3Box1 = findViewById(R.id.checkbox3Quest1);
        checkBoxQues3Box2 = findViewById(R.id.checkbox3Quest2);
        checkBoxQues3Box3 = findViewById(R.id.checkbox3Quest3);
        checkBoxQues3Box4 = findViewById(R.id.checkbox3Quest4);

        checkBoxQues4Box1 = findViewById(R.id.checkbox3Quest1);
        checkBoxQues4Box2 = findViewById(R.id.checkbox3Quest2);
        checkBoxQues4Box3 = findViewById(R.id.checkbox3Quest3);
        checkBoxQues4Box4 = findViewById(R.id.checkbox3Quest4);

        question1 = "What is 20 x 3: ";
        question2 = "What is 64 / 4: ";
        question3 = "What is 13 + 9: ";
        question4 = "What is 18 - 7: ";

    }
}