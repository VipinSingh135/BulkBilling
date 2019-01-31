package com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.WelcomeActivity;

public class WelcomeModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final WelcomeActivity activity;
    private final Api apis;

    public WelcomeModel(WelcomeActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }



    void gotoLoginScreen(){
        Intent intent= new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
