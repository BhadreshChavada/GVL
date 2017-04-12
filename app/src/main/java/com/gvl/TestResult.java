package com.gvl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bhadresh Chavada on 12-04-2017.
 */

public class TestResult extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);
        init();

    }

    void init() {

        String score = getIntent().getStringExtra("Score");
        String LicNo = getIntent().getStringExtra("LicNo");
        TextView confirm_txt = (TextView) findViewById(R.id.confirm_txt);
        ImageView img_status = (ImageView) findViewById(R.id.img_status);

        if (Integer.parseInt(score) > 24) {
            img_status.setImageResource(R.drawable.ic_checked);
            confirm_txt.setText("Licence No : " + LicNo + "\n You Complete the Test. \n Your Score is : " + score + "\n Total attempt question : " + getIntent().getStringExtra("Checked"));
        } else {
            img_status.setImageResource(R.drawable.ic_cancel);
            confirm_txt.setText("You not Complete the Test. \n Your Score is : " + score + "\n Total attempt question : " + getIntent().getStringExtra("Checked") + "\n Try Again after 1 Month.");
        }
        Button btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setText("Back to Home");
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResult.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TestResult.this, MainMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
