package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.dagger;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.BookAppointmentActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core.AppointmentModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core.AppointmentPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core.AppointmentView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppointmentModule {

    private final BookAppointmentActivity activity;

    public AppointmentModule(BookAppointmentActivity activity) {
        this.activity = activity;
    }

    @Provides
    @AppointmentScope
    public AppointmentView view(SharedPrefsUtil prefs) {
        return new AppointmentView(activity,prefs);
    }

    @Provides
    @AppointmentScope
    public AppointmentPresenter presenter(AppointmentView view, AppointmentModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new AppointmentPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @AppointmentScope
    AppointmentModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new AppointmentModel(activity, taskApi,  prefs);
    }

}
