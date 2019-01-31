package com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core.SetPassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core.SetPassView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.dagger.DaggerSetPassComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.dagger.SetPassModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetPassActivity extends AppCompatActivity {


    @Inject
    SetPassView view;

    @Inject
    SetPassPresenter presenter;

    int userType=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getIntent().getExtras();
        if (bundle!=null){
            userType= bundle.getInt("userType");
        }
        DaggerSetPassComponent.builder()
                .appComponent(AppController.getAppComponent())
                .setPassModule(new SetPassModule(this))
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
