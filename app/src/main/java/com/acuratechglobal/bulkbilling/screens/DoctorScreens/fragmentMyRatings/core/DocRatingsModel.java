package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.response.GetAppointmentListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetMyRatingsApiResponse;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import io.reactivex.Observable;

public class DocRatingsModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;
    private UserData userData;

    public DocRatingsModel(MainActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        if (prefs!=null){
            userData= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
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

    Observable<GetMyRatingsApiResponse> performGetRatingList() {
        return  apis.apiGetMyRatings(userData.getDocUID());
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

}
