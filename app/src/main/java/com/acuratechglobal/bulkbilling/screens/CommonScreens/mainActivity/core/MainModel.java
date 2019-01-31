package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

public class MainModel {

    private MainActivity activity;
    UserData data;
    SharedPrefsUtil prefs;
    public MainModel(MainActivity activity, SharedPrefsUtil prefs) {
        this.activity = activity;
        if (prefs!=null){
            this.prefs=prefs;
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

//    Observable<Boolean> isNetworkAvailable() {
//        return NetworkUtils.networkAvailable(splashContext);
//    }


    public void gotoCreateProfileActivity() {

        Intent intent = new Intent(activity, CreateProfileActivity.class);
        intent.putExtra("isUpdate",true);
        activity.startActivity(intent);
    }

    public void gotoLoginActivity() {
        if (prefs!=null)
            prefs.removeAll();
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    int getUserType(){
        return data.getUserType();
    }

}
