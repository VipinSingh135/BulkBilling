package com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core.WelcomePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core.WelcomeView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.dagger.DaggerWelcomeComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.dagger.WelcomeModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {


    @Inject
    WelcomeView view;

    @Inject
    WelcomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWelcomeComponent.builder()
                .appComponent(AppController.getAppComponent())
                .welcomeModule(new WelcomeModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
