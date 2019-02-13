package com.acuratechglobal.bulkbilling.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorRatingModel {

    @SerializedName("docUID")
    @Expose
    private String docUID;
    @SerializedName("totalPeople")
    @Expose
    private Integer totalPeople;
    @SerializedName("five_Star")
    @Expose
    private Integer fiveStar;
    @SerializedName("four_Star")
    @Expose
    private Integer fourStar;
    @SerializedName("three_Star")
    @Expose
    private Integer threeStar;
    @SerializedName("two_Star")
    @Expose
    private Integer twoStar;
    @SerializedName("one_Star")
    @Expose
    private Integer oneStar;
    @SerializedName("avgRate")
    @Expose
    private Integer avgRate;
    @SerializedName("reviewHighLights")
    @Expose
    private List<RatingDataModel> reviewHighLights = null;


    public String getDocUID() {
        return docUID;
    }

    public void setDocUID(String docUID) {
        this.docUID = docUID;
    }

    public Integer getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(Integer totalPeople) {
        this.totalPeople = totalPeople;
    }

    public Integer getFiveStar() {
        return fiveStar;
    }

    public void setFiveStar(Integer fiveStar) {
        this.fiveStar = fiveStar;
    }

    public Integer getFourStar() {
        return fourStar;
    }

    public void setFourStar(Integer fourStar) {
        this.fourStar = fourStar;
    }

    public Integer getThreeStar() {
        return threeStar;
    }

    public void setThreeStar(Integer threeStar) {
        this.threeStar = threeStar;
    }

    public Integer getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(Integer twoStar) {
        this.twoStar = twoStar;
    }

    public Integer getOneStar() {
        return oneStar;
    }

    public void setOneStar(Integer oneStar) {
        this.oneStar = oneStar;
    }

    public Integer getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Integer avgRate) {
        this.avgRate = avgRate;
    }

    public List<RatingDataModel> getReviewHighLights() {
        return reviewHighLights;
    }

    public void setReviewHighLights(List<RatingDataModel> reviewHighLights) {
        this.reviewHighLights = reviewHighLights;
    }
}
