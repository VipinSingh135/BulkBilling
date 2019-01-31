package com.acuratechglobal.bulkbilling.api.request;

public class ChangePassApiRequest {

    private String userUID =null;
    private String oldPassword =null;
    private String newPassword =null;
    private String confirmPassword =null;
    private Integer userType = 0;

    public String getUserUniqueID() {
        return userUID;
    }

    public void setUserUniqueID(String userUniqueID) {
        this.userUID = userUniqueID;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
