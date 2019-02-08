package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.dagger.DaggerHomeComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.core.HomePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.core.HomeView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.dagger.DaggerPatHomeComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.dagger.PatHomeModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PatHomeFragment extends Fragment {


    @Inject
    HomeView view;

    @Inject
    HomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerPatHomeComponent.builder()
                .appComponent(AppController.getAppComponent())
                .patHomeModule(new PatHomeModule((MainActivity) getActivity()))
                .build()
                .inject(this);
        presenter.onCreate();
        return view.getView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
