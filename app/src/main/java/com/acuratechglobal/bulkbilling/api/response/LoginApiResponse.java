package com.acuratechglobal.bulkbilling.api.response;

import com.acuratechglobal.bulkbilling.models.UserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginApiResponse {

    @SerializedName("object")
    @Expose
    private UserData userData;
    @SerializedName("list")
    @Expose
    private List<UserData> list = null;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("results")
    @Expose
    private List<String> results = null;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<UserData> getList() {
        return list;
    }

    public void setList(List<UserData> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
