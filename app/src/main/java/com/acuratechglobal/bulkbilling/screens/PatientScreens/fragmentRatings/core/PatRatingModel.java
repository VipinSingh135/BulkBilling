package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.GetDoctorListRequest;
import com.acuratechglobal.bulkbilling.api.request.GetPatientRatingListRequest;
import com.acuratechglobal.bulkbilling.api.response.GetDoctorListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetPatientRatingApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class PatRatingModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;

    public PatRatingModel(MainActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    void openDrawer() {
        activity.OpenDrawer();
    }


    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<GetPatientRatingApiResponse> performGetRatingList(GetPatientRatingListRequest request) {
        return  apis.apiGetPatientRating(request);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

}
