package com.gvl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by Bhadresh Chavada on 03-04-2017.
 */

public class SplushScreen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splush);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);
                if (sp.getString("EMAIL", "").equals("") || sp.getString("EMAIL", "").equals(null)) {
                    Intent intent = new Intent(SplushScreen.this, LoginActivity.class);
                    startActivity(intent);
                    SplushScreen.this.finish();
                } else {
                    if (sp.getString("EMAIL", "").equals("admin@gmail.com")) {
                        Intent intent = new Intent(SplushScreen.this, LicenceApproveList.class);
                        startActivity(intent);
                        SplushScreen.this.finish();
                    } else {
                        Intent intent = new Intent(SplushScreen.this, MainMenuActivity.class);
                        startActivity(intent);
                        SplushScreen.this.finish();
                    }
                }
            }
        }, 3000);

    }
}
