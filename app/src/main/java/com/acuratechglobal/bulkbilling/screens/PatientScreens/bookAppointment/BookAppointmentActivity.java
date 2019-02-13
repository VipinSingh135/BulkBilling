package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core.AppointmentPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core.AppointmentView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.dagger.AppointmentModule;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.dagger.DaggerAppointmentComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.dagger.DaggerDoctorProfileComponent;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BookAppointmentActivity extends AppCompatActivity {


    @Inject
    AppointmentView view;

    @Inject
    AppointmentPresenter presenter;

//    public static void start(Context context) {
//        Intent intent = new Intent(context, CreateProfileActivity.class);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAppointmentComponent.builder()
                .appComponent(AppController.getAppComponent())
                .appointmentModule(new AppointmentModule(this))
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
