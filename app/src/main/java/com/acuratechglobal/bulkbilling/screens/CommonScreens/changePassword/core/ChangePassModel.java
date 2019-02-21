package com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core;

import android.content.Intent;
import android.widget.Toast;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.ChangePassApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;

public class ChangePassModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final ChangePassActivity activity;
    private final Api apis;
    private final SharedPrefsUtil prefs;

    public ChangePassModel(ChangePassActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        this.prefs = prefs;
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<CommonApiResponse> performChangePass(ChangePassApiRequest request) {
        return apis.apiChangePassword(request);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    void gotoLogin() {
        Toasty.success(activity, "Password reset successfully, please login to continue", Toast.LENGTH_SHORT, false).show();

//        Toast.makeText(activity,"Password reset successfully, please login to continue",Toast.LENGTH_LONG).show();
        prefs.removeAll();
        Intent intent= new Intent(activity, OptionsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }
}
