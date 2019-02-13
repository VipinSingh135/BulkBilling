package com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.SetPasswordValidation;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SetPassPresenter {

    private final SetPassView view;
    private final SetPassModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public SetPassPresenter(SetPassView view, SetPassModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
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
                .flatMap(hasNetwork -> SignUp())
                .subscribe(SignUpResponse -> {
                    if (AppController.getUserType()!=3){
                        view.hideLoadingDialog();
                    }
                    if (SignUpResponse.getStatus()==1) {
                        if (AppController.getUserType()!=3){
                            model.gotoSelectPlan();
                        }else{
                            subscriptions.add(patientScreen());
                        }
//                        model.finish();
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


    private Disposable patientScreen() {
        return SelectPlan()
                .subscribe(SignUpResponse -> {
                    view.hideLoadingDialog();
                    if (SignUpResponse.getStatus()==1) {
                        model.gotoWelcomeScreen();
//                        model.finish();
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


    private Observable<CommonApiResponse> SelectPlan() {
        return model.performSelectPlan(getSetPlanRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private SetPlanApiRequest getSetPlanRequest() {
        return view.getPlanParams();
    }

    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(SetPasswordValidation.validateSetPassRequest(getSetPassRequest()))
                .subscribeOn(rxSchedulers.background())
                .observeOn(rxSchedulers.androidUI())
                .doOnNext(validationResponse -> {
                    if (!validationResponse.getSuccess()) {
                        UiUtils.showSnackbar(view.getView(), validationResponse.getFailReason(), Snackbar.LENGTH_SHORT);
                        view.handleErrorCode(validationResponse.getFailCode());
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

    private Observable<CommonApiResponse> SignUp() {
        return model.performSetPass(getSetPassRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private SetPassApiRequest getSetPassRequest() {
        return view.getParams();
    }

}
