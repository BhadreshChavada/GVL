package com.gvl.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.gvl.Model.LicenceModel;
import com.gvl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMD21 on 10/4/17.
 */

public class LicenceAdapter extends BaseAdapter {

    Context context;
    List<LicenceModel> licence_array;
    LayoutInflater inflater;

    public LicenceAdapter(Context context, List<LicenceModel> licence_array) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_licence, null);


        TextView txt_licence_apply_date, txt_licence_type, txt_licence_exam_score, txt_licence_status;

        txt_licence_apply_date = (TextView) convertView.findViewById(R.id.txt_licence_apply_date);
        txt_licence_type = (TextView) convertView.findViewById(R.id.txt_licence_type);
        txt_licence_exam_score = (TextView) convertView.findViewById(R.id.txt_licence_exam_score);
        txt_licence_status = (TextView) convertView.findViewById(R.id.txt_licence_status);

        txt_licence_apply_date.setText(licence_array.get(position).getAPPLYDATE());
        txt_licence_type.setText(licence_array.get(position).getVEHICLE_TYPE());
        txt_licence_exam_score.setText(licence_array.get(position).getEXAMSCORE());
        String status;
        if (licence_array.get(position).getSTATUS() == false)
            status = "Not Approve";
        else
            status = "Approve";
        txt_licence_status.setText(status);


        return convertView;
    }
}
