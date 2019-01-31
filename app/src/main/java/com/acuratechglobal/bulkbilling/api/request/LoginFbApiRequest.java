package com.acuratechglobal.bulkbilling.api.request;

public class LoginFbApiRequest {

    private String fbUniqueId =null;
    private String device_Token= "";
    private Integer userType=0;
    private Integer device_ID= 1;

    public String getFbUniqueId() {
        return fbUniqueId;
    }

    public void setFbUniqueId(String fbUniqueId) {
        this.fbUniqueId = fbUniqueId;
    }

    public String getDevice_Token() {
        return device_Token;
    }

    public void setDevice_Token(String device_Token) {
        this.device_Token = device_Token;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getDevice_ID() {
        return device_ID;
    }

    public void setDevice_ID(Integer device_ID) {
        this.device_ID = device_ID;
    }
}
