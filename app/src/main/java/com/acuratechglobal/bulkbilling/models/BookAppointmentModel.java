package com.acuratechglobal.bulkbilling.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookAppointmentApiRequest {

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
    @SerializedName("timeSlot")
    @Expose
    private Integer timeSlot;

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
}
