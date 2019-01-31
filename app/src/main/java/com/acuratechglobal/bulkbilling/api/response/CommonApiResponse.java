package com.acuratechglobal.bulkbilling.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonApiResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sUID")
    @Expose
    private String sUID;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSUID() {
        return sUID;
    }

    public void setSUID(String sUID) {
        this.sUID = sUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
