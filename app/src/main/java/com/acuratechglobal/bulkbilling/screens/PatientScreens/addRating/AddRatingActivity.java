package com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core.AddRatingPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core.AddRatingView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.dagger.AddRatingModule;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.dagger.DaggerAddRatingComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.dagger.DaggerPatFavouritesComponent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class AddRatingActivity extends AppCompatActivity {


    @Inject
    AddRatingView view;

    @Inject
    AddRatingPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAddRatingComponent.builder()
                .appComponent(AppController.getAppComponent())
                .addRatingModule(new AddRatingModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate();
        view.bindData(getIntent().getExtras());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
         presenter.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
