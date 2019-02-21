package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.GetMyRatingsApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DocRatingsPresenter {

    private final DocRatingsView view;
    private final DocRatingsModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public DocRatingsPresenter(DocRatingsView view, DocRatingsModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(menuClick());
        subscriptions.add(GetRatingListSubscription());
    }

    private Disposable menuClick() {
        return view.menuClick().subscribe(aVoid -> model.openDrawer());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    //GetRatingList
    private Disposable GetRatingListSubscription() {
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
                .flatMap(profileResponse -> getRatings())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(profileResponse -> {
                    view.hideLoadingDialog();
                    if (profileResponse.getStatus() == 1) {
//                        UiUtils.showSnackbar(view.getView(), profileResponse.getMessage(), Snackbar.LENGTH_SHORT);
                        view.bindRatings(profileResponse.getObject());
                        view.setAdapterList(profileResponse.getObject().getReviewHighLights());

                    } else {
                        String errorMessage =
                                null == profileResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : profileResponse.getMessage();
                        view.bindRatings(profileResponse.getObject());
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                });
    }

    private Observable<GetMyRatingsApiResponse> getRatings() {
        return model.performGetRatingList()
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

}
