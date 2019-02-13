package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core.DocAppointmentModel;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core.DocAppointmentPresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core.DocAppiontmentView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class DocAppointmentModule {

    private final MainActivity activity;

    public DocAppointmentModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @DocAppointmentScope
    public DocAppiontmentView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new DocAppiontmentView(activity, progressDialog);
    }

    @Provides
    @DocAppointmentScope
    public DocAppointmentPresenter presenter(DocAppiontmentView view, DocAppointmentModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new DocAppointmentPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @DocAppointmentScope
    DocAppointmentModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new DocAppointmentModel(activity, taskApi,prefs);
    }
}
