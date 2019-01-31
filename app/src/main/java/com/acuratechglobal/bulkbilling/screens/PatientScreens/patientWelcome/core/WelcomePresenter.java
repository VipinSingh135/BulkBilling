package com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class WelcomePresenter {

    private final WelcomeView view;
    private final WelcomeModel model;
    private final CompositeDisposable subscriptions;


    public WelcomePresenter(WelcomeView view, WelcomeModel model, CompositeDisposable subscriptions) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(doneClicked());
    }

    private Disposable doneClicked() {
        return view.doneClick().subscribe(aVoid -> model.gotoLoginScreen());
    }


    public void onDestroy() {
        subscriptions.clear();
    }

}
