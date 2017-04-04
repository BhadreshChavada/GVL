package com.gvl.Model;

/**
 * Created by AMD21 on 4/4/17.
 */

public class LicenceModel {
    String ID;
    String VEHICLE_TYPE;
    String APPLYDATE;
    String EXAMSCORE;
    Boolean STATUS;
    String USERID;


    public LicenceModel() {
    }

    public LicenceModel(String ID, String VEHICLE_TYPE, String APPLYDATE, String EXAMSCORE, Boolean STATUS, String USERID) {
        this.ID = ID;
        this.VEHICLE_TYPE = VEHICLE_TYPE;
        this.APPLYDATE = APPLYDATE;
        this.EXAMSCORE = EXAMSCORE;
        this.STATUS = STATUS;
        this.USERID = USERID;
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

    public Boolean getSTATUS() {
        return STATUS;
    }

//    public void setSTATUS(String STATUS) {
//        this.STATUS = STATUS;
//    }


    public void setSTATUS(Boolean STATUS) {
        this.STATUS = STATUS;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }
}
