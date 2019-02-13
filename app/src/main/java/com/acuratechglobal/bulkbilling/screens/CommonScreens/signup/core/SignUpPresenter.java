package com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.facebook.GraphResponse;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SignUpPresenter {

    private final SignUpView view;
    private final SignUpModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public SignUpPresenter(SignUpView view, SignUpModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(SignUpSubscription());
//        subscriptions.add(docClicked());
//        subscriptions.add(patClicked());
        subscriptions.add(loginClicked());
        subscriptions.add(emailClicked());
        subscriptions.add(smsClicked());
//        subscriptions.add(fbSignUpClicked());
    }

    private Disposable loginClicked() {
        return view.loginClick().mergeWith(view.backClicked()).subscribe(aVoid -> model.finish());
    }

    private Disposable emailClicked() {
        return view.emailClick().subscribe(aVoid -> view.isEmail());
    }

    private Disposable smsClicked() {
        return view.smsClick().subscribe(aVoid -> view.isSms());
    }

//    private Disposable docClicked() {
//        return view.doctorClick().subscribe(aVoid -> view.toggleTabs(true));
//    }
//    private Disposable patClicked() {
//        return view.patientClick().subscribe(aVoid -> view.toggleTabs(false));
//    }
//    private Disposable fbSignUpClicked() {
//        return view.signupFbClick()
//                .flatMap(validationResponse -> validateNetwork())
//                .subscribe(aVoid -> model.requestLoginFb(), throwable -> {
//                            UiUtils.handleThrowable(throwable);
//                            UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_login), Snackbar.LENGTH_SHORT);
//                        }
//                );
//    }

    private Disposable SignUpSubscription() {
        return view.nextClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(validationResponse -> validateRequest())
                .flatMap(isTermsChecked -> isTermsChecked())
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(signupApi -> SignUp())
                .subscribe(SignUpResponse -> {
                    view.hideLoadingDialog();
                    if (SignUpResponse.getStatus()==1) {
                        AppController.setUserID(SignUpResponse.getSUID());
                        model.gotoPhoneVerification(view.getPhone());
//                        model.finish();
                    } else {
                        String errorMessage =
                                null == SignUpResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : SignUpResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(SignUpSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(SignUpValidations.validateSignUpRequest(getSignUpRequest()))
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

    private Observable<Boolean> isTermsChecked() {
        return view.isChecked()
                .doOnNext(isChecked -> {
                    if (!isChecked) {
                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.strTermsConditionCheck),
                                Snackbar.LENGTH_SHORT);
                    }
                })
                .filter(isChecked -> isChecked);
    }

    private Observable<CommonApiResponse> SignUp() {
        return model.performSignUp(getSignUpRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private SignUpApiRequest getSignUpRequest() {
        return view.getParams();
    }

    public void onGetProfileSuccess(JSONObject object, GraphResponse response) {
        view.setLoginFbRequest(object, response);
        subscriptions.add(RegisterFb());
    }

    private Disposable RegisterFb(){
        return model.performSignUpFb(view.getParamsFb())
                .subscribe(loginResponse -> {
                    view.hideLoadingDialog();
                    if (loginResponse.getStatus()==1) {
//                        model.gotoHome(loginResponse.getUserData());
                    } else {
                        String errorMessage =
                                null == loginResponse.getMessage() ? model.getString(R.string.error_login)
                                        : loginResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
//                    subscriptions.add(loginSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_login), Snackbar.LENGTH_SHORT);
                });
    }

    public void onFBLoginError() {
        UiUtils.showSnackbar(view.getView(), model.getString(R.string.fb_error),
                Snackbar.LENGTH_SHORT);
    }

    public void onFBLoginCancel() {
        UiUtils.showSnackbar(view.getView(), model.getString(R.string.fb_cancel),
                Snackbar.LENGTH_SHORT);
    }

    public void onFBLoginSuccess() {
        view.showLoadingDialog(model.getString(R.string.loading_add_task));
        model.getFacebookProfile();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        model.setActivityResult( requestCode, resultCode, data);
    }
}
