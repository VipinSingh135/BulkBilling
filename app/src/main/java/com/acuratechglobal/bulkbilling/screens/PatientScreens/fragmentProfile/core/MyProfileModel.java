package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.response.GetPatientProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.models.PatientProfileModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.EditProfileActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.google.gson.Gson;

import io.reactivex.Observable;

public class MyProfileModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;
    private UserData data;
    SharedPrefsUtil prefs;

    public MyProfileModel(MainActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        this.prefs= prefs;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }


    void openDrawer() {
        activity.OpenDrawer();
    }

    void setPatData(PatientProfileModel model) {
        data.setAddress(model.getAddress());
        data.setFirstName(model.getFirstName());
        data.setLastName(model.getLastName());
        data.setsPhotoPath(model.getsPhotoPath());
        prefs.save(SharedPrefsUtil.PREFERENCE_USER_DATA,data);
        activity.setNavigationHeader();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<GetPatientProfileApiResponse> performGetProfile(){
        return apis.apiGetPatientProfile(data.getPatUID());
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    void gotoEditProfile(PatientProfileModel profileData) {
        Intent intent= new Intent(activity, EditProfileActivity.class);
        intent.putExtra("data", new Gson().toJson(profileData));
        activity.startActivity(intent);
    }


}
