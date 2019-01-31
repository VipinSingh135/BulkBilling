package com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.PhoneVerifyActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core.PhoneVerifyModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core.PhoneVerifyPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core.PhoneVerifyView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PhoneVerifyModule {

    private final PhoneVerifyActivity activity;

    public PhoneVerifyModule(PhoneVerifyActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PhoneVerifyScope
    public PhoneVerifyView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new PhoneVerifyView(activity, progressDialog);
    }

    @Provides
    @PhoneVerifyScope
    public PhoneVerifyPresenter presenter(PhoneVerifyView view, PhoneVerifyModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new PhoneVerifyPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @PhoneVerifyScope
    PhoneVerifyModel model(Api taskApi) {
        return new PhoneVerifyModel(activity, taskApi);
    }
}
