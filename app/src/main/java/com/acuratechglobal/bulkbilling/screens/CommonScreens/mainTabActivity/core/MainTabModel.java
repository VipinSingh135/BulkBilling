package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

public class MainModel {

    private MainTabActivity activity;
    private UserData data;
    private SharedPrefsUtil prefs;
    public MainModel(MainTabActivity activity, SharedPrefsUtil prefs) {
        this.activity = activity;
        if (prefs!=null){
            this.prefs=prefs;
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

//    Observable<Boolean> isNetworkAvailable() {
//        return NetworkUtils.networkAvailable(splashContext);
//    }




    public void gotoLoginActivity()  {
        if (prefs!=null)
            prefs.removeAll();
        Intent intent = new Intent(activity, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    int getUserType(){
        return data.getUserType();
    }

}
