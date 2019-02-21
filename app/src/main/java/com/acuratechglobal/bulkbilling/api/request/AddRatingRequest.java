package com.acuratechglobal.bulkbilling.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddRatingRequest {

    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("fkDoctorId")
    @Expose
    private Integer fkDoctorId;
    @SerializedName("fkPatientId")
    @Expose
    private Integer fkPatientId;
    @SerializedName("fkAppointmentId")
    @Expose
    private Integer fkAppointmentId;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getFkDoctorId() {
        return fkDoctorId;
    }

    public void setFkDoctorId(Integer fkDoctorId) {
        this.fkDoctorId = fkDoctorId;
    }

    public Integer getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Integer fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getFkAppointmentId() {
        return fkAppointmentId;
    }

    public void setFkAppointmentId(Integer fkAppointmentId) {
        this.fkAppointmentId = fkAppointmentId;
    }
}
