package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core.SplashPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core.SplashView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.dagger.DaggerSplashComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.dagger.SplashContextModule;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SplashView view;
    @Inject
    SplashPresenter splashPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder().
                appComponent(AppController.getAppComponent()).
                splashContextModule(new SplashContextModule(this)).
                build().inject(this);

        setContentView(view.constructView());
        splashPresenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        splashPresenter.onDestroy();
    }



    public void gotoHomeActivity() {
        Log.d("loaded", "ok showed");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.finish();
    }

    public void gotoOptionsActivity() {
        Log.d("loaded", "ok showed");
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
        finish();
    }
}
