package com.gvl;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gvl.Adapter.LicenceAdapter;
import com.gvl.Model.LicenceModel;
import com.gvl.Sqlite.GVLDatabase;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by AMD21 on 10/4/17.
 */

public class LicenceActivity extends AppCompatActivity {

    private static final int DATE_PICKER_ID = 3;
    ListView licence_list;
    List<LicenceModel> list_licence = new ArrayList<>();
    private int year;
    private int month;
    private int day;
    TextView txt_licence_testdate, txt_licence_apply_date;
    Button btn_appointment;

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

        final TextView txt_licence_type, txt_licence_exam_score, txt_licence_status;
        final LinearLayout licence_no_ll, detail_ll;

        txt_licence_apply_date = (TextView) findViewById(R.id.txt_licence_apply_date);
        txt_licence_type = (TextView) findViewById(R.id.txt_licence_type);
        txt_licence_exam_score = (TextView) findViewById(R.id.txt_licence_exam_score);
        txt_licence_status = (TextView) findViewById(R.id.txt_licence_status);
        txt_licence_testdate = (TextView) findViewById(R.id.txt_licence_testdate);
        licence_no_ll = (LinearLayout) findViewById(R.id.licence_no_ll);
        detail_ll = (LinearLayout) findViewById(R.id.detail_ll);

        btn_appointment = (Button) findViewById(R.id.btn_appointment);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (licenceNo.getText().toString().length() == 10) {
                    GVLDatabase database = new GVLDatabase(LicenceActivity.this);
                    LicenceModel model = database.GetLearningLicByID(licenceNo.getText().toString());
                    if (model != null) {
//                        model.getID();
                        licence_no_ll.setVisibility(View.GONE);
                        detail_ll.setVisibility(View.VISIBLE);

                        txt_licence_apply_date.setText(model.getAPPLYDATE());
                        txt_licence_type.setText(model.getVEHICLE_TYPE());
                        txt_licence_exam_score.setText(model.getEXAMSCORE());
                        String status;
                        if (model.getSTATUS().equals("false"))
                            status = "Not Approve";
                        else
                            status = "Approve";


                        if (model.getAPPOINTMENTDATE().equals("")) {

                            btn_appointment.setBackgroundResource(R.drawable.bg_btn);
                            btn_appointment.setText("Select Date");

                            if ((Integer.parseInt(model.getEXAMSCORE()) < 24)) {
                                btn_appointment.setVisibility(View.GONE);
                                txt_licence_testdate.setClickable(false);
                                status = "You are not eligiable for Test Drive";
                            } else {
                                btn_appointment.setVisibility(View.GONE);
                                txt_licence_testdate.setClickable(true);
                            }
                        } else {
                            txt_licence_testdate.setText(model.getAPPOINTMENTDATE());
                            btn_appointment.setVisibility(View.GONE);
                            txt_licence_testdate.setClickable(false);
                        }

//                        else if ((Integer.parseInt(model.getEXAMSCORE()) > 2)) {
//                            btn_appointment.setVisibility(View.VISIBLE);
//                            txt_licence_testdate.setClickable(true);
//                            status = "You are not eligiable";
//                        } else {
//                            txt_licence_testdate.setClickable(true);
//                            btn_appointment.setVisibility(View.VISIBLE);
//                        }
                        txt_licence_status.setText(status);


//                        Toast.makeText(LicenceActivity.this, "-- : " + model.getID(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LicenceActivity.this, "Enter Valid Licence No.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LicenceActivity.this, "Enter 10 digit Learning Licence No", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_licence_testdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate();
            }
        });

        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GVLDatabase database = new GVLDatabase(LicenceActivity.this);
                database.updateAppointment(txt_licence_testdate.getText().toString(), licenceNo.getText().toString());

                Toast.makeText(LicenceActivity.this, "Appointment successfully appoint", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LicenceActivity.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void SelectDate() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        showDialog(DATE_PICKER_ID);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:


                Calendar c = Calendar.getInstance();

                String Mindate_str = txt_licence_apply_date.getText().toString();
                int day1 = Integer.parseInt(Mindate_str.substring(0, 2));

                c.set(2017, 04, day1);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, pickerListener, year, month + 1, day);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                return datePickerDialog;
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;


            // Show selected date
            txt_licence_testdate.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));

            btn_appointment.setVisibility(View.VISIBLE);


        }
    };


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


//        LicenceAdapter adapter = new LicenceAdapter(LicenceActivity.this, list_licence);
//        licence_list.setAdapter(adapter);

    }


}
