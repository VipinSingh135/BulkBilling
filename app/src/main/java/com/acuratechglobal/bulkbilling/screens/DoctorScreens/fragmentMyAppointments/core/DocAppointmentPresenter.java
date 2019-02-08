package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter {

    private final HomeView view;
    private final HomeModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public HomePresenter(HomeView view, HomeModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(menuClick());
    }

    private Disposable menuClick() {
        return view.menuClick().subscribe(aVoid -> model.openDrawer());
    }

    public void onDestroy() {
        subscriptions.clear();
    }
}
