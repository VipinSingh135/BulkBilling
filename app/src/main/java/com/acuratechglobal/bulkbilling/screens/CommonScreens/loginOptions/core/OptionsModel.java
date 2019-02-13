package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

public class OptionsModel {

    private OptionsActivity splashContext;
    private UserData data;

    public OptionsModel(OptionsActivity splashCtx, SharedPrefsUtil prefs) {
        this.splashContext = splashCtx;
        if (prefs != null) {
            data = prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA, UserData.class);
        }
    }

    void gotoLoginActivity(boolean isDoctor) {
        Log.d("loaded", "ok showed");
        Intent i = new Intent(splashContext, LoginActivity.class);
        if (isDoctor)
            AppController.setUserType(2);
        else
            AppController.setUserType(3);

        i.putExtra("isDoctor", isDoctor);
        splashContext.startActivity(i);

//        splashContext.finish();
    }

}
