package com.gvl.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.gvl.Model.LicenceModel;
import com.gvl.Model.UserLicenceModel;
import com.gvl.Model.Contact;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMD21 on 4/4/17.
 */

public class GVLDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GVLdatabase";

    // Contacts table name
    public static final String TABLE_REGISTRATION = "Registration";
    public static final String TABLE_LEARNING_LIC_REGISTRATION = "Learning_Lic_Registration";

    // Contacts Table Columns names
    private static final String REGISTRATION_ID = "id";
    private static final String REGISTRATION_FNAME = "fname";
    private static final String REGISTRATION_LNAME = "lname";
    private static final String REGISTRATION_BIRTHDATE = "birthdate";
    private static final String REGISTRATION_EMAIL = "email";
    private static final String REGISTRATION_GENDER = "gender";
    private static final String REGISTRATION_BLOOD_GROUP = "bloodgroup";
    private static final String REGISTRATION_PASSWORD = "password";
    private static final String REGISTRATION_ADDRESS = "address";
    private static final String REGISTRATION_IMAGE = "image";

    private static final String LEARNING_LIC_ID = "id";
    private static final String LEARNING_LIC_VEHICLE_TYPE = "vehicletype";
    private static final String LEARNING_LIC_APPLYDATE = "ApplyDate";
    private static final String LEARNING_LIC_EXAMSCORE = "examScore";
    private static final String LEARNING_LIC_STATUS = "Status";
    private static final String LEARNING_LIC_USERID = "userid";
    private static final String LEARNING_LIC_NO = "Licence_no";
    private static final String LEARNING_APPOINTMENT_DATE = "AppointmentDate";


    public GVLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_REGISTRATION + "("
                + REGISTRATION_ID + " INTEGER PRIMARY KEY," + REGISTRATION_FNAME + " TEXT," + REGISTRATION_LNAME + " TEXT," + REGISTRATION_BIRTHDATE + " TEXT," + REGISTRATION_EMAIL + " TEXT," + REGISTRATION_PASSWORD + " TEXT," + REGISTRATION_ADDRESS + " TEXT," + REGISTRATION_IMAGE + " TEXT," + REGISTRATION_BLOOD_GROUP + " TEXT," + REGISTRATION_GENDER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_LEARNING_LIC_TABLE = "CREATE TABLE " + TABLE_LEARNING_LIC_REGISTRATION + "(" + LEARNING_LIC_ID + " INTEGER PRIMARY KEY," + LEARNING_LIC_VEHICLE_TYPE + " TEXT," + LEARNING_LIC_APPLYDATE + " TEXT," + LEARNING_LIC_EXAMSCORE + " TEXT," + LEARNING_LIC_STATUS + " TEXT," + LEARNING_LIC_NO + " TEXT," + LEARNING_APPOINTMENT_DATE + " TEXT," + LEARNING_LIC_USERID + " TEXT," + " FOREIGN KEY (" + LEARNING_LIC_USERID + ") REFERENCES " + TABLE_REGISTRATION + "(" + REGISTRATION_ID + "));";
        db.execSQL(CREATE_LEARNING_LIC_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);

        // Create tables again
        onCreate(db);
    }

    // Adding new Registration
    public void addRegistration(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REGISTRATION_FNAME, contact.getFNAME());
        values.put(REGISTRATION_LNAME, contact.getLNAME());
        values.put(REGISTRATION_BIRTHDATE, contact.getBIRTHDATE());
        values.put(REGISTRATION_EMAIL, contact.getEMAIL());
        values.put(REGISTRATION_PASSWORD, contact.getPASSWORD());
        values.put(REGISTRATION_ADDRESS, contact.getADDRESS());
        values.put(REGISTRATION_IMAGE, contact.getIMAGE());
        values.put(REGISTRATION_GENDER, contact.getGENDER());
        values.put(REGISTRATION_BLOOD_GROUP, contact.getBLOODGROP());


        // Inserting Row
        db.insert(TABLE_REGISTRATION, null, values);
        db.close(); // Closing database connection
    }

    public Contact getContact(String email, String password) {

        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REGISTRATION, new String[]{REGISTRATION_ID,
                        REGISTRATION_FNAME, REGISTRATION_LNAME, REGISTRATION_BIRTHDATE, REGISTRATION_EMAIL, REGISTRATION_PASSWORD, REGISTRATION_ADDRESS, REGISTRATION_IMAGE, REGISTRATION_GENDER, REGISTRATION_BLOOD_GROUP}, REGISTRATION_EMAIL + "=? AND " + REGISTRATION_PASSWORD + "=?",
                new String[]{email, password}, null, null, null, null);
        if (cursor != null) {
//            cursor.moveToFirst();
            while (cursor.moveToFirst()) {
                count = cursor.getInt(0);
                break;
            }
        }

        if (count > 0) {

            Contact contact = new Contact(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
            // return contact
            return contact;
        } else {
            return null;
        }
    }


    public LicenceModel GetLearningLicByID(String ID) {

        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LEARNING_LIC_REGISTRATION, new String[]{LEARNING_LIC_ID,
                        LEARNING_LIC_VEHICLE_TYPE, LEARNING_LIC_APPLYDATE, LEARNING_LIC_EXAMSCORE, LEARNING_LIC_STATUS, LEARNING_APPOINTMENT_DATE}, LEARNING_LIC_NO + "=?",
                new String[]{ID}, null, null, null, null);
        if (cursor != null) {
//            cursor.moveToFirst();
            while (cursor.moveToFirst()) {
                count = cursor.getInt(0);
                break;
            }
        }

        if (count > 0) {

            LicenceModel licenceModel = new LicenceModel(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            // return contact
            return licenceModel;
        } else {
            return null;
        }
    }

    public boolean GetLearningLicByType(String Type, String ID) {

        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LEARNING_LIC_REGISTRATION, new String[]{LEARNING_LIC_ID,
                        LEARNING_LIC_VEHICLE_TYPE, LEARNING_LIC_APPLYDATE, LEARNING_LIC_EXAMSCORE, LEARNING_LIC_STATUS, LEARNING_APPOINTMENT_DATE}, LEARNING_LIC_VEHICLE_TYPE + "=?" + " AND " + LEARNING_LIC_USERID + "=?",
                new String[]{Type, ID}, null, null, null, null);
        if (cursor != null) {
//            cursor.moveToFirst();
            while (cursor.moveToFirst()) {
                count = cursor.getInt(0);
                break;
            }
        }

        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }


    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REGISTRATION, REGISTRATION_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    public void addLicenceRequest(LicenceModel licenceModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LEARNING_LIC_VEHICLE_TYPE, licenceModel.getVEHICLE_TYPE());
        values.put(LEARNING_LIC_APPLYDATE, licenceModel.getAPPLYDATE());
        values.put(LEARNING_LIC_EXAMSCORE, licenceModel.getEXAMSCORE());
        values.put(LEARNING_LIC_USERID, licenceModel.getUSERID());
        values.put(LEARNING_LIC_NO, licenceModel.getLEARNING_LIC_NO());
        values.put(LEARNING_LIC_STATUS, "false");
        values.put(LEARNING_APPOINTMENT_DATE, "");

        // Inserting Row
        db.insert(TABLE_LEARNING_LIC_REGISTRATION, null, values);
        db.close(); // Closing database connection
    }


    public List<LicenceModel> getLicenceRequest() {
        List<LicenceModel> LicenceModel = new ArrayList<LicenceModel>();
        // Select All Query
//        r LEFT OUTER JOIN respondentType rt ON r.type_code = rt.rtype_code
//        String selectQuery = "SELECT  * FROM " + TABLE_LEARNING_LIC_REGISTRATION + "Learning LEFT OUTER JOIN " + TABLE_REGISTRATION + " Registration ON Learning." + LEARNING_LIC_USERID + "= Registration." + REGISTRATION_ID;

        String selectQuery = "SELECT  * FROM " + TABLE_LEARNING_LIC_REGISTRATION;
        Log.d("Query", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LicenceModel licenceModel = new LicenceModel();
                licenceModel.setID(cursor.getString(0));
                licenceModel.setVEHICLE_TYPE(cursor.getString(1));
                licenceModel.setAPPLYDATE(cursor.getString(2));
                licenceModel.setEXAMSCORE(cursor.getString(3));
                licenceModel.setSTATUS(cursor.getString(4));
                licenceModel.setLEARNING_LIC_NO(cursor.getString(5));
                licenceModel.setUSERID(cursor.getString(6));
                // Adding contact to list
                LicenceModel.add(licenceModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return LicenceModel;
    }

    public void updateAppointment(String Date, String Licno) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LEARNING_APPOINTMENT_DATE, Date);

        db.update(TABLE_LEARNING_LIC_REGISTRATION, cv, LEARNING_LIC_NO + " = ?", new String[]{Licno});
    }

    public void updateAppointmentStatus(String Staus, String Licno) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LEARNING_LIC_STATUS, Staus);

        db.update(TABLE_LEARNING_LIC_REGISTRATION, cv, LEARNING_LIC_NO + " = ?", new String[]{Licno});
    }


