package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core.MainPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core.MainView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.dagger.DaggerMainComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.dagger.MainModule;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainView view;
    @Inject
    MainPresenter mainPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainComponent.builder().
                appComponent(AppController.getAppComponent()).
                mainModule(new MainModule(this)).
                build().inject(this);

        setContentView(view.constructView());
        mainPresenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }

    public void OpenDrawer(){
        mainPresenter.openDrawer();;
    }


}
