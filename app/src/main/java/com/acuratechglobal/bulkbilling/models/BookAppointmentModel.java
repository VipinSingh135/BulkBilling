package com.acuratechglobal.bulkbilling.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookAppointmentModel {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("issue")
    @Expose
    private String issue;
    @SerializedName("fkDoctorId")
    @Expose
    private Integer fkDoctorId;
    @SerializedName("fkDoctorName")
    @Expose
    private String fkDoctorName;
    @SerializedName("fkPatientId")
    @Expose
    private Integer fkPatientId;
    @SerializedName("fkPatientName")
    @Expose
    private String fkPatientName;
    @SerializedName("patientPhotoPath")
    @Expose
    private String patientPhotoPath;
    @SerializedName("doctorPhotoPath")
    @Expose
    private String doctorPhotoPath;
    @SerializedName("timeSlot")
    @Expose
    private Integer timeSlot;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("isSuggested")
    @Expose
    private Boolean isSuggested;
    @SerializedName("suggestedOn")
    @Expose
    private String suggestedOn;
    @SerializedName("suggestedOnTimeSlot")
    @Expose
    private String suggestedOnTimeSlot;
    @SerializedName("isConfirmed")
    @Expose
    private Boolean isConfirmed;
    @SerializedName("confirmedOn")
    @Expose
    private String confirmedOn;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("deletedOn")
    @Expose
    private String deletedOn;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Integer getFkDoctorId() {
        return fkDoctorId;
    }

    public void setFkDoctorId(Integer fkDoctorId) {
        this.fkDoctorId = fkDoctorId;
    }

    public String getFkDoctorName() {
        return fkDoctorName;
    }

    public void setFkDoctorName(String fkDoctorName) {
        this.fkDoctorName = fkDoctorName;
    }

    public Integer getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Integer fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public String getFkPatientName() {
        return fkPatientName;
    }

    public void setFkPatientName(String fkPatientName) {
        this.fkPatientName = fkPatientName;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuggested() {
        return isSuggested;
    }

    public void setSuggested(Boolean suggested) {
        isSuggested = suggested;
    }

    public String getSuggestedOn() {
        return suggestedOn;
    }

    public void setSuggestedOn(String suggestedOn) {
        this.suggestedOn = suggestedOn;
    }

    public String getSuggestedOnTimeSlot() {
        return suggestedOnTimeSlot;
    }

    public void setSuggestedOnTimeSlot(String suggestedOnTimeSlot) {
        this.suggestedOnTimeSlot = suggestedOnTimeSlot;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getConfirmedOn() {
        return confirmedOn;
    }

    public void setConfirmedOn(String confirmedOn) {
        this.confirmedOn = confirmedOn;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
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

    public String getPatientPhotoPath() {
        return patientPhotoPath;
    }

    public void setPatientPhotoPath(String patientPhotoPath) {
        this.patientPhotoPath = patientPhotoPath;
    }

    public String getDoctorPhotoPath() {
        return doctorPhotoPath;
    }

    public void setDoctorPhotoPath(String doctorPhotoPath) {
        this.doctorPhotoPath = doctorPhotoPath;
    }
}
