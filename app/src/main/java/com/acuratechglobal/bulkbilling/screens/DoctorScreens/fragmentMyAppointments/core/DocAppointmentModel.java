package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.ForgotPassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.SignUpActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class HomeModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;

    public HomeModel(MainActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    void openDrawer() {
        activity.OpenDrawer();
    }

}
