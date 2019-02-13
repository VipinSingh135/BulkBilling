package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core.MyProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core.MyProfileView;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.dagger.DaggerMyProfileComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.dagger.MyProfileModule;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.dagger.DaggerSettingsComponent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyProfileDocFragment extends Fragment {


    @Inject
    MyProfileView view;

    @Inject
    MyProfilePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerMyProfileComponent.builder()
                .appComponent(AppController.getAppComponent())
                .myProfileModule(new MyProfileModule((MainActivity) getActivity()))
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
