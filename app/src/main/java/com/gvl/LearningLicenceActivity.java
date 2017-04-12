package com.gvl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gvl.Sqlite.GVLDatabase;

/**
 * Created by Bhadresh Chavada on 09-04-2017.
 */

public class LearningLicenceActivity extends AppCompatActivity implements View.OnClickListener {

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

    void init() {
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
        SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);


        if (v.getId() == R.id.btn_learning_licence_class_c) {

            GVLDatabase database = new GVLDatabase(LearningLicenceActivity.this);
            if (database.GetLearningLicByType("Class C", sp.getString("USERID", ""))) {
                Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
                intent.putExtra("Licence_Type", "Class C");
                startActivity(intent);
            } else {
                Toast.makeText(this, "You already Applied for Class C, Please apply after 7 days", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.btn_learning_licence_class_e) {


            GVLDatabase database = new GVLDatabase(LearningLicenceActivity.this);
            if (database.GetLearningLicByType("Class E", sp.getString("USERID", ""))) {
                Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
                intent.putExtra("Licence_Type", "Class E");
                startActivity(intent);
            } else {
                Toast.makeText(this, "You already Applied for Class E, Please apply after 7 days", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_learning_licence_class_f) {
            GVLDatabase database = new GVLDatabase(LearningLicenceActivity.this);
            if (database.GetLearningLicByType("Class F", sp.getString("USERID", ""))) {
                Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
                intent.putExtra("Licence_Type", "Class F");
                startActivity(intent);
            } else {
                Toast.makeText(this, "You already Applied for Class F, Please apply after 7 days", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_learning_licence_class_m) {
            GVLDatabase database = new GVLDatabase(LearningLicenceActivity.this);
            if (database.GetLearningLicByType("Class M", sp.getString("USERID", ""))) {
                Intent intent = new Intent(LearningLicenceActivity.this, ConfirmQuizActivity.class);
                intent.putExtra("Licence_Type", "Class M");
                startActivity(intent);
            } else {
                Toast.makeText(this, "You already Applied for Class M, Please apply after 7 days", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
