package com.gvl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gvl.Adapter.LicenceAdapter;
import com.gvl.Model.LicenceModel;
import com.gvl.Model.UserLicenceModel;
import com.gvl.Sqlite.GVLDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhadresh Chavada on 12-04-2017.
 */

public class LicenceApproveList extends AppCompatActivity {
    ListView licence_list;
    List<UserLicenceModel> list_licence = new ArrayList<>();
    Button btn_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence_list);

        init();
    }

    void init() {
        licence_list = (ListView) findViewById(R.id.licence_list);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        TextView norecord = (TextView) findViewById(R.id.norecord);

        SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);


        GVLDatabase database = new GVLDatabase(LicenceApproveList.this);
        List<UserLicenceModel> data = database.getContact();

//        for (int i = 0; i < data.size(); i++) {
//            if (data.get(i).getSTATUS().equals("false")) {
//                list_licence.add(data.get(i));
//            }
//        }
        if (data.size() > 0) {

            LicenceAdapter adapter = new LicenceAdapter(LicenceApproveList.this, data);
            licence_list.setAdapter(adapter);
            norecord.setVisibility(View.GONE);
        } else {
            licence_list.setVisibility(View.GONE);
            norecord.setVisibility(View.VISIBLE);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LicenceApproveList.this);
                alertDialogBuilder.setMessage("Do you want to Logout?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
//                                    Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();

                                SharedPreferences sp = getSharedPreferences("SHAREDPREFERENCE", MODE_PRIVATE);
                                sp.edit().clear().commit();
                                Intent intent = new Intent(LicenceApproveList.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                LicenceApproveList.this.finish();
                            }
                        });
                alertDialogBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                    finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }


}
