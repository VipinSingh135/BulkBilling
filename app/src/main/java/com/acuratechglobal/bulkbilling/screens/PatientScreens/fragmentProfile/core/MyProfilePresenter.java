package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.GetPatientProfileApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

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
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(menuClick());
        subscriptions.add(editClick());
    }

    public void onResume(){
        subscriptions.add(GetProfileSubscription());
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
                        model.setPatData(profileResponse.getObject());

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
    private Observable<GetPatientProfileApiResponse> getProfile(){
        return model.performGetProfile()
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

    private Disposable menuClick() {
        return view.menuClick().subscribe(aVoid -> model.openDrawer());
    }

    private Disposable editClick() {
        return view.editClick().subscribe(aVoid -> model.gotoEditProfile(view.getProfileData()));
    }

    public void onDestroy() {
        subscriptions.clear();
    }
}
