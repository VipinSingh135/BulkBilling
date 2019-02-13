package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core.PatAppiontmentView;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core.PatAppointmentModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core.PatAppointmentPresenter;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PatAppointmentModule {

    private final MainActivity activity;

    public PatAppointmentModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PatAppointmentScope
    public PatAppiontmentView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new PatAppiontmentView(activity, progressDialog);
    }

    @Provides
    @PatAppointmentScope
    public PatAppointmentPresenter presenter(PatAppiontmentView view, PatAppointmentModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new PatAppointmentPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @PatAppointmentScope
    PatAppointmentModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new PatAppointmentModel(activity, taskApi,prefs);
    }
}
