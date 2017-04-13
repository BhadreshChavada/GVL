package com.gvl.Model;

/**
 * Created by AMD21 on 4/4/17.
 */

public class LicenceModel {
    String ID;
    String VEHICLE_TYPE;
    String APPLYDATE;
    String EXAMSCORE;
    String STATUS;
    String USERID;
    String LEARNING_LIC_NO;
    String APPOINTMENTDATE;


    public LicenceModel() {
    }

    public LicenceModel(String ID, String VEHICLE_TYPE, String APPLYDATE, String EXAMSCORE, String STATUS, String USERID, String LEARNING_LIC_NO) {
        this.ID = ID;
        this.VEHICLE_TYPE = VEHICLE_TYPE;
        this.APPLYDATE = APPLYDATE;
        this.EXAMSCORE = EXAMSCORE;
        this.STATUS = STATUS;
        this.USERID = USERID;
        this.LEARNING_LIC_NO = LEARNING_LIC_NO;
    }

    public LicenceModel(String ID, String VEHICLE_TYPE, String APPLYDATE, String EXAMSCORE, String STATUS, String APPOINTMENTDATE) {
        this.ID = ID;
        this.VEHICLE_TYPE = VEHICLE_TYPE;
        this.APPLYDATE = APPLYDATE;
        this.EXAMSCORE = EXAMSCORE;
        this.STATUS = STATUS;
        this.APPOINTMENTDATE = APPOINTMENTDATE;

    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getAPPOINTMENTDATE() {
        return APPOINTMENTDATE;
    }

    public void setAPPOINTMENTDATE(String APPOINTMENTDATE) {
        this.APPOINTMENTDATE = APPOINTMENTDATE;
    }

    public String getLEARNING_LIC_NO() {
        return LEARNING_LIC_NO;
    }

    public void setLEARNING_LIC_NO(String LEARNING_LIC_NO) {
        this.LEARNING_LIC_NO = LEARNING_LIC_NO;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVEHICLE_TYPE() {
        return VEHICLE_TYPE;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.VEHICLE_TYPE = VEHICLE_TYPE;
    }

    public String getAPPLYDATE() {
        return APPLYDATE;
    }

    public void setAPPLYDATE(String APPLYDATE) {
        this.APPLYDATE = APPLYDATE;
    }

    public String getEXAMSCORE() {
        return EXAMSCORE;
    }

    public void setEXAMSCORE(String EXAMSCORE) {
        this.EXAMSCORE = EXAMSCORE;
    }


    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }
}
