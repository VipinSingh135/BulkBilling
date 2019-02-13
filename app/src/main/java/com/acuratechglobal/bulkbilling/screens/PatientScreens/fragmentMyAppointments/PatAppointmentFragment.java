package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.dagger.DaggerDocAppointmentComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core.PatAppiontmentView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core.PatAppointmentPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.dagger.DaggerPatAppointmentComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.dagger.PatAppointmentModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PatAppointmentFragment extends Fragment {


    @Inject
    PatAppiontmentView view;

    @Inject
    PatAppointmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerPatAppointmentComponent.builder()
                .appComponent(AppController.getAppComponent())
                .patAppointmentModule(new PatAppointmentModule((MainActivity) getActivity()))
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
