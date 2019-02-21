package com.acuratechglobal.bulkbilling.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientProfileModel {

    @SerializedName("pUID")
    @Expose
    private String pUID;
    @SerializedName("isEnabled")
    @Expose
    private Boolean isEnabled;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("deletedOn")
    @Expose
    private String deletedOn;
    @SerializedName("addedOn")
    @Expose
    private String addedOn;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("userUniqueId")
    @Expose
    private String userUniqueId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("userType")
    @Expose
    private Integer userType;
    @SerializedName("subscriptionId")
    @Expose
    private Integer subscriptionId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("isNewUser")
    @Expose
    private Boolean isNewUser;
    @SerializedName("base64Photo")
    @Expose
    private String base64Photo;
    @SerializedName("sPhotoPath")
    @Expose
    private String sPhotoPath;
    @SerializedName("docUID")
    @Expose
    private String docUID;
    @SerializedName("docID")
    @Expose
    private Integer docID;
    @SerializedName("patID")
    @Expose
    private Integer patID;
    @SerializedName("patUID")
    @Expose
    private String patUID;

    public String getpUID() {
        return pUID;
    }

    public void setpUID(String pUID) {
        this.pUID = pUID;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(String deletedOn) {
        this.deletedOn = deletedOn;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

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

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getNewUser() {
        return isNewUser;
    }

    public void setNewUser(Boolean newUser) {
        isNewUser = newUser;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public String getsPhotoPath() {
        return sPhotoPath;
    }

    public void setsPhotoPath(String sPhotoPath) {
        this.sPhotoPath = sPhotoPath;
    }

    public String getDocUID() {
        return docUID;
    }

    public void setDocUID(String docUID) {
        this.docUID = docUID;
    }

    public Integer getDocID() {
        return docID;
    }

    public void setDocID(Integer docID) {
        this.docID = docID;
    }

    public Integer getPatID() {
        return patID;
    }

    public void setPatID(Integer patID) {
        this.patID = patID;
    }

    public String getPatUID() {
        return patUID;
    }

    public void setPatUID(String patUID) {
        this.patUID = patUID;
    }
}
