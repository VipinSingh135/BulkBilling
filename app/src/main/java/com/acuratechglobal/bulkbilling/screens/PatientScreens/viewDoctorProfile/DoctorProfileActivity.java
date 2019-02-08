package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile;

import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core.CreateProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core.CreateProfileView;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.dagger.CreateProfileModule;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.dagger.DaggerCreateProfileComponent;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateProfileActivity extends AppCompatActivity {


    @Inject
    CreateProfileView view;

    @Inject
    CreateProfilePresenter presenter;

//    public static void start(Context context) {
//        Intent intent = new Intent(context, CreateProfileActivity.class);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCreateProfileComponent.builder()
                .appComponent(AppController.getAppComponent())
                .createProfileModule(new CreateProfileModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        Bundle bundle= getIntent().getExtras();
        boolean isUpdate = false;
        if (bundle!=null){
            isUpdate= bundle.getBoolean("isUpdate",false);
        }
        presenter.onCreate();
        view.setData(isUpdate);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode,resultCode,data);
    }


    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
