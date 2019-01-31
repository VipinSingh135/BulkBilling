package com.acuratechglobal.bulkbilling.api.request;

import com.acuratechglobal.bulkbilling.models.DoctorAvailabilityModel;
import com.acuratechglobal.bulkbilling.models.DoctorSpecializationModel;
import com.acuratechglobal.bulkbilling.models.QualificationModel;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateProfileApiRequest {

    @SerializedName("doctorSpecialization")
    @Expose
    private List<DoctorSpecializationModel> doctorSpecialization = null;
    @SerializedName("clinicName")
    @Expose
    private String clinicName;
    @SerializedName("clinicAddress")
    @Expose
    private String clinicAddress;
    @SerializedName("clinicAddressLat")
    @Expose
    private String clinicAddressLat;
    @SerializedName("clinicAddressLong")
    @Expose
    private String clinicAddressLong;
    @SerializedName("qualifications")
    @Expose
    private List<QualificationModel> qualifications = null;
    @SerializedName("doctorAvailability")
    @Expose
    private DoctorAvailabilityModel doctorAvailability;
    @SerializedName("experience")
    @Expose
    private Integer experience;
    @SerializedName("openTime")
    @Expose
    private String openTime;
    @SerializedName("closeTime")
    @Expose
    private String closeTime;
    @SerializedName("isEnabled")
    @Expose
    private Boolean isEnabled;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("deletedOn")
    @Expose
    private String deletedOn;
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
    @SerializedName("sPhotoPath")
    @Expose
    private String sPhotoPath;
    @SerializedName("docUID")
    @Expose
    private String docUID;
    @SerializedName("patUID")
    @Expose
    private String patUID;
    @SerializedName("base64Photo")
    @Expose
    private String  base64Photo;
    @SerializedName("selectedSpecializationLevel1")
    @Expose
    private SpecializationModel selectedLevel1;
    @SerializedName("selectedSpecializationLevel2")
    @Expose
    private SpecializationModel selectedLevel2;

    public List<DoctorSpecializationModel> getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(List<DoctorSpecializationModel> doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getClinicAddressLat() {
        return clinicAddressLat;
    }

    public void setClinicAddressLat(String clinicAddressLat) {
        this.clinicAddressLat = clinicAddressLat;
    }

    public String getClinicAddressLong() {
        return clinicAddressLong;
    }

    public void setClinicAddressLong(String clinicAddressLong) {
        this.clinicAddressLong = clinicAddressLong;
    }

    public List<QualificationModel> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationModel> qualifications) {
        this.qualifications = qualifications;
    }

    public DoctorAvailabilityModel getDoctorAvailability() {
        return doctorAvailability;
    }

    public void setDoctorAvailability(DoctorAvailabilityModel doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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

//    public byte[] getPhotoByte() {
//        return photoByte;
//    }
//
//    public void setPhotoByte(byte[] photoByte) {
//        this.photoByte = photoByte;
//    }

    public SpecializationModel getSelectedLevel1() {
        return selectedLevel1;
    }

    public void setSelectedLevel1(SpecializationModel selectedLevel1) {
        this.selectedLevel1 = selectedLevel1;
    }

    public SpecializationModel getSelectedLevel2() {
        return selectedLevel2;
    }

    public void setSelectedLevel2(SpecializationModel selectedLevel2) {
        this.selectedLevel2 = selectedLevel2;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }
}
