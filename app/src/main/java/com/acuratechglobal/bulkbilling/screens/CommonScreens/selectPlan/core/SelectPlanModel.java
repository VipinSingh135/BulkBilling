package com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core;

import android.content.Intent;
import android.widget.Toast;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.SelectPlanActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;

public class SelectPlanModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final SelectPlanActivity activity;
    private final Api apis;

    public SelectPlanModel(SelectPlanActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<CommonApiResponse> performSelectPlan(SetPlanApiRequest request) {
        return apis.apiSetPlan(request);
    }

    void gotoHomeScreen(){
        Toasty.success(activity, "Registered successfully, please login to continue", Toast.LENGTH_SHORT, true).show();

//        Toast.makeText(activity,"Registered successfully, please login to continue",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }
}