//    public void updateAppointmentStatus(Boolean status, String Licno) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv1 = new ContentValues();
//        cv1.put(LEARNING_LIC_STATUS, status);
//
//        Log.d("Status", String.valueOf(status));
//
////        if (status == false) {
////            cv1.put(LEARNING_APPOINTMENT_DATE, "");
////        }
//
//        db.update(TABLE_LEARNING_LIC_REGISTRATION, cv1, LEARNING_LIC_NO + " = ?", new String[]{Licno});
////        db.rawQuery("UPDATE " + TABLE_LEARNING_LIC_REGISTRATION + " SET " + LEARNING_APPOINTMENT_DATE + " = '" + Date + "' WHERE " + LEARNING_LIC_NO + " = " + Licno, null);
//    }

    public List<UserLicenceModel> getContact() {
        List<UserLicenceModel> LicenceModel = new ArrayList<UserLicenceModel>();

//        String Query = "SELECT " + REGISTRATION_FNAME + ", " + REGISTRATION_EMAIL + ", " + REGISTRATION_LNAME + ", " + LEARNING_LIC_EXAMSCORE + " FROM " + TABLE_REGISTRATION + " INNER JOIN " + TABLE_LEARNING_LIC_REGISTRATION + " ON " + TABLE_REGISTRATION + "." + REGISTRATION_ID + "=" + TABLE_LEARNING_LIC_REGISTRATION + "." + LEARNING_LIC_ID;

        String Query = "SELECT *" + " FROM " + TABLE_REGISTRATION + " INNER JOIN " + TABLE_LEARNING_LIC_REGISTRATION + " ON " + TABLE_REGISTRATION + "." + REGISTRATION_ID + "=" + TABLE_LEARNING_LIC_REGISTRATION + "." + LEARNING_LIC_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {

                UserLicenceModel licenceModel = new UserLicenceModel();
                licenceModel.setUSERID(cursor.getString(0));
                licenceModel.setFNAME(cursor.getString(1));
                licenceModel.setLNAME(cursor.getString(2));
                licenceModel.setBIRTHDATE(cursor.getString(3));
                licenceModel.setEMAIL(cursor.getString(4));
                licenceModel.setPASSWORD(cursor.getString(5));
                licenceModel.setADDRESS(cursor.getString(6));
                licenceModel.setIMAGE(cursor.getString(7));
                licenceModel.setGENDER(cursor.getString(8));
                licenceModel.setBLOODGROUP(cursor.getString(9));
                licenceModel.setID(cursor.getString(10));
                licenceModel.setVEHICLE_TYPE(cursor.getString(11));
                licenceModel.setAPPLYDATE(cursor.getString(12));
                licenceModel.setEXAMSCORE(cursor.getString(13));
                licenceModel.setSTATUS(Boolean.valueOf(cursor.getString(14)));
                licenceModel.setLicenceNO(cursor.getString(15));
                licenceModel.setUSERID(cursor.getString(16));

                // Adding contact to list
                LicenceModel.add(licenceModel);
            } while (cursor.moveToNext());
        }
        return LicenceModel;
    }
}
