package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.dagger;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core.CreateProfileModel;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core.CreateProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core.CreateProfileView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class CreateProfileModule {

    private final CreateProfileActivity activity;

    public CreateProfileModule(CreateProfileActivity activity) {
        this.activity = activity;
    }

    @Provides
    @CreateProfileScope
    public CreateProfileView view(SharedPrefsUtil prefs) {
        return new CreateProfileView(activity,prefs);
    }

    @Provides
    @CreateProfileScope
    public CreateProfilePresenter presenter(CreateProfileView view, CreateProfileModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        RxPermissions rxPermissions= new RxPermissions(activity);
        return new CreateProfilePresenter(view, model, compositeSubscription, rxSchedulers,rxPermissions);
    }

    @Provides
    @CreateProfileScope
    CreateProfileModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new CreateProfileModel(activity, taskApi,  prefs);
    }
}
