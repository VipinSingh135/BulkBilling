package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core.SettingsPresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core.SettingsView;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.dagger.DaggerSettingsComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.dagger.SettingsModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {


    @Inject
    SettingsView view;

    @Inject
    SettingsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerSettingsComponent.builder()
                .appComponent(AppController.getAppComponent())
                .settingsModule(new SettingsModule(this))
                .build()
                .inject(this);
        presenter.onCreate();
        return view.getView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetach();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
