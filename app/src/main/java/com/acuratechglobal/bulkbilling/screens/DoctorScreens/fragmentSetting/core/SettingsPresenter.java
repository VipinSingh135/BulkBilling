package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SettingsPresenter {

    private final SettingsView view;
    private final SettingsModel model;
    private final CompositeDisposable subscriptions;

    public SettingsPresenter(SettingsView view, SettingsModel model, CompositeDisposable subscriptions) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(menuClicked());
        subscriptions.add(changePass());
        subscriptions.add(gotoNotification());
        subscriptions.add(gotoProfileSetting());
        subscriptions.add(gotoUpadatePayment());
        subscriptions.add(gotoUpadateSubs());

    }

    private Disposable menuClicked() {
        return view.btnMenu().subscribe(aVoid -> model.openDrawer());
    }

    private Disposable changePass() {
        return view.changePassClicked().subscribe(aVoid -> model.gotoChangePass());
    }

    private Disposable gotoNotification() {
        return view.notifyClicked().subscribe(aVoid -> model.gotoNotification());
    }

    private Disposable gotoProfileSetting() {
        return view.profileClicked().subscribe(aVoid -> model.gotoMyProfile());
    }

    private Disposable gotoUpadatePayment() {
        return view.updatePaymentClicked().subscribe(aVoid -> model.gotoUpdatePayment());
    }

    private Disposable gotoUpadateSubs() {
        return view.updateSubsClicked().subscribe(aVoid -> model.gotoUpdatePayment());
    }


    public void onDetach() {
        subscriptions.clear();
    }
}
