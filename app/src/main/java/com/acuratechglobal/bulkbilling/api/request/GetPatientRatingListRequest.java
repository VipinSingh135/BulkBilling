package com.acuratechglobal.bulkbilling.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPatientRatingListRequest {

    @SerializedName("patientUID")
    @Expose
    private String patientUID;
    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("recordsPerPage")
    @Expose
    private Integer recordsPerPage;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("sortOrder")
    @Expose
    private String sortOrder;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("search")
    @Expose
    private String search;

    public String getPatientUID() {
        return patientUID;
    }

    public void setPatientUID(String patientUID) {
        this.patientUID = patientUID;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(Integer recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
