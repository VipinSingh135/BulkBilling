package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.models.DoctorAvailabilityModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.MyProfileDocFragment;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MyProfileModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;
    private List<String> selectedDaysList=new ArrayList<>();

    public MyProfileModel(MainActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void openDrawer() {
       activity.OpenDrawer();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<GetProfileApiResponse> performGetProfile(String id){
        return apis.apiGetProfile(id);
    }

    List<String> getSelectedDaysList() {
        return selectedDaysList;
    }

    void setDays(DoctorAvailabilityModel doctorAvailability) {
        selectedDaysList.clear();
        if (doctorAvailability.getSunday()){
            selectedDaysList.add("Sunday");
        }
        if (doctorAvailability.getMonday()){
            selectedDaysList.add("Monday");
        }
        if (doctorAvailability.getTuesday()){
            selectedDaysList.add("Tuesday");
        }
        if (doctorAvailability.getWednesday()){
            selectedDaysList.add("Wednesday");
        }
        if (doctorAvailability.getThursday()){
            selectedDaysList.add("Thursday");
        }
        if (doctorAvailability.getFriday()){
            selectedDaysList.add("Friday");
        }
        if (doctorAvailability.getSaturday()){
            selectedDaysList.add("Saturday");
        }

    }

    void gotoCreateProfileActivity() {
        Intent intent = new Intent(activity, CreateProfileActivity.class);
        intent.putExtra("isUpdate",true);
        activity.startActivity(intent);
    }
}
