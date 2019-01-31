package com.acuratechglobal.bulkbilling.api.request;

public class SendOtpRequest {

    private String phone= null;
    private Integer userType= 1;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
