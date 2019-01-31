package com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.SendOtpRequest;
import com.acuratechglobal.bulkbilling.api.response.ResetPassApiResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.ForgotPassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.ResetPassActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class ForgotPassModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final ForgotPassActivity activity;
    private final Api apis;

    public ForgotPassModel(ForgotPassActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<ResetPassApiResponse> performSendOtp(SendOtpRequest request) {
        return apis.apiForgotPass(request);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    public void gotoNewPassword(int userType) {
        Intent intent= new Intent(activity, ResetPassActivity.class);
        intent.putExtra("userType",userType);
        activity.startActivity(intent);

    }
}
