package com.gvl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gvl.Adapter.LicenceAdapter;
import com.gvl.Model.LicenceModel;
import com.gvl.Sqlite.GVLDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMD21 on 10/4/17.
 */

public class LicenceActivity extends AppCompatActivity {

    ListView licence_list;
    List<LicenceModel> list_licence = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

        SetView();
//        init();
    }

    void SetView() {
        final EditText licenceNo = (EditText) findViewById(R.id.learning_licence_no_edt);
        Button submit_btn = (Button) findViewById(R.id.learning_lic_submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (licenceNo.getText().toString().length() == 10) {
                    GVLDatabase database = new GVLDatabase(LicenceActivity.this);
                    LicenceModel model = database.GetLearningLicByID(licenceNo.getText().toString());
                    if (model != null) {
//                        model.getID();
                        Toast.makeText(LicenceActivity.this, "-- : " + model.getID(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LicenceActivity.this, "Enter Valid Licence No.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LicenceActivity.this, "Enter 10 digit Learning Licence No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void init() {
        licence_list = (ListView) findViewById(R.id.licence_list);

        GVLDatabase database = new GVLDatabase(LicenceActivity.this);
        list_licence = database.getLicenceRequest();


        SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);

        for (int i = 0; i < list_licence.size(); i++) {
            if (!list_licence.get(i).getUSERID().equals(sp.getString("USERID", "-1"))) {
                list_licence.remove(i);
            }
        }


        LicenceAdapter adapter = new LicenceAdapter(LicenceActivity.this, list_licence);
        licence_list.setAdapter(adapter);

    }
}
