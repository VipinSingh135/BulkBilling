package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.dagger;

import android.app.Activity;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.MyProfileDocFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core.MyProfileModel;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core.MyProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core.MyProfileView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MyProfileModule {

    private final MainActivity activity;

    public MyProfileModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MyProfileScope
    public MyProfileView view(SharedPrefsUtil prefs) {
        return new MyProfileView(activity,prefs);
    }

    @Provides
    @MyProfileScope
    public MyProfilePresenter presenter(MyProfileView view, MyProfileModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new MyProfilePresenter(view, model, compositeSubscription,rxSchedulers);
    }

    @Provides
    @MyProfileScope
    MyProfileModel model(Api taskApi) {
        return new MyProfileModel(activity, taskApi);
    }
}
