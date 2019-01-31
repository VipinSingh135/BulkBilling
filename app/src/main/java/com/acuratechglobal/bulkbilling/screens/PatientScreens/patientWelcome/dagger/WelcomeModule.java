package com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.WelcomeActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core.WelcomeModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core.WelcomePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core.WelcomeView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class WelcomeModule {

    private final WelcomeActivity activity;

    public WelcomeModule(WelcomeActivity activity) {
        this.activity = activity;
    }

    @Provides
    @WelcomeScope
    public WelcomeView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new WelcomeView(activity, progressDialog);
    }

    @Provides
    @WelcomeScope
    public WelcomePresenter presenter(WelcomeView view, WelcomeModel model) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new WelcomePresenter(view, model, compositeSubscription);
    }

    @Provides
    @WelcomeScope
    WelcomeModel model(Api taskApi) {
        return new WelcomeModel(activity, taskApi);
    }
}
