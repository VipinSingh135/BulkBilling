package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.FavouritesListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetDoctorListApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class FavouritesPresenter {

    private final FavouritesView view;
    private final FavouritesModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public FavouritesPresenter(FavouritesView view, FavouritesModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
//        subscriptions.add(menuClick());
        subscriptions.add(GetFavouriteListSubscription());
        subscriptions.add(respondToItemClick());

//        subscriptions.add(respondToItemClick());

    }

//    private Disposable menuClick() {
//        return view.menuClick().subscribe(aVoid -> model.openDrawer());
//    }

    //GetDoctorList
    private Disposable GetFavouriteListSubscription() {
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
                .flatMap(profileResponse -> getDoctors())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(profileResponse -> {
                    view.hideLoadingDialog();
                    if (profileResponse.getStatus() == 1) {
//                        UiUtils.showSnackbar(view.getView(), profileResponse.getMessage(), Snackbar.LENGTH_SHORT);
                        view.setAdapterList(profileResponse.getList());

                    } else {
                        String errorMessage =
                                null == profileResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : profileResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                });
    }

    private Observable<FavouritesListApiResponse> getDoctors() {
        return model.performGetFavList()
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

//    private Disposable respondToItemClick() {
//        return view.itemClick().subscribe(view::gotoDocProfile);
//    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Disposable respondToItemClick() {
        return view.itemClick().subscribe(view::gotoDocProfile);
    }
}
