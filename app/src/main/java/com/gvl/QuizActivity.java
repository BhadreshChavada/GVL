package com.gvl;

import android.app.Activity;
import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gvl.Model.QuizModel;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by AMD21 on 5/4/17.
 */

public class QuizActivity extends Activity {

    ArrayList<QuizModel> QuizArray = new ArrayList<>();
    TextView question, timertxt;
    RadioButton option1, option2, option3, option4;
    Button Submitbtn;
    int count = 0;
    CountDownTimer CountDownTimer;
    int Result = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        init();
    }

    void init() {

        InsertData();
        question = (TextView) findViewById(R.id.questiontxt);
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);
        option4 = (RadioButton) findViewById(R.id.option4);
        Submitbtn = (Button) findViewById(R.id.submit_button);
        timertxt = (TextView) findViewById(R.id.timertxt);
        setData();

        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(QuizActivity.this, "" + QuizArray.get(count).getAnswer(), Toast.LENGTH_SHORT).show();
                if (count <= 30) {

                    if (option1.isChecked()) {
                        if (QuizArray.get(count).getAnswer().equals("1")) {
                            Result++;
                        }

                    } else if (option2.isChecked()) {
                        if (QuizArray.get(count).getAnswer().equals("2")) {
                            Result++;
                        }

                    } else if (option3.isChecked()) {
                        if (QuizArray.get(count).getAnswer().equals("3")) {
                            Result++;
                        }

                    } else if (option4.isChecked()) {
                        if (QuizArray.get(count).getAnswer().equals("4")) {
                            Result++;
                        }

                    }
                    count++;
                    setData();
                }
            }
        });
    }

    private void setData() {

        if (count < 30) {
            question.setText(QuizArray.get(count).getQuestion());
            option1.setText(QuizArray.get(count).getOption1());
            option2.setText(QuizArray.get(count).getOption2());
            option3.setText(QuizArray.get(count).getOption3());
            option4.setText(QuizArray.get(count).getOption4());

            option1.setChecked(false);
            option2.setChecked(false);
            option3.setChecked(false);
            option4.setChecked(false);


            if (CountDownTimer != null)
                CountDownTimer.cancel();

//            android.os.Handler handler = new android.os.Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    count++;
//                    setData();
//                }
//            }, 60000);

            CountDownTimer = new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timertxt.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
//                mTextField.setText("done!");
                    count++;
                    setData();
                }
            }.start();


        } else {
            Toast.makeText(QuizActivity.this, "Quiz FINISH Result is ---  " + Result, Toast.LENGTH_SHORT).show();
        }
    }

    private void InsertData() {
        String[] Question = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] Option1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] Option2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] Option3 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] Option4 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] Answer = {"1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2"};

        for (int i = 0; i < 30; i++) {
            QuizModel quizModel = new QuizModel();
            quizModel.setQuestion(Question[i]);
            quizModel.setOption1(Option1[i]);
            quizModel.setOption2(Option2[i]);
            quizModel.setOption3(Option3[i]);
            quizModel.setOption4(Option4[i]);
            quizModel.setAnswer(Answer[i]);
            QuizArray.add(i, quizModel);
        }

    }
}
