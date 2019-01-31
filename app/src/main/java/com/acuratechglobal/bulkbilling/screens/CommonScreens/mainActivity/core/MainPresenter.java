package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core;


import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.DocHomeFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.SettingsFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.PatHomeFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.PatProfileFragment;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainPresenter {

    private final MainModel model;
    private final MainView view;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public MainPresenter(MainModel model, MainView view, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }



    public void onCreate() {
        subscriptions.add(sideMenuClicked());
        subscriptions.add(logoutClicked());
    }

    public void onDestroy() {
        subscriptions.clear();
    }


    public void openDrawer() {
        view.openDrawer();
    }

    private Disposable sideMenuClicked(){
        return view.itemMenuClicks().subscribe(
                pos ->{
                  view.setMenuAdapter(pos);
                  view.closeDrawer();
                  if (pos==0){
                      if (model.getUserType()==2) {
                          view.goToFragment(new DocHomeFragment(), false);
                      }else{
                          view.goToFragment(new PatHomeFragment(), false);
                      }
                  }else if (pos==3){
                      if (model.getUserType()==2) {
                          model.gotoCreateProfileActivity();
                      }else{
                          view.goToFragment(new PatProfileFragment(), false);
                      }
                  }else if (pos==6){
                      view.goToFragment(new SettingsFragment(),false);

                  }else{
                  }
                }
        );
    }

    private Disposable logoutClicked(){
        return view.btnLogout().subscribe(aVoid -> model.gotoLoginActivity());
    }

}
