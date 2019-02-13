package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SplashPresenter {

    private SplashModel model;


    public SplashPresenter(SplashModel model,SplashView view) {
        this.model = model;
    }


    public void onCreate() {
        model.gotoHomeActivity();
    }


}
