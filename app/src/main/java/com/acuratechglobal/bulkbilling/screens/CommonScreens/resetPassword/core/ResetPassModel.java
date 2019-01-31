package com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core;

import android.content.Intent;
import android.widget.Toast;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.ResetPassActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class ResetPassModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final ResetPassActivity activity;
    private final Api apis;

    public ResetPassModel(ResetPassActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<CommonApiResponse> performResetPass(ResetPassApiRequest request) {
        return apis.apiResetPass(request);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    public void gotoLogin() {

        Toast.makeText(activity,"Password reset successfully, please login to continue",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }
}
