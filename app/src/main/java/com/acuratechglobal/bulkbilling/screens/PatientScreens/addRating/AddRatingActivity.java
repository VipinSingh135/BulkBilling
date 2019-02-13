package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.dagger.DaggerPatHomeComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core.FavouritesPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core.FavouritesView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.dagger.DaggerPatFavouritesComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.dagger.FavouritesModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PatFavouritesFragment extends Fragment {


    @Inject
    FavouritesView view;

    @Inject
    FavouritesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerPatFavouritesComponent.builder()
                .appComponent(AppController.getAppComponent())
                .favouritesModule(new FavouritesModule((MainActivity) getActivity()))
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
