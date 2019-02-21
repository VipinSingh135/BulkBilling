package com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.dagger;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.EditProfileActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core.EditProfileModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core.EditProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core.EditProfileView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class EditProfileModule {

    private final EditProfileActivity activity;

    public EditProfileModule(EditProfileActivity activity) {
        this.activity = activity;
    }

    @Provides
    @EditProfileScope
    public EditProfileView view(SharedPrefsUtil prefs) {
        return new EditProfileView(activity,prefs);
    }

    @Provides
    @EditProfileScope
    public EditProfilePresenter presenter(EditProfileView view, EditProfileModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        RxPermissions rxPermissions= new RxPermissions(activity);
        return new EditProfilePresenter(view, model, compositeSubscription, rxSchedulers,rxPermissions);
    }

    @Provides
    @EditProfileScope
    EditProfileModel model(Api taskApi, SharedPrefsUtil prefs) {
        return new EditProfileModel(activity, taskApi,  prefs);
    }
}
