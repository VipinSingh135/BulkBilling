package com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core.ResetPassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core.ResetPassView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.dagger.DaggerResetPassComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.dagger.ResetPassModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPassActivity extends AppCompatActivity {


    @Inject
    ResetPassView view;

    @Inject
    ResetPassPresenter presenter;
    int userType=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        if (bundle!=null){
            userType= bundle.getInt("userType");
        }
        DaggerResetPassComponent.builder()
                .appComponent(AppController.getAppComponent())
                .resetPassModule(new ResetPassModule(this))
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
