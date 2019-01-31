package com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core.ChangePassModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core.ChangePassPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core.ChangePassView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ChangePassModule {

    private final ChangePassActivity activity;

    public ChangePassModule(ChangePassActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ChangePassScope
    public ChangePassView view(SharedPrefsUtil prefs) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new ChangePassView(activity, progressDialog,prefs);
    }

    @Provides
    @ChangePassScope
    public ChangePassPresenter presenter(ChangePassView view, ChangePassModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new ChangePassPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @ChangePassScope
    ChangePassModel model(Api taskApi) {
        return new ChangePassModel(activity, taskApi);
    }
}
