package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core;


public class SplashPresenter {

    private SplashModel model;


    public SplashPresenter(SplashModel model) {
        this.model = model;

    }


    public void onCreate() {
        model.gotoLoginActivity();
    }

    public void onDestroy() {
//        subscriptions.clear();

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
