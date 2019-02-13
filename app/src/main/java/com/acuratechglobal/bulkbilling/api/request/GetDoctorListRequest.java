package com.acuratechglobal.bulkbilling.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDoctorListRequest {

    @SerializedName("level1ID")
    @Expose
    private Integer level1ID;
    @SerializedName("level2ID")
    @Expose
    private Integer level2ID;
    @SerializedName("level3ID")
    @Expose
    private List<Integer> level3ID = null;
    @SerializedName("experience")
    @Expose
    private Integer experience;
    @SerializedName("rate")
    @Expose
    private List<Integer> rate = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
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
    @SerializedName("search")
    @Expose
    private String search;

    public Integer getLevel1ID() {
        return level1ID;
    }

    public void setLevel1ID(Integer level1ID) {
        this.level1ID = level1ID;
    }

    public Integer getLevel2ID() {
        return level2ID;
    }

    public void setLevel2ID(Integer level2ID) {
        this.level2ID = level2ID;
    }

    public List<Integer> getLevel3ID() {
        return level3ID;
    }

    public void setLevel3ID(List<Integer> level3ID) {
        this.level3ID = level3ID;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public List<Integer> getRate() {
        return rate;
    }

    public void setRate(List<Integer> rate) {
        this.rate = rate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
