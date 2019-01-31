package com.acuratechglobal.bulkbilling.models;

public class PhoneVerificationDataModel {

    int status =0;
    String message= null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
