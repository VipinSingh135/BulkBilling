package com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core;

import android.provider.Telephony;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.api.request.AddRatingRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.AddRatingActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class AddRatingModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final AddRatingActivity activity;
    private final Api apis;

    public AddRatingModel(AddRatingActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }


    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<CommonApiResponse> performAddRating(AddRatingRequest request) {
        return  apis.apiAddRating(request);
    }
}
