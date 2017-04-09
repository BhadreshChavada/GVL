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

public class LearningLicenceActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_classm, btn_classf, btn_classe, btn_classc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence_type);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);
        init();
    }
    void init(){
        btn_classm = (Button) findViewById(R.id.btn_learning_licence_class_m);
        btn_classf = (Button) findViewById(R.id.btn_learning_licence_class_f);
        btn_classe = (Button) findViewById(R.id.btn_learning_licence_class_e);
        btn_classc = (Button) findViewById(R.id.btn_learning_licence_class_c);

        btn_classm.setOnClickListener(this);
        btn_classf.setOnClickListener(this);
        btn_classe.setOnClickListener(this);
        btn_classc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_learning_licence_class_c ) {
            Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_learning_licence_class_e) {
            Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_learning_licence_class_f) {
            Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_learning_licence_class_m) {
            Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
            startActivity(intent);
        }
    }
}
