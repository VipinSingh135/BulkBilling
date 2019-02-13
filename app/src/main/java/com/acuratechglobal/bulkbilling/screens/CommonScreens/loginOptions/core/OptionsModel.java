package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

public class SplashModel {

    private SplashActivity splashContext;
    private UserData data;
    public SplashModel(SplashActivity splashCtx, SharedPrefsUtil prefs) {
        this.splashContext = splashCtx;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

//    Observable<Boolean> isNetworkAvailable() {
//        return NetworkUtils.networkAvailable(splashContext);
//    }

    public void gotoHomeActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (data!=null && data.getUserUniqueId()!=null){
                    splashContext.gotoHomeActivity();
                }else{
                    splashContext.showOptions();
                }
            }
        },2000);
    }

    public void gotoLoginActivity(boolean isDoctor) {
        Log.d("loaded", "ok showed");
        Intent i = new Intent(splashContext, LoginActivity.class);
        i.putExtra("isDoctor",isDoctor);
        splashContext.startActivity(i);
        splashContext.finish();
    }

}
