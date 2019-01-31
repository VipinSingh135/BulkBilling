package com.acuratechglobal.bulkbilling.screens.CommonScreens.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core.LoginPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core.LoginView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.dagger.DaggerLoginComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.dagger.LoginModule;
import com.acuratechglobal.bulkbilling.utils.FacebookLoginUtils.FacebookLoginListener;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements FacebookLoginListener {

    @Inject
    LoginView view;

    @Inject
    LoginPresenter presenter;

//    @Inject
//    SharedPrefsUtil mySharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerLoginComponent.builder()
                .appComponent(AppController.getAppComponent())
                .loginModule(new LoginModule(this))
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFbLoginSuccess() {
        presenter.onFBLoginSuccess();
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
