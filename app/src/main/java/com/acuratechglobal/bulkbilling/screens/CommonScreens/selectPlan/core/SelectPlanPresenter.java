package com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SelectPlanPresenter {

    private final SelectPlanView view;
    private final SelectPlanModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public SelectPlanPresenter(SelectPlanView view, SelectPlanModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(doneClicked());
        subscriptions.add(backClicked());
        subscriptions.add(basicClicked());
        subscriptions.add(premiumClicked());
    }


    private Disposable backClicked() {
        return view.backClicked().subscribe(aVoid -> model.finish());
    }
    private Disposable basicClicked() {
        return view.basicClicked().subscribe(aVoid -> view.setPlan(true));
    }
    private Disposable premiumClicked() {
        return view.premiumClicked().subscribe(aVoid -> view.setPlan(false));
    }

    private Disposable doneClicked() {
        return view.doneClick()
                .throttleFirst(1, TimeUnit.SECONDS) // maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(hasNetwork -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(hasNetwork -> SelectPlan())
                .subscribe(SignUpResponse -> {
                    view.hideLoadingDialog();
                    if (SignUpResponse.getStatus()==1) {
                        model.gotoHomeScreen();
                        model.finish();
                    } else {
                        String errorMessage =
                                null == SignUpResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : SignUpResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(doneClicked());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }


    public void onDestroy() {
        subscriptions.clear();
    }

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

    private Observable<CommonApiResponse> SelectPlan() {
        return model.performSelectPlan(getSetPlanRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private SetPlanApiRequest getSetPlanRequest() {
        return view.getParams();
    }

}
