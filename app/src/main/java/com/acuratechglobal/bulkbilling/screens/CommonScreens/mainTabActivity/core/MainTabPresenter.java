package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.core;


import android.content.Intent;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.MyProfileDocFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.DocHomeFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.DocAppointmentFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.DocRatingsFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.SettingsFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.PatHomeFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.PatAppointmentFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.PatFavouritesFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyProfile.PatProfileFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.PatRatingFragment;
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
        AppController.setCurrentActivity(MainTabActivity.class.getName());
        subscriptions.add(tabClicked());
//        subscriptions.add(logoutClicked());
    }

    public void onDestroy() {
        AppController.setCurrentActivity(null);
        subscriptions.clear();
    }


    public void openDrawer() {
        view.openDrawer();
    }

    private Disposable tabClicked() {
        return view.tabSelected().subscribe(
                pos -> {
                    view.closeDrawer();
                    switch (pos) {
                        case R.id.action_item1:
                            if (model.getUserType() == 2)
                                view.goToFragment(new DocHomeFragment(), false);
                            else
                                view.goToFragment(new PatHomeFragment(), false);
                            break;

                        case R.id.action_item2:
                            if (model.getUserType() == 2)
                                view.goToFragment(new DocAppointmentFragment(), false);
                            else
                                view.goToFragment(new PatAppointmentFragment(), false);
                            break;
                        case R.id.action_item3:
                            if (model.getUserType() == 2)
                                view.goToFragment(new DocRatingsFragment(), false);
                            else
                                view.goToFragment(new PatRatingFragment(), false);
                            break;
                        case R.id.action_item4:
                            if (model.getUserType() == 2)
                                view.goToFragment(new MyProfileDocFragment(), false);
                            else
                                view.goToFragment(new PatFavouritesFragment(), false);
                            break;
                        case R.id.action_item5:
                            view.goToFragment(new SettingsFragment(), false);
                            break;
                    }
                }
        );
    }

//    private Disposable logoutClicked() {
//        return view.btnLogout().subscribe(aVoid -> model.gotoLoginActivity());
//    }

    public void newIntent(Intent intent) {
        if (intent.getExtras() != null && intent.getExtras().getString("notificationType") != null)
            if (intent.getExtras().getString("notificationType").equals("1")) {
                view.goToFragment(new DocHomeFragment(), false);
//                view.setMenuAdapter(0);

            } else if (intent.getExtras().getString("notificationType").equals("2")) {
                view.goToFragment(new DocAppointmentFragment(), false);
//                view.setMenuAdapter(1);

            } else if (intent.getExtras().getString("notificationType").equals("3")) {

            } else if (intent.getExtras().getString("notificationType").equals("4")) {
                view.goToFragment(new PatAppointmentFragment(), false);
//                view.setMenuAdapter(1);

            } else if (intent.getExtras().getString("notificationType").equals("5")) {

            } else if (intent.getExtras().getString("notificationType").equals("6")) {
                view.goToFragment(new DocRatingsFragment(), false);
//                view.setMenuAdapter(3);

            } else if (intent.getExtras().getString("notificationType").equals("7")) {
                view.goToFragment(new PatAppointmentFragment(), false);
//                view.setMenuAdapter(1);

            } else if (intent.getExtras().getString("notificationType").equals("8")) {

            } else if (intent.getExtras().getString("notificationType").equals("9")) {
                view.goToFragment(new DocAppointmentFragment(), false);
//                view.setMenuAdapter(1);
            }
    }
}
