package com.gvl.Model;

/**
 * Created by AMD21 on 4/4/17.
 */

public class UserLicenceModel {


    String LIC_ID;
    String VEHICLE_TYPE;
    String APPLYDATE;
    String EXAMSCORE;
    Boolean STATUS;
    String USERID;
    String ID;
    String FNAME;
    String LNAME;
    String BIRTHDATE;
    String EMAIL;
    String PASSWORD;
    String ADDRESS;
    String IMAGE;
    String GENDER;
    String BLOODGROUP;

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getBLOODGROUP() {
        return BLOODGROUP;
    }

    public void setBLOODGROUP(String BLOODGROUP) {
        this.BLOODGROUP = BLOODGROUP;
    }

    public UserLicenceModel() {
    }


    public String getLIC_ID() {
        return LIC_ID;
    }

    public void setLIC_ID(String LIC_ID) {
        this.LIC_ID = LIC_ID;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getLNAME() {
        return LNAME;
    }

    public void setLNAME(String LNAME) {
        this.LNAME = LNAME;
    }

    public String getBIRTHDATE() {
        return BIRTHDATE;
    }

    public void setBIRTHDATE(String BIRTHDATE) {
        this.BIRTHDATE = BIRTHDATE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
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
