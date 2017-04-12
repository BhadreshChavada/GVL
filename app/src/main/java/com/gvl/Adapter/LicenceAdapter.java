package com.gvl.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.gvl.Model.LicenceModel;
import com.gvl.Model.UserLicenceModel;
import com.gvl.R;
import com.gvl.Sqlite.GVLDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMD21 on 10/4/17.
 */

public class LicenceAdapter extends BaseAdapter {

    Context context;
    List<UserLicenceModel> licence_array;
    LayoutInflater inflater;

    public LicenceAdapter(Context context, List<UserLicenceModel> licence_array) {
        this.context = context;
        this.licence_array = licence_array;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return licence_array.size();
    }

    @Override
    public Object getItem(int position) {
        return licence_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_licence, null);


        TextView txt_licence_apply_date, txt_licence_type, txt_licence_exam_score, txt_licence_status, txt_firstname, txt_lastname;
        Button btn_licence_reject, btn_licence_approve;

        txt_licence_apply_date = (TextView) convertView.findViewById(R.id.txt_licence_apply_date);
        txt_licence_type = (TextView) convertView.findViewById(R.id.txt_licence_type);
        txt_licence_exam_score = (TextView) convertView.findViewById(R.id.txt_licence_exam_score);
        txt_licence_status = (TextView) convertView.findViewById(R.id.txt_licence_status);
        txt_firstname = (TextView) convertView.findViewById(R.id.txt_first_name);
        txt_lastname = (TextView) convertView.findViewById(R.id.txt_last_name);
        btn_licence_reject = (Button) convertView.findViewById(R.id.btn_licence_reject);
        btn_licence_approve = (Button) convertView.findViewById(R.id.btn_licence_approve);

        txt_licence_apply_date.setText(licence_array.get(position).getAPPLYDATE());
        txt_licence_type.setText(licence_array.get(position).getVEHICLE_TYPE());
        txt_licence_exam_score.setText(licence_array.get(position).getEXAMSCORE());
        txt_firstname.setText(licence_array.get(position).getFNAME());
        txt_lastname.setText(licence_array.get(position).getLNAME());
        String status;

        if (licence_array.get(position).getSTATUS().equals("false")) {
            status = "Not Approve";
            btn_licence_approve.setVisibility(View.VISIBLE);
            btn_licence_reject.setVisibility(View.VISIBLE);
        } else {
            status = "Approve";
            btn_licence_approve.setVisibility(View.GONE);
            btn_licence_reject.setVisibility(View.GONE);
        }
        txt_licence_status.setText(status);


        btn_licence_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GVLDatabase database = new GVLDatabase(context);
                database.updateAppointmentStatus("true", licence_array.get(position).getLicenceNO());
                licence_array.remove(position);
                notifyDataSetChanged();
            }
        });

        btn_licence_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GVLDatabase database = new GVLDatabase(context);
                database.updateAppointmentStatus("false", licence_array.get(position).getLicenceNO());
                licence_array.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
