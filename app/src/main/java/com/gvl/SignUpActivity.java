package com.gvl;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.gvl.Model.Contact;
import com.gvl.Sqlite.GVLDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bhadresh Chavada on 04-04-2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int DATE_PICKER_ID = 1;
    EditText birthdate_edt, fname_edt, lname_edt, password_edt, socialsec_1, socialsec_2, socialsec_3, address_1_edt, address_2_edt, address_city_edt, address_state_edt, address_zip_edt, address_country_edt;
    AutoCompleteTextView email_edt;
    private static final int SELECT_PICTURE = 10;
    Button btn_gallery, btn_camera, btn_submit;
    ImageView displayImage;
    private String selectedImagePath = null;
    private int CAMERA_REQUEST = 20;
    RadioButton rb_male, rb_female;
    Spinner bloodgroup;
    String str_image;
    int temp = 0;
    int birthtemp = 0;


    private int year;
    private int month;
    private int day;
    private byte[] byteArray;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

        init();
    }

    void init() {
        birthdate_edt = (EditText) findViewById(R.id.birthdate_edt);
        fname_edt = (EditText) findViewById(R.id.fname_edt);
        lname_edt = (EditText) findViewById(R.id.lname_edt);
        email_edt = (AutoCompleteTextView) findViewById(R.id.email);
        password_edt = (EditText) findViewById(R.id.password);
        btn_submit = (Button) findViewById(R.id.email_sign_in_button);
        btn_gallery = (Button) findViewById(R.id.btn_gallery);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        displayImage = (ImageView) findViewById(R.id.img_display);
        rb_male = (RadioButton) findViewById(R.id.radioMale);
        rb_female = (RadioButton) findViewById(R.id.radioFemale);
        bloodgroup = (Spinner) findViewById(R.id.spinner_blood_group);
        socialsec_1 = (EditText) findViewById(R.id.ssn1_edt);
        socialsec_2 = (EditText) findViewById(R.id.ssn2_edt);
        socialsec_3 = (EditText) findViewById(R.id.ssn3_edt);
        address_1_edt = (EditText) findViewById(R.id.address_edt);
        address_2_edt = (EditText) findViewById(R.id.address2_edt);
        address_city_edt = (EditText) findViewById(R.id.address_city_edt);
        address_state_edt = (EditText) findViewById(R.id.address_satte_edt);
        address_zip_edt = (EditText) findViewById(R.id.address_zip_edt);
        address_country_edt = (EditText) findViewById(R.id.address_country_edt);

        btn_camera.setOnClickListener(this);
        btn_gallery.setOnClickListener(this);
        birthdate_edt.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.birthdate_edt:
                birthtemp = 1;
                SelectDate();
                break;
            case R.id.btn_gallery:
                if (ContextCompat.checkSelfPermission(SignUpActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SignUpActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                } else {
                    OpenGallery();
                }
                break;
            case R.id.btn_camera:
                if (ContextCompat.checkSelfPermission(SignUpActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SignUpActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            2);
                } else {
                    OpenCamera();
                }
                break;
            case R.id.email_sign_in_button:
                Registration();
                break;
        }
    }

    private void Registration() {

        boolean cancel = false;
        View focusView = null;
        if (fname_edt.getText().toString().length() == 0) {
            fname_edt.setError("First Name is required");
            focusView = fname_edt;
            cancel = true;
        } else if (lname_edt.getText().toString().length() == 0) {
            lname_edt.setError("Last Name is required");
            focusView = lname_edt;
            cancel = true;
        } else if (birthtemp == 0) {
            Toast.makeText(this, "Choose Birthdate", Toast.LENGTH_SHORT).show();
        } else if (bloodgroup.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Choose Blood Group", Toast.LENGTH_SHORT).show();
        } else if (socialsec_1.getText().toString().length() != 3) {
            socialsec_1.setError("Social Security have 3 digit");
            focusView = socialsec_1;
            cancel = true;
        } else if (socialsec_2.getText().toString().length() != 2) {
            socialsec_2.setError("Social Security have 2 digit");
            focusView = socialsec_2;
            cancel = true;
        } else if (socialsec_3.getText().toString().length() != 4) {
            socialsec_3.setError("Social Security have 4 digit");
            focusView = socialsec_3;
            cancel = true;
        } else if (address_1_edt.getText().toString().length() == 0) {
            address_1_edt.setError("Address Line 1 is required");
            focusView = address_1_edt;
            cancel = true;
        } else if (address_2_edt.getText().toString().length() == 0) {
            address_2_edt.setError("Address Line 2 is required");
            focusView = address_2_edt;
            cancel = true;
        } else if (address_city_edt.getText().toString().length() == 0) {
            address_city_edt.setError("City is required");
            focusView = address_city_edt;
            cancel = true;
        } else if (address_state_edt.getText().toString().length() == 0) {
            address_state_edt.setError("State/Province/Region is required");
            focusView = address_state_edt;
            cancel = true;
        } else if (address_zip_edt.getText().toString().length() == 0) {
            address_zip_edt.setError("ZipCode is required");
            focusView = address_zip_edt;
            cancel = true;
        } else if (address_country_edt.getText().toString().length() == 0) {
            address_country_edt.setError("Country is required");
            focusView = address_country_edt;
            cancel = true;
        } else if (email_edt.getText().toString().length() == 0) {
            email_edt.setError("Email is required");
            focusView = email_edt;
            cancel = true;
        } else if (!isValidEmail(email_edt.getText().toString())) {
            email_edt.setError(getString(R.string.error_invalid_email));
            focusView = email_edt;
            cancel = true;
        } else if (password_edt.getText().toString().length() == 0) {
            password_edt.setError("Password is required");
            focusView = password_edt;
            cancel = true;
        } else if (temp != 1) {
            Toast.makeText(this, "Upload Image", Toast.LENGTH_SHORT).show();
        }

        // signin - email is required,signup color- address country add


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else if (temp == 0 && birthtemp == 0 && bloodgroup.getSelectedItemPosition() == 0) {

        } else if (fname_edt.getText().toString().length() > 0 && lname_edt.getText().toString().length() > 0 && email_edt.getText().toString().length() > 0 && password_edt.getText().toString().length() > 0 && temp == 1) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Do you want to Submit?");
            alertDialogBuilder.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
//                                    Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                            Contact contact = new Contact();
                            contact.setFNAME(fname_edt.getText().toString());
                            contact.setLNAME(lname_edt.getText().toString());
                            contact.setADDRESS(address_1_edt.getText().toString() + "|" + address_2_edt.getText().toString() + "|" + address_city_edt.getText().toString() + "|" + address_state_edt.getText().toString() + "|" + address_zip_edt.getText().toString() + "|" + address_country_edt.getText().toString());
                            contact.setBIRTHDATE(birthdate_edt.getText().toString());
                            contact.setEMAIL(email_edt.getText().toString());
                            contact.setPASSWORD(password_edt.getText().toString());
                            contact.setIMAGE(str_image);
                            contact.setBLOODGROP(bloodgroup.getSelectedItem().toString());
                            if (rb_male.isSelected()) {
                                contact.setGENDER("Male");
                            } else {
                                contact.setGENDER("Female");
                            }
                            GVLDatabase database = new GVLDatabase(SignUpActivity.this);
                            database.addRegistration(contact);
                            Intent intent = new Intent(SignUpActivity.this, ConfirmActivity.class);
                            startActivity(intent);
                            SignUpActivity.this.finish();

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


        } else

        {
//            Toast.makeText(this, "Enter All the Field", Toast.LENGTH_SHORT).show();
        }

        // fna,lname,email,pass,birthdate, gender,bloodgroup,address-US format,
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

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                Calendar c = Calendar.getInstance();
                c.set(year - 18, month + 1, day);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, pickerListener, year - 18, month + 1, day);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
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
            birthdate_edt.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));

        }
    };

    private void OpenGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);

    }

    private void OpenCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                temp = 1;
                Uri selectedImageUri = data.getData();
                displayImage.setImageURI(selectedImageUri);
                selectedImagePath = getPath(selectedImageUri);


                Uri selectedImage = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(SignUpActivity.this.getContentResolver(), selectedImage);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byteArray = stream.toByteArray();

                    Log.d("byteArray", String.valueOf(byteArray));

                    str_image = byteArray.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                temp = 1;
                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                Toast.makeText(SignUpActivity.this, "" + photo, Toast.LENGTH_SHORT).show();
                displayImage.setImageBitmap(photo);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArray = stream.toByteArray();

                str_image = byteArray.toString();
            }
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // gallery-related task you need to do.

                    OpenGallery();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(SignUpActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case 2: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // camera-related task you need to do.

                    OpenCamera();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(SignUpActivity.this, "Permission denied to open Camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
