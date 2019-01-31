package com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.ForgotPassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core.ForgotPassModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core.ForgotPassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core.ForgotPassView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ForgotPassModule {

    private final ForgotPassActivity activity;

    public ForgotPassModule(ForgotPassActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ForgotPassScope
    public ForgotPassView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new ForgotPassView(activity, progressDialog);
    }

    @Provides
    @ForgotPassScope
    public ForgotPassPresenter presenter(ForgotPassView view, ForgotPassModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new ForgotPassPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @ForgotPassScope
    ForgotPassModel model(Api taskApi) {
        return new ForgotPassModel(activity, taskApi);
    }
}
