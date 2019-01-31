package com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.SignUpActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core.SignUpModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core.SignUpPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core.SignUpView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class SignUpModule {

    private final SignUpActivity activity;

    public SignUpModule(SignUpActivity activity) {
        this.activity = activity;
    }

    @Provides
    @SignUpScope
    public SignUpView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new SignUpView(activity, progressDialog);
    }

    @Provides
    @SignUpScope
    public SignUpPresenter presenter(SignUpView view, SignUpModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new SignUpPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @SignUpScope
    SignUpModel model(Api taskApi) {
        return new SignUpModel(activity, taskApi);
    }
}
