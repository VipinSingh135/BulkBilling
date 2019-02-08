package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;

public class DocAppointmentModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;

    public DocAppointmentModel(MainActivity activity, Api api) {
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
