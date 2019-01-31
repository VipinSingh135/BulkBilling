package com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.ResetPassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core.ResetPassModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core.ResetPassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core.ResetPassView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ResetPassModule {

    private final ResetPassActivity activity;

    public ResetPassModule(ResetPassActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ResetPassScope
    public ResetPassView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new ResetPassView(activity, progressDialog);
    }

    @Provides
    @ResetPassScope
    public ResetPassPresenter presenter(ResetPassView view, ResetPassModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new ResetPassPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @ResetPassScope
    ResetPassModel model(Api taskApi) {
        return new ResetPassModel(activity, taskApi);
    }
}
