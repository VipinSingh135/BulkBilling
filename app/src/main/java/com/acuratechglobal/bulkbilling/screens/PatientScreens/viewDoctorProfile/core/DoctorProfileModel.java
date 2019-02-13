package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.BookAppointmentActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DoctorProfileModel {
    private static final int REQUEST_PLACE_PICKER = 6;
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final DoctorProfileActivity activity;
    private final Api apis;

    private List<String> daysList=new ArrayList<>();

    UserData data;
    SharedPrefsUtil prefs;
    public DoctorProfileModel(DoctorProfileActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        daysList.clear();
        daysList.add("Sunday");
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");
        daysList.add("Saturday");

        this.prefs= prefs;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

    void finish() {
        activity.finish();
    }

    void gotoGetAppoinment(com.acuratechglobal.bulkbilling.models.DoctorProfileModel data) {
        Intent intent= new Intent(activity, BookAppointmentActivity.class);
        String strData= new Gson().toJson(data);
        intent.putExtra("data",strData);
        activity.startActivity(intent);

    }
}
