package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core.OptionsPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core.OptionsView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.dagger.DaggerOptionsComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.dagger.OptionsContextModule;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.dagger.DaggerSplashComponent;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    @Inject
    OptionsView view;
    @Inject
    OptionsPresenter splashPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerOptionsComponent.builder().
                appComponent(AppController.getAppComponent()).
                optionsContextModule(new OptionsContextModule(this)).
                build().inject(this);

        setContentView(view.constructView());
        splashPresenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
    }


}
