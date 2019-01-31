package com.acuratechglobal.bulkbilling.api.request;

public class SignUpApiRequest {

    private String firstName="";
    private String lastName="";
    private String email="";
    private Integer userType;
    private String phone="";
    private Integer notificationType;
    private String facebookUID="";
    private Integer signUpType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Integer notificationType) {
        this.notificationType = notificationType;
    }

    public String getFacebookUID() {
        return facebookUID;
    }

    public void setFacebookUID(String facebookUID) {
        this.facebookUID = facebookUID;
    }

    public Integer getSignUpType() {
        return signUpType;
    }

    public void setSignUpType(Integer signUpType) {
        this.signUpType = signUpType;
    }
}
