package com.gvl.Model;

/**
 * Created by AMD21 on 4/4/17.
 */

public class Contact {
    String ID;
    String FNAME;
    String LNAME;
    String BIRTHDATE;
    String EMAIL;
    String PASSWORD;
    String ADDRESS;
    String IMAGE;
    String BLOODGROP;
    String GENDER;

    // Empty constructor
    public Contact() {

    }

    public Contact(String ID, String FNAME, String LNAME, String BIRTHDATE, String EMAIL, String PASSWORD, String ADDRESS, String IMAGE, String BLOODGROP, String GENDER) {
        this.ID = ID;
        this.FNAME = FNAME;
        this.LNAME = LNAME;
        this.BIRTHDATE = BIRTHDATE;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.ADDRESS = ADDRESS;
        this.IMAGE = IMAGE;
        this.BLOODGROP = BLOODGROP;
        this.GENDER = GENDER;
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

    public String getBLOODGROP() {
        return BLOODGROP;
    }

    public void setBLOODGROP(String BLOODGROP) {
        this.BLOODGROP = BLOODGROP;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }
}


