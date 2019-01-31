package com.acuratechglobal.bulkbilling.screens.CommonScreens.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core.SignUpPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core.SignUpView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.dagger.DaggerSignUpComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.dagger.SignUpModule;
import com.acuratechglobal.bulkbilling.utils.FacebookLoginUtils.FacebookLoginListener;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements FacebookLoginListener {


    @Inject
    SignUpView view;

    @Inject
    SignUpPresenter presenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSignUpComponent.builder()
                .appComponent(AppController.getAppComponent())
                .signUpModule(new SignUpModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onFbLoginSuccess() {
        presenter.onFBLoginSuccess();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onFbLoginCancel() {
        presenter.onFBLoginCancel();
    }

    @Override
    public void onFbLoginError() {
        presenter.onFBLoginError();
    }

    @Override
    public void onGetprofileSuccess(JSONObject object, GraphResponse response) {
        presenter.onGetProfileSuccess(object,response);
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
