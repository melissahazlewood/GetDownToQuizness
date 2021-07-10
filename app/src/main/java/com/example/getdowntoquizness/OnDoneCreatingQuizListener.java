package com.example.getdowntoquizness;

import java.util.ArrayList;

public interface OnDoneCreatingQuizListener {
    void onDoneCreatingQuiz(String name, String topic, int timeLimit,
                                    ArrayList<QuizQuestion> questions, ArrayList<Integer> correctAnswers);
}
