package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;

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
