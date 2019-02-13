package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class OptionsPresenter {

    private OptionsModel model;
    private OptionsView view;
    private CompositeDisposable subscriptions;

    public OptionsPresenter(OptionsModel model, OptionsView view, CompositeDisposable subscriptions) {
        this.model = model;
        this.view= view;
        this.subscriptions= subscriptions;

    }

    public void onCreate() {
        subscriptions.add(docClicked());
        subscriptions.add(patClicked());
    }

    public void onDestroy() {
        subscriptions.clear();
    }


    private Disposable docClicked(){
        return view.doctorClick().subscribe(avoid ->model.gotoLoginActivity(true));
    }

    private Disposable patClicked(){
        return view.patientClick().subscribe(avoid ->model.gotoLoginActivity(false));
    }


//    private Subscription getHeroesList() {
//
//        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
//            if (!networkAvailable) {
//                Log.d("no conn", "no connexion");
//            }
//        }).
//                filter(isNetworkAvailable -> true).
//                flatMap(isAvailable -> model.isNetworkAvailable()).
//                subscribeOn(rxSchedulers.internet()).
//                observeOn(rxSchedulers.androidThread()).subscribe(aBoolean -> model.gotoHeroesListActivity(), throwable -> UiUtils.handleThrowable(throwable));
//    }

}
