package com.acuratechglobal.bulkbilling.api.response;

import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;
import com.acuratechglobal.bulkbilling.models.OTPDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassApiResponse {

    @SerializedName("object")
    @Expose
    private OTPDataModel OtpObject;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public OTPDataModel getOtpObject() {
        return OtpObject;
    }

    public void setOtpObject(OTPDataModel otpObject) {
        OtpObject = otpObject;
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
