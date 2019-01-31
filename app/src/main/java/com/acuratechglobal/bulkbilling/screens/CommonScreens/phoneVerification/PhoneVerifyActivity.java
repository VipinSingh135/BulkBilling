package com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core.PhoneVerifyPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core.PhoneVerifyView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.dagger.DaggerPhoneVerifyComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.dagger.PhoneVerifyModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneVerifyActivity extends AppCompatActivity {

    @Inject
    PhoneVerifyView view;

    @Inject
    PhoneVerifyPresenter presenter;

    String phoneNumber;
    Integer userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getIntent().getExtras();
        if (bundle!=null){
            phoneNumber= bundle.getString("phone");
            userType= bundle.getInt("userType");
        }

        DaggerPhoneVerifyComponent.builder()
                .appComponent(AppController.getAppComponent())
                .phoneVerifyModule(new PhoneVerifyModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate();
        view.setUserType(userType);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
