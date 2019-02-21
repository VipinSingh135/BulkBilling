package com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile;

import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core.EditProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core.EditProfileView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.dagger.DaggerEditProfileComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.dagger.EditProfileModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {


    @Inject
    EditProfileView view;

    @Inject
    EditProfilePresenter presenter;

//    public static void start(Context context) {
//        Intent intent = new Intent(context, EditProfileActivity.class);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerEditProfileComponent.builder()
                .appComponent(AppController.getAppComponent())
                .editProfileModule(new EditProfileModule(this))
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
