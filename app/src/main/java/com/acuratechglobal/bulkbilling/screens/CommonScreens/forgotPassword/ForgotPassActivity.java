package com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core.ForgotPassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core.ForgotPassView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.dagger.DaggerForgotPassComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.dagger.ForgotPassModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassActivity extends AppCompatActivity {


    @Inject
    ForgotPassView view;

    @Inject
    ForgotPassPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerForgotPassComponent.builder()
                .appComponent(AppController.getAppComponent())
                .forgotPassModule(new ForgotPassModule(this))
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
