package com.example.getdowntoquizness;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;

import java.util.ArrayList;

public class QuizQuestion implements Parcelable {
    String questionText;
    String answer1Text;
    String answer2Text;
    String answer3Text;
    String answer4Text;
    ArrayList<CheckBox> checkBoxes; //TODO: possibly remove
    ArrayList<Integer> correctCheckBoxChoices;

//    public QuizQuestion(String questionText, String answer1Text, String answer2Text, String answer3Text, String answer4Text,
//                        ArrayList<CheckBox> checkBoxes) {
//        this.questionText = questionText;
//        this.answer1Text = answer1Text;
//        this.answer2Text = answer2Text;
//        this.answer3Text = answer3Text;
//        this.answer4Text = answer4Text;
//        this.checkBoxes = new ArrayList<>();
//        this.checkBoxes.addAll(checkBoxes);
//      TODO: add correctCheckBoxChoices array initialization
//    }

    public QuizQuestion(String questionText, String answer1Text, String answer2Text, String answer3Text, String answer4Text,
                        ArrayList<Integer> correctCheckBoxChoices) {
        this.questionText = questionText;
        this.answer1Text = answer1Text;
        this.answer2Text = answer2Text;
        this.answer3Text = answer3Text;
        this.answer4Text = answer4Text;
        this.checkBoxes = new ArrayList<>(); //TODO: possibly remove
        this.correctCheckBoxChoices = correctCheckBoxChoices;
    }

    public QuizQuestion() {
        this.questionText = "";
        this.answer1Text = "";
        this.answer2Text = "";
        this.answer3Text = "";
        this.answer4Text = "";
        this.checkBoxes = new ArrayList<>();
        this.correctCheckBoxChoices = new ArrayList<>();
    }

    protected QuizQuestion(Parcel in) {
        questionText = in.readString();
        answer1Text = in.readString();
        answer2Text = in.readString();
        answer3Text = in.readString();
        answer4Text = in.readString();
    }

    public static final Creator<QuizQuestion> CREATOR = new Creator<QuizQuestion>() {
        @Override
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        @Override
        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };

    //Setters and getters for QuizQuestion fields
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public void setAnswer1Text(String answer1Text) {
        this.answer1Text = answer1Text;
    }
    public void setAnswer2Text(String answer2Text) {
        this.answer2Text = answer2Text;
    }
    public void setAnswer3Text(String answer3Text) {
        this.answer3Text = answer3Text;
    }
    public void setAnswer4Text(String answer4Text) {
        this.answer4Text = answer4Text;
    }
    public void setCheckBoxes(ArrayList<CheckBox> checkBoxes) {
        this.checkBoxes = checkBoxes;
    }
    public void setCorrectCheckBoxChoices(ArrayList<Integer> correctCheckBoxChoices) {
        this.correctCheckBoxChoices = correctCheckBoxChoices;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String getAnswer1Text() {
        return answer1Text;
    }
    public String getAnswer2Text() {
        return answer2Text;
    }
    public String getAnswer3Text() {
        return answer3Text;
    }
    public String getAnswer4Text() {
        return answer4Text;
    }
    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }
    public ArrayList<Integer> getCorrectCheckBoxChoices() {
        return correctCheckBoxChoices;
    }

    public void checkmarkCorrectAnswers(ArrayList<Integer> correctAnswerChoices) {
        for (int i : correctAnswerChoices) {
            checkBoxes.get(i).setChecked(true);
        }
    }

    public ArrayList<Integer> getCorrectAnswerChoices() {
        // Return the index (as an int) of the CheckBox objects that are associated with a correct answer
        ArrayList<Integer> correctChoices = new ArrayList<>();
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isChecked())
                correctChoices.add(i);
        }
        return correctChoices;
    }

    public ArrayList<CheckBox> getCorrectAnswerCheckboxes() {
        // Return the CheckBox objects that are associated with a correct answer
        ArrayList<CheckBox> correctChoices = new ArrayList<>();
        for (CheckBox cb : checkBoxes) {
            if (cb.isChecked())
                correctChoices.add(cb);
        }
        return correctChoices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeString(answer1Text);
        dest.writeString(answer2Text);
        dest.writeString(answer3Text);
        dest.writeString(answer4Text);
        dest.writeArray(correctCheckBoxChoices.toArray());
    }
}
