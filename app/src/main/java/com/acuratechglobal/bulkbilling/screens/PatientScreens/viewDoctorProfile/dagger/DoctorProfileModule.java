package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.dagger;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfileView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class DoctorProfileModule {

    private final DoctorProfileActivity activity;

    public DoctorProfileModule(DoctorProfileActivity activity) {
        this.activity = activity;
    }

    @Provides
    @DoctorProfileScope
    public DoctorProfileView view(SharedPrefsUtil prefs) {
        return new DoctorProfileView(activity,prefs);
    }

    @Provides
    @DoctorProfileScope
    public DoctorProfilePresenter presenter(DoctorProfileView view, DoctorProfileModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new DoctorProfilePresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @DoctorProfileScope
    DoctorProfileModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new DoctorProfileModel(activity, taskApi,  prefs);
    }

}
