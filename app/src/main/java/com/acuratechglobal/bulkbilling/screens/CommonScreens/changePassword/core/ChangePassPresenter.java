package com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.ChangePassApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.ChangePassValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ChangePassPresenter {

    private final ChangePassView view;
    private final ChangePassModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public ChangePassPresenter(ChangePassView view, ChangePassModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(doneClicked());
        subscriptions.add(backClicked());
    }


    private Disposable backClicked() {
        return view.backClicked().subscribe(aVoid -> model.finish());
    }


    private Disposable doneClicked() {
        return view.doneClick()
                .throttleFirst(1, TimeUnit.SECONDS) // maybe you want to ignore multiple clicks
                .flatMap(validationResponse -> validateRequest())
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(hasNetwork -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(hasNetwork -> ChangePassApi())
                .subscribe(CommonApiResponse -> {
                    view.hideLoadingDialog();
                    if (CommonApiResponse.getStatus()==1) {
                        model.gotoLogin();
//                        model.finish();
                    } else {
                        String errorMessage =
                                null == CommonApiResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : CommonApiResponse.getMessage();
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


    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(ChangePassValidations.validateChangePassRequest(getChangePassRequest()))
                .subscribeOn(rxSchedulers.background())
                .observeOn(rxSchedulers.androidUI())
                .doOnNext(validationResponse -> {
                    if (!validationResponse.getSuccess()) {
                        UiUtils.showSnackbar(view.getView(), validationResponse.getFailReason(), Snackbar.LENGTH_SHORT);
//                        view.handleErrorCode(validationResponse.getFailCode());
                    }
                })
                .filter(ValidationResponse::getSuccess);
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

    private Observable<CommonApiResponse> ChangePassApi() {
        return model.performChangePass(getChangePassRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private ChangePassApiRequest getChangePassRequest() {
        return view.getParams();
    }

}
