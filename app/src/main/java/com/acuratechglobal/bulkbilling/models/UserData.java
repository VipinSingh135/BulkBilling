package com.acuratechglobal.bulkbilling.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("profilePicture")
    @Expose
    private String profilePicture;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageLink")
    @Expose
    private String imageLink;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("isAuthenticated")
    @Expose
    private Boolean isAuthenticated;
    @SerializedName("facebookID")
    @Expose
    private String facebookID;
    @SerializedName("eAuthenticationType")
    @Expose
    private Integer eAuthenticationType;
    @SerializedName("token")
    @Expose
    private String token;
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
    private String subscriptionId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("photoByte")
    @Expose
    private String photoByte;
    @SerializedName("sPhotoPath")
    @Expose
    private String sPhotoPath;
    @SerializedName("docUID")
    @Expose
    private String docUID;
    @SerializedName("patUID")
    @Expose
    private String patUID;
    @SerializedName("isNewUser")
    @Expose
    private Boolean IsNewUser;


    public Boolean getNewUser() {
        return IsNewUser;
    }

    public void setNewUser(Boolean newUser) {
        IsNewUser = newUser;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public Integer geteAuthenticationType() {
        return eAuthenticationType;
    }

    public void seteAuthenticationType(Integer eAuthenticationType) {
        this.eAuthenticationType = eAuthenticationType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getPhotoByte() {
        return photoByte;
    }

    public void setPhotoByte(String photoByte) {
        this.photoByte = photoByte;
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

    public String getPatUID() {
        return patUID;
    }

    public void setPatUID(String patUID) {
        this.patUID = patUID;
    }
}
