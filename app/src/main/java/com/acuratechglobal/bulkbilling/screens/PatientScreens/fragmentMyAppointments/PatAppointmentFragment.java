package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.dagger.DaggerHomeComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core.DocAppointmentPresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core.DocAppiontmentView;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.dagger.DaggerDocAppointmentComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.dagger.DocAppointmentModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DocAppointmentFragment extends Fragment {


    @Inject
    DocAppiontmentView view;

    @Inject
    DocAppointmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerDocAppointmentComponent.builder()
                .appComponent(AppController.getAppComponent())
                .docAppointmentModule(new DocAppointmentModule((MainActivity) getActivity()))
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
