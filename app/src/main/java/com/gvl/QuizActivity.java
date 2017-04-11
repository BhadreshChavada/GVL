package com.gvl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gvl.Model.LicenceModel;
import com.gvl.Model.QuizModel;
import com.gvl.Sqlite.GVLDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Handler;



public class QuizActivity extends AppCompatActivity {

//    https://www.simplifiedcoding.net/android-email-app-using-javamail-api-in-android-studio/

    ArrayList<QuizModel> QuizArray = new ArrayList<>();
    TextView question, timertxt;
    RadioButton option1, option2, option3, option4;
    Button Submitbtn;
    int count = 0;
    CountDownTimer CountDownTimer;
    int Result = 0;
    int temp = 0;
    private String Licence_Type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

        Licence_Type = getIntent().getStringExtra("Licence_Type");

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

        option1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Submitbtn.setText("Submit");
                } else if (option2.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option3.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option4.isChecked()) {
                    Submitbtn.setText("Submit");
                } else {
                    Submitbtn.setText("Next");
                }
            }
        });
        option2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Submitbtn.setText("Submit");
                } else if (option1.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option3.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option4.isChecked()) {
                    Submitbtn.setText("Submit");
                } else {
                    Submitbtn.setText("Next");
                }
            }
        });
        option3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Submitbtn.setText("Submit");
                } else if (option2.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option1.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option4.isChecked()) {
                    Submitbtn.setText("Submit");
                } else {
                    Submitbtn.setText("Next");
                }
            }
        });
        option4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Submitbtn.setText("Submit");
                } else if (option2.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option3.isChecked()) {
                    Submitbtn.setText("Submit");
                } else if (option1.isChecked()) {
                    Submitbtn.setText("Submit");
                } else {
                    Submitbtn.setText("Next");
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

            Submitbtn.setText("Next");

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

            CountDownTimer = new CountDownTimer(30000, 1000) {

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

            if (CountDownTimer != null)
                CountDownTimer.cancel();
            Toast.makeText(QuizActivity.this, "Quiz Successfully Completed", Toast.LENGTH_SHORT).show();
            GVLDatabase database = new GVLDatabase(QuizActivity.this);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
            String datetime = dateformat.format(c.getTime());
            System.out.println(datetime);

            SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);


            LicenceModel model = new LicenceModel();
            model.setAPPLYDATE(datetime);
            model.setEXAMSCORE(String.valueOf(Result));
            model.setSTATUS(false);
            model.setUSERID(sp.getString("USERID", ""));
            model.setVEHICLE_TYPE(Licence_Type);
            database.addLicenceRequest(model);

            Intent intent = new Intent(QuizActivity.this, MainMenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private void InsertData() {
        String[] Question = {"What percentage of the side vision you need is obscured by a normal motorcycle helmet?", "If you borrow someone else's motorcycle, you should...?", "You must take care to keep yourself warm and dry as chill can affect...?", "Your street legal motorcycle should have, as a minimum,...?", "The best protection for your face and eyes is what?", "You've just taken delivery of your first 'big' motorcycle. What's the first thing you should do?", "What does your odometer show you?", "If you need corrective lenses to ride, you should...?", "Before every ride you should...?", "If you're taking a 400 mile trip, when are you most likely, statistically, to have a crash?", "What is the main advantage of buying a good quality, motorcycle specific rain suit?", "Which of these should you be aware of as a possible hazard on your motorcycle boots?", "What does the choke do on a motorcycle?", "If you suspect even a minor technical problem with your motorcycle, what should you do?", "At below 30 mph, what reduction is there in the number and severity of head injuries in a crash?", "In every 100 motorcycle crashes, how many motorcyclists will receive head or neck injuries?", "You should not ride in gloves made of...?", "More than 50% of motorcycle crashes happen at what speed?", "On a normal motorcycle where will you find the engine cut-off switch?", "Which of these is likely to make your motorcycle helmet useless in a crash?", "You are 3 times more likely to die if you're not wearing a helmet and you crash at...?", "You only really need to wear a helmet when...?", "When you first get your motorcycle license, what sort of bike should you think about buying?", "You notice your high beam headlight isn't working. What should you do?", "When riding your motorcycle, the 'right' gear is...?", "You should wear your motorcycle jacket at all times, except...?", "To fit you properly, a motorcycle helmet should be...?", "Your priority when choosing clothing should be that...?", "Your brake light only needs to be operated by...?", "The rear brake pedal on nearly all motorcycles is where?"};
        String[] Option1 = {"5%", "Make all the checks you would on your own", "Your concentration", "Rear turn signals", "Goggles and bandanna", "Read the owner's manual", "The speed you're going", "Always ride with the face shield open", "Fill up with gas", "Once past mile 200", "It will make other drivers take you seriously", "Laces", "Helps with braking", "Take it to a mechanic when you have time", "40%", "20", "Nylon", "30 mph or less", "By the fuel supply valve", "Not keeping the liner clean", "35 mph", "When riding in the city", "One well within your capabilities", "Ride with the headlight off", "That which protects you", "When riding at low speed", "Tight", "It makes you more visible to others", "All of these", "Right-hand handlebar"};
        String[] Option2 = {"10%", "Ride completely normally from the outset", "All of these", "Front turn signals", "A windshield", "Take it for a slow ride", "Your engine revolutions", "Only ever ride in goggles", "Run the engine for 10 minutes", "Within 5 miles of home", "It will look good on your motorcycle", "Rubber heels", "Helps with starting", "Don't ride until you've fixed it", "20%", "5", "Leather", "40 mph plus", "By your right thumb", "Any of these", "Any speed", "You need one at all times", "The smallest one available", "Stick reflective tape over the headlight", "That which makes you feel professional", "On very hot sunny days", "Snug", "It shields you from the heat and cold", "Both brakes applied together", "Left-hand handlebar"};
        String[] Option3 = {"0%", "Figure out the controls by trial and error as you go along", "Your control sensitivity", "Brake light", "A face shield", "Climb on to try it for size", "The distance you've travelled", "Choose a helmet where they can fit under the face shield", "Wash your motorcycle", "Between hours 2 and 3", "It won't tear apart at high speed", "Steel toe caps", "Cuts off the fuel supply", "Keep riding and see if it worsens", "30%", "15", "Wool", "50 mph plus", "By your left thumb", "Not closing the face shield", "45 mph", "When riding at high speed", "The biggest you can afford", "Get it fixed at once", "That which impresses other riders", "There are no exceptions", "Loose", "It provides protection in a collision", "Your front brake", "Beneath your left foot"};
        String[] Option4 = {"20%", "Assume they have made the proper checks", "Your reaction time", "All of these", "Sunglasses and turned up collar", "Take it for a long ride", "Your fuel remaining", "Use your glasses as protection", "Inspect your motorcycle", " In the first 100 miles", "It will be better value for money", "Buckles", "Helps at high speed", "Mention it at your next regular service", "50%", "10", "Lorica", "60 mph plus", "Under your right foot", "Not securing the straps", "55 mph", "When taking a long trip", "The fastest you can afford", "Ride on low beam permanently", "That which matches your motorcycle", "When making very short trips", "Roomy", "All of these are vital", "Your rear brake", "Beneath your right foot"};
        String[] Answer = {"3", "1", "2", "4", "3", "1", "3", "3", "4", "2", "3", "1", "2", "2", "4", "1", "3", "1", "2", "4", "2", "2", "1", "3", "1", "3", "2", "4", "1", "4"};

        for (int i = 0; i < 30; i++) {
            QuizModel quizModel = new QuizModel();
            quizModel.setQuestion(i + 1 + ".  " + Question[i]);
            quizModel.setOption1(Option1[i]);
            quizModel.setOption2(Option2[i]);
            quizModel.setOption3(Option3[i]);
            quizModel.setOption4(Option4[i]);
            quizModel.setAnswer(Answer[i]);
            QuizArray.add(i, quizModel);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        temp = 1;
    }

    @Override
    protected void onStop() {
        super.onStop();
        temp = 1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (temp == 1) {
            Intent intent = new Intent(QuizActivity.this, MainMenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
