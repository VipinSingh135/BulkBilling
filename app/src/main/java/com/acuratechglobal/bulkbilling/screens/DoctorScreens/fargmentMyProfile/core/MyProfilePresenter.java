package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
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
        view.setProgressDialog();
        subscriptions.add(menuClicked());
        subscriptions.add(editClicked());
        subscriptions.add(GetProfileSubscription());

    }

    private Disposable menuClicked() {
        return view.btnMenu().subscribe(aVoid -> model.openDrawer());
    }

    private Disposable editClicked() {
        return view.btnEdit().subscribe(aVoid -> model.gotoCreateProfileActivity());
    }

    //GetProfile
    private Disposable GetProfileSubscription() {
        return model.networkAvailable()
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_no_internet),
                                Snackbar.LENGTH_SHORT);
                    }
                })
                .filter(networkAvailable -> networkAvailable)
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(profileResponse -> getProfile())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(profileResponse -> {
                    view.hideLoadingDialog();
                    if ( profileResponse.getStatus()== 1) {
//                        UiUtils.showSnackbar(view.getView(), profileResponse.getMessage(), Snackbar.LENGTH_SHORT);
                        view.bindViews(profileResponse.getObject());
                        model.setDays(profileResponse.getObject().getDoctorAvailability());
                        view.setDaysAdapter(model.getSelectedDaysList());

                    } else {
                        String errorMessage =
                                null == profileResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : profileResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
//                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }

    private Observable<GetProfileApiResponse> getProfile(){
        return model.performGetProfile(view.getDocId())
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

    public void onDetach() {
        subscriptions.clear();
    }
}
