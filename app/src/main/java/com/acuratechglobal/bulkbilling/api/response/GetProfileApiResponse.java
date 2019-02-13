package com.acuratechglobal.bulkbilling.api.response;

import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfileApiResponse {

    @SerializedName("object")
    @Expose
    private DoctorProfileModel object;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public DoctorProfileModel getObject() {
        return object;
    }

    public void setObject(DoctorProfileModel object) {
        this.object = object;
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
