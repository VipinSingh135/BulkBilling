package com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core;

import android.content.Intent;

import com.facebook.GraphResponse;
import com.google.android.material.snackbar.Snackbar;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginPresenter {

    private final LoginView view;
    private final LoginModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public LoginPresenter(LoginView view, LoginModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(loginSubscription());
        subscriptions.add(gotoSignUp());
        subscriptions.add(showPass());
        subscriptions.add(docClicked());
        subscriptions.add(patClicked());
        subscriptions.add(gotoForgotPass());
//        subscriptions.add(fbLoginClicked());
    }

    private Disposable gotoSignUp() {
        return view.signupClick().subscribe(aVoid -> model.gotoSignUp());
    }

    private Disposable gotoForgotPass() {
        return view.forgotPasswdClick().
                subscribe(aVoid -> model.gotoForgotPass(view.getParams().getUserType()));
    }

    private Disposable showPass() {
        return view.btnShowPass().subscribe(aVoid -> view.showPass());
    }

    private Disposable loginSubscription() {
        return view.loginClick()
//                .filter(actionItem -> actionItem == R.id.addTask)
                .flatMap(actionId -> validateRequest())
                .flatMap(validationResponse -> validateNetwork())
                .doOnNext(hasNetwork -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(hasNetwork -> login())
                .subscribe(loginResponse -> {
                    view.hideLoadingDialog();
                    if (loginResponse.getStatus()==1) {
                        model.gotoHome(loginResponse.getUserData());
                    } else {
                        String errorMessage =
                                null == loginResponse.getMessage() ? model.getString(R.string.error_login)
                                        : loginResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(loginSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_login), Snackbar.LENGTH_SHORT);
                });
    }

    private Disposable docClicked() {
        return view.doctorClick().subscribe(aVoid -> view.toggleTabs(true));
    }
    private Disposable patClicked() {
        return view.patientClick().subscribe(aVoid -> view.toggleTabs(false));
    }

//    private Disposable fbLoginClicked() {
//        return view.loginFbClick()
//                .flatMap(validationResponse -> validateNetwork())
//                .subscribe(aVoid -> model.requestLoginFb(), throwable -> {
//                    UiUtils.handleThrowable(throwable);
//                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_login), Snackbar.LENGTH_SHORT);
//                }
//                );
//    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(LoginValidations.validateLoginRequest(getLoginRequest()))
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

    private Observable<LoginApiResponse> login() {
        return model.performLogin(getLoginRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private LoginApiRequest getLoginRequest() {
        return view.getParams();
    }

    public void onGetProfileSuccess(JSONObject object, GraphResponse response) {
        view.setLoginFbRequest(object, response);
        subscriptions.add(LoginFb());
    }

    private Disposable LoginFb(){
        return model.performLoginFb(view.getParamsFb())
                .subscribe(loginResponse -> {
                    view.hideLoadingDialog();
                    if (loginResponse.getStatus()==1) {
                        model.gotoHome(loginResponse.getUserData());
                    } else {
                        String errorMessage =
                                null == loginResponse.getMessage() ? model.getString(R.string.error_login)
                                        : loginResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
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
