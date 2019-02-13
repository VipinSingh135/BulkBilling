package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.GetDoctorListRequest;
import com.acuratechglobal.bulkbilling.api.response.GetDoctorListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import io.reactivex.Observable;

public class HomeModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainActivity activity;
    private final Api apis;
    private String level1Id= null;
    private String level2Id= null;
//    UserData data;
//    SharedPrefsUtil prefs;

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


    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<GetDoctorListApiResponse> performGetDocList(GetDoctorListRequest request) {
        return  apis.apiGetDoctorList(request);
    }


    Observable<SpecializationResponse> callLevel1Api() {
        return apis.apiSpecialization1();
    }
    Observable<SpecializationResponse> callLevel2Api(String levelId1) {
        return apis.apiSpecialization2(levelId1);
    }
    Observable<SpecializationResponse> callLevel3Api(String levelId2) {
        return apis.apiSpecialization3(levelId2);
    }

    String getLevel1Id() {
        return level1Id;
    }

    void setLevel1Id(String level1Id) {
        if (this.level1Id!=null && !this.level1Id.equals(level1Id)) {
            level2Id=null;
        }
        this.level1Id = level1Id;
    }

    String getLevel2Id() {
        return level2Id;
    }

    void setLevel2Id(String level2Id) {
        this.level2Id = level2Id;
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<Boolean> isLevel1Selected() {
        return Observable.just(isLevel1Added());
    }

    Observable<Boolean> isLevel2Selected() {
        return Observable.just(isLevel2Added());
    }

    private boolean isLevel1Added() {
        return level1Id != null && level1Id.length() > 0;
    }
    private boolean isLevel2Added() {
        return level2Id != null && level2Id.length() > 0;
    }
}
