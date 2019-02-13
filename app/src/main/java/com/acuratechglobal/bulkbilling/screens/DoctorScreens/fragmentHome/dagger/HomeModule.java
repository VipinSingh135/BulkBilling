package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core.HomeModel;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core.HomePresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core.HomeView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class HomeModule {

    private final MainActivity activity;

    public HomeModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @HomeScope
    public HomeView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new HomeView(activity, progressDialog);
    }

    @Provides
    @HomeScope
    public HomePresenter presenter(HomeView view, HomeModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new HomePresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @HomeScope
    HomeModel model(Api taskApi , SharedPrefsUtil prefs) {
        return new HomeModel(activity, taskApi,prefs);
    }

}
