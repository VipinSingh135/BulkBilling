package com.acuratechglobal.bulkbilling.screens.CommonScreens.login.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core.LoginModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core.LoginPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core.LoginView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class LoginModule {

    private final LoginActivity activity;

    public LoginModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    @LoginScope
    public LoginView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new LoginView(activity, progressDialog);
    }

    @Provides
    @LoginScope
    public LoginPresenter presenter(LoginView view, LoginModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new LoginPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @LoginScope
    LoginModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new LoginModel(activity, taskApi,prefs);
    }
}
