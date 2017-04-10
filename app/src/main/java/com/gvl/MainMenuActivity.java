package com.gvl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by AMD21 on 5/4/17.
 */

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_logout, btn_learning_licence, btn_apply_licence;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

        init();
    }

    void init() {

        btn_learning_licence = (Button) findViewById(R.id.btn_learning_licence);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_apply_licence = (Button) findViewById(R.id.btn_apply_licence);

//        btn_licence, btn_logout;


        btn_learning_licence.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_apply_licence.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_learning_licence) {
            Intent intent = new Intent(MainMenuActivity.this, LearningLicenceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_logout) {
            SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }
        else if(v.getId() == R.id.btn_apply_licence){
            Intent intent = new Intent(MainMenuActivity.this, LicenceActivity.class);
            startActivity(intent);
        }

    }
}
