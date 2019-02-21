package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.dagger.DaggerPatHomeComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core.PatRatingPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core.PatRatingView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.dagger.DaggerPatRatingComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.dagger.PatRatingModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PatRatingFragment extends Fragment {


    @Inject
    PatRatingView view;

    @Inject
    PatRatingPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerPatRatingComponent.builder()
                .appComponent(AppController.getAppComponent())
                .patRatingModule(new PatRatingModule((MainActivity) getActivity()))
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
