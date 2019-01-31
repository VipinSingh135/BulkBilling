package com.acuratechglobal.bulkbilling.api.request;

public class SetPassApiRequest {

    private String newPassword=null;
    private String confirmPassword=null;
    private String userUniqueID=null;

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

    public String getUserUniqueID() {
        return userUniqueID;
    }

    public void setUserUniqueID(String userUniqueID) {
        this.userUniqueID = userUniqueID;
    }
}
