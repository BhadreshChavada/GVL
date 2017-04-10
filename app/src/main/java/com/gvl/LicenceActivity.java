package com.gvl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
        setContentView(R.layout.activity_licence_list);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);


        init();
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
