package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetAppointmentListApiResponse;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.AddRatingActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.google.gson.Gson;

import io.reactivex.Observable;

public class PatAppointmentModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";
    private final MainActivity activity;
    private final Api apis;
    private UserData userData;

    public PatAppointmentModel(MainActivity activity, Api api, SharedPrefsUtil prefs) {
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

    Observable<GetAppointmentListApiResponse> performGetAppointmentList() {
        return  apis.apiGetPatientAppointmentList(userData.getPatUID());
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<CommonApiResponse> performAcceptRejectTime(AcceptRejectApiRequest request) {
        return  apis.apiAcceptRejectTime(request);
    }

    void gotoAddRatingScreen(BookAppointmentModel details){

        Intent i=new Intent(activity, AddRatingActivity.class);
        String strData = new Gson().toJson(details);
        i.putExtra("data",strData);
        activity.startActivity(i);

    }
}
