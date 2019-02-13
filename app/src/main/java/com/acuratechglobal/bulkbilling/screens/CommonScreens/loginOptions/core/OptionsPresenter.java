package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SplashPresenter {

    private SplashModel model;
    private SplashView view;


    public SplashPresenter(SplashModel model,SplashView view, CompositeDisposable subscriptions) {
        this.model = model;
        this.view= view;
        subscriptions.add(docClicked());
        subscriptions.add(patClicked());
    }


    public void onCreate() {
        model.gotoHomeActivity();
    }

    public void onDestroy() {
//        subscriptions.clear();

    }

    public void showOptions() {
        view.showOptions();
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
