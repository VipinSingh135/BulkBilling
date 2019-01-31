package com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.SetPassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core.SetPassModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core.SetPassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core.SetPassView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class SetPassModule {

    private final SetPassActivity activity;

    public SetPassModule(SetPassActivity activity) {
        this.activity = activity;
    }

    @Provides
    @SetPassScope
    public SetPassView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new SetPassView(activity, progressDialog);
    }

    @Provides
    @SetPassScope
    public SetPassPresenter presenter(SetPassView view, SetPassModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new SetPassPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @SetPassScope
    SetPassModel model(Api taskApi) {
        return new SetPassModel(activity, taskApi);
    }
}
