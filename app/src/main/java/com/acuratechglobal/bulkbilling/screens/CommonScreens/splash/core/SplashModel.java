package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core;

import android.os.Handler;

import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

public class SplashModel {

    private SplashActivity splashContext;
    UserData data;
    public SplashModel(SplashActivity splashCtx, SharedPrefsUtil prefs) {
        this.splashContext = splashCtx;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

//    Observable<Boolean> isNetworkAvailable() {
//        return NetworkUtils.networkAvailable(splashContext);
//    }

    public void gotoLoginActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (data!=null && data.getUserUniqueId()!=null){
                    splashContext.gotoHomeActivity();
                }else{
                    splashContext.gotoLoginActivity();
                }
            }
        },2000);
    }

}
