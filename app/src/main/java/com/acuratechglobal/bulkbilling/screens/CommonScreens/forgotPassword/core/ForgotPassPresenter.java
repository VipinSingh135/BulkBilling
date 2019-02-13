package com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SendOtpRequest;
import com.acuratechglobal.bulkbilling.api.response.ResetPassApiResponse;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.SendOtpValidation;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ForgotPassPresenter {

    private final ForgotPassView view;
    private final ForgotPassModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public ForgotPassPresenter(ForgotPassView view, ForgotPassModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
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
                .flatMap(hasNetwork -> SendOtp())
                .subscribe(ResetPassApiResponse -> {
                    view.hideLoadingDialog();
                    if (ResetPassApiResponse.getStatus()==1) {
                        AppController.setUserID(ResetPassApiResponse.getOtpObject().getUID());
                        model.gotoNewPassword(view.getParams().getUserType());
//                        model.finish();
                    } else {
                        String errorMessage =
                                null == ResetPassApiResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : ResetPassApiResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }


    public void onDestroy() {
        subscriptions.clear();
    }


    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(SendOtpValidation.validateSendOtp(getSendOtpRequest()))
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

    private Observable<ResetPassApiResponse> SendOtp() {
        return model.performSendOtp(getSendOtpRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private SendOtpRequest getSendOtpRequest() {
        return view.getParams();
    }

}
