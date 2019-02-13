package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.dagger.DaggerHomeComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core.DocRatingsView;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core.DocRatingsPresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.dagger.DaggerDocRatingsComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.dagger.DocRatingsModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DocRatingsFragment extends Fragment {


    @Inject
    DocRatingsView view;

    @Inject
    DocRatingsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerDocRatingsComponent.builder()
                .appComponent(AppController.getAppComponent())
                .docRatingsModule(new DocRatingsModule((MainActivity) getActivity()))
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
