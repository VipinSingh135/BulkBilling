package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core.MyProfileModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core.MyProfilePresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core.MyProfileView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

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
    public MyProfileView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new MyProfileView(activity, progressDialog);
    }

    @Provides
    @MyProfileScope
    public MyProfilePresenter presenter(MyProfileView view, MyProfileModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new MyProfilePresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @MyProfileScope
    MyProfileModel model(Api taskApi) {
        return new MyProfileModel(activity, taskApi);
    }
}
