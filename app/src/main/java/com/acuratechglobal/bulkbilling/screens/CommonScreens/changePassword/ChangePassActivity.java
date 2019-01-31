package com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core.ChangePassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core.ChangePassView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.dagger.ChangePassModule;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.dagger.DaggerChangePassComponent;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePassActivity extends AppCompatActivity {


    @Inject
    ChangePassView view;

    @Inject
    ChangePassPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerChangePassComponent.builder()
                .appComponent(AppController.getAppComponent())
                .changePassModule(new ChangePassModule(this))
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
