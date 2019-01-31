package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core;

import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MyProfilePresenter {

    private final MyProfileView view;
    private final MyProfileModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public MyProfilePresenter(MyProfileView view, MyProfileModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
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
