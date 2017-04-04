package com.gvl;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Bhadresh Chavada on 04-04-2017.
 */

public class OptionSignup extends Activity implements View.OnClickListener {

    Button btn_signIN, btn_signUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        init();
    }

    void init() {
        btn_signIN = (Button) findViewById(R.id.btn_signin);
        btn_signUp = (Button) findViewById(R.id.btn_signup);

        btn_signIN.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin:
                Intent intent = new Intent(OptionSignup.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                Intent intent_Signup = new Intent(OptionSignup.this, SignUpActivity.class);
                startActivity(intent_Signup);
                break;


        }


    }
}
