package com.gvl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Bhadresh Chavada on 09-04-2017.
 */

public class ConfirmQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_start_quiz;
    String Licence_Type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmquiz);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

        Licence_Type = getIntent().getStringExtra("Licence_Type");


        init();
    }

    void init() {
        btn_start_quiz = (Button) findViewById(R.id.btn_start_quiz);

        btn_start_quiz.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_quiz) {
            Intent intent = new Intent(ConfirmQuizActivity.this, QuizActivity.class);
            intent.putExtra("Licence_Type",Licence_Type);
            startActivity(intent);
            this.finish();
        }
    }
}
