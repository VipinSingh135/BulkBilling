package com.acuratechglobal.bulkbilling.api.response;

import com.acuratechglobal.bulkbilling.models.PatientRatingModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPatientRatingApiResponse {

    @SerializedName("list")
    @Expose
    private List<PatientRatingModel> list = null;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<PatientRatingModel> getList() {
        return list;
    }

    public void setList(List<PatientRatingModel> list) {
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
}
