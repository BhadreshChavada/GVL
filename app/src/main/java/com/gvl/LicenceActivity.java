package com.gvl;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gvl.Adapter.LicenceAdapter;
import com.gvl.Sqlite.GVLDatabase;

/**
 * Created by AMD21 on 10/4/17.
 */

public class LicenceActivity extends AppCompatActivity {

    ListView licence_list;

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

        LicenceAdapter adapter = new LicenceAdapter(LicenceActivity.this, database.getLicenceRequest());
        licence_list.setAdapter(adapter);

    }
}
