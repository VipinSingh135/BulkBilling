package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.WebConstants;
import com.acuratechglobal.bulkbilling.api.response.FavouritesListApiResponse;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observable;

public class FavouritesModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final MainTabActivity activity;
    private final Api apis;
    private UserData userData;

    public FavouritesModel(MainTabActivity activity, Api api, SharedPrefsUtil prefsUtil) {
        this.activity = activity;
        this.apis = api;
        userData= prefsUtil.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA, UserData.class);
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

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<FavouritesListApiResponse> performGetFavList() {
        HashMap<String,String> headerMap= new HashMap<>();

        String timestamp= null;
        try {
            timestamp = TimeUtils.getTimeStamp();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String clientHash= null;
        try {
            clientHash = TimeUtils.SHA1(userData.getToken()+ timestamp + WebConstants.SECRET_KEY);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        headerMap.put(WebConstants.PARAM_CLIENT_HASH,clientHash);
        headerMap.put(WebConstants.PARAM_SESSION_TOKEN,userData.getToken());
        headerMap.put(WebConstants.PARAM_TIMESTAMP, String.valueOf(timestamp));

        return  apis.apiGetFavourites(headerMap,userData.getPatUID());
    }

}
