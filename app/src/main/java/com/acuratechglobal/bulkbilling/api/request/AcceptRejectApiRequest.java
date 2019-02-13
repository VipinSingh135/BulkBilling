package com.acuratechglobal.bulkbilling.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptRejectApiRequest {

    @SerializedName("appointmentUID")
    @Expose
    private String appointmentUID;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("suggestedOn")
    @Expose
    private String suggestedOn;
    @SerializedName("suggestedOnTimeSlot")
    @Expose
    private Integer suggestedOnTimeSlot;

    public String getAppointmentUID() {
        return appointmentUID;
    }

    public void setAppointmentUID(String appointmentUID) {
        this.appointmentUID = appointmentUID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSuggestedOn() {
        return suggestedOn;
    }

    public void setSuggestedOn(String suggestedOn) {
        this.suggestedOn = suggestedOn;
    }

    public Integer getSuggestedOnTimeSlot() {
        return suggestedOnTimeSlot;
    }

    public void setSuggestedOnTimeSlot(Integer suggestedOnTimeSlot) {
        this.suggestedOnTimeSlot = suggestedOnTimeSlot;
    }

}
