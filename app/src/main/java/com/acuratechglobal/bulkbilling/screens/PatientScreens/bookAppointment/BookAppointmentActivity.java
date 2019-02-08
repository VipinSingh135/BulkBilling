package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile;

import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.dagger.DaggerCreateProfileComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfileView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.dagger.DaggerDoctorProfileComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.dagger.DoctorProfileModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorProfileActivity extends AppCompatActivity {


    @Inject
    DoctorProfileView view;

    @Inject
    DoctorProfilePresenter presenter;

//    public static void start(Context context) {
//        Intent intent = new Intent(context, CreateProfileActivity.class);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerDoctorProfileComponent.builder()
                .appComponent(AppController.getAppComponent())
                .doctorProfileModule(new DoctorProfileModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate(getIntent().getExtras());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }



    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
