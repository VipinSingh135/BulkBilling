package com.acuratechglobal.bulkbilling.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPDataModel {

    @SerializedName("uID")
    @Expose
    private String uID;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("userType")
    @Expose
    private Integer userType;

    public String getUID() {
        return uID;
    }

    public void setUID(String uID) {
        this.uID = uID;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
