package com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.AddRatingRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AddRatingPresenter {

    private final AddRatingView view;
    private final AddRatingModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;

    public AddRatingPresenter(AddRatingView view, AddRatingModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(backClick());
        subscriptions.add(star1());
        subscriptions.add(star2());
        subscriptions.add(star3());
        subscriptions.add(star4());
        subscriptions.add(star5());
        subscriptions.add(AddRatingSubscription());
    }

    private Disposable backClick() {
        return view.backClick().subscribe(aVoid -> model.finish());
    }


    private Disposable star1() {
        return view.star1().subscribe(aVoid -> view.setRatingStars(1));
    }

    private Disposable star2() {
        return view.star2().subscribe(aVoid -> view.setRatingStars(2));
    }

    private Disposable star3() {
        return view.star3().subscribe(aVoid -> view.setRatingStars(3));
    }

    private Disposable star4() {
        return view.star4().subscribe(aVoid -> view.setRatingStars(4));
    }

    private Disposable star5() {
        return view.star5().subscribe(aVoid -> view.setRatingStars(5));
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    //AddRating
    private Disposable AddRatingSubscription() {
        return view.submitClicked()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(Common -> AddRating())
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
                        view.showToast(response.getMessage());
                        model.finish();

                    } else {
                        String errorMessage =
                                null == response.getMessage() ? model.getString(R.string.error_signUp)
                                        : response.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(AddRatingSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }

    private Observable<CommonApiResponse> AddRating() {
        return model.performAddRating(getRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private AddRatingRequest getRequest() {
        return view.getParams();
    }

    //Network
    private Observable<Boolean> validateNetwork() {
        return model.networkAvailable()
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_no_internet),
                                Snackbar.LENGTH_SHORT);
                    }
                })
                .filter(networkAvailable -> networkAvailable);
    }
}
