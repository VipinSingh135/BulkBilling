package com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.SetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.WelcomeActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.SelectPlanActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.SetPassActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class SetPassModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final SetPassActivity activity;
    private final Api apis;

    public SetPassModel(SetPassActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<CommonApiResponse> performSetPass(SetPassApiRequest request) {
        return apis.apiSetPass(request);
    }

    void gotoSelectPlan(){
        Intent intent= new Intent(activity, SelectPlanActivity.class);
        activity.startActivity(intent);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<CommonApiResponse> performSelectPlan(SetPlanApiRequest request) {
        return apis.apiSetPlan(request);
    }

    public void gotoWelcomeScreen() {
        Intent intent= new Intent(activity, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.no_animation);
//        activity.finish();
    }
}
