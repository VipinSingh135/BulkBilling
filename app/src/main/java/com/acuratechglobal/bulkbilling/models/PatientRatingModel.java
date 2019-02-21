package com.acuratechglobal.bulkbilling.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientRatingModel {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("fkDoctorId")
    @Expose
    private Integer fkDoctorId;
    @SerializedName("docName")
    @Expose
    private String docName;
    @SerializedName("docUID")
    @Expose
    private String docUID;
    @SerializedName("docPhoto")
    @Expose
    private String docPhoto;
    @SerializedName("fkPatientId")
    @Expose
    private Integer fkPatientId;
    @SerializedName("patName")
    @Expose
    private String patName;
    @SerializedName("patUID")
    @Expose
    private String patUID;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocUID() {
        return docUID;
    }

    public void setDocUID(String docUID) {
        this.docUID = docUID;
    }

    public String getDocPhoto() {
        return docPhoto;
    }

    public void setDocPhoto(String docPhoto) {
        this.docPhoto = docPhoto;
    }

    public Integer getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Integer fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatUID() {
        return patUID;
    }

    public void setPatUID(String patUID) {
        this.patUID = patUID;
    }
}
