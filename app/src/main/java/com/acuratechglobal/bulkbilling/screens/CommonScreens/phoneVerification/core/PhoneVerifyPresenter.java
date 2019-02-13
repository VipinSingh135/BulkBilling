package com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class PhoneVerifyPresenter {

    private final PhoneVerifyView view;
    private final PhoneVerifyModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;

    public PhoneVerifyPresenter(PhoneVerifyView view, PhoneVerifyModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
//        subscriptions.add(sendVerificationCode());
        subscriptions.add(codeEntered());
        subscriptions.add(backClicked());
        subscriptions.add(verifyClicked());
    }

    private Disposable backClicked() {
        return view.backClicked().subscribe(aVoid -> model.finish());
    }

    private Disposable verifyClicked() {
        return view.verifyClick()
                .flatMap(isVerified ->isVerified())
                .subscribe(aVoid -> {
//                    view.showToast("verified number");
                    model.gotoSetPassword();
                });
    }

    private Observable<Boolean> isVerified() {
        return model.isVerified(view.getCode())
                .doOnNext(isVerified -> {
                    if (!isVerified) {
                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.invalid_otp),
                                Snackbar.LENGTH_SHORT);
                    }
                })
                .filter(isVerified -> isVerified);
    }

//    private Disposable sendVerificationCode() {
//        return model.sendCode()
//                .observeOn(rxSchedulers.androidUI())
//                .subscribe(phoneVerificationDataModel -> {
//                    if (phoneVerificationDataModel.getStatus() == 1) {
//                        view.showToast(phoneVerificationDataModel.getMessage());
//                    } else if (phoneVerificationDataModel.getStatus() == 2) {
//                        view.showToast(phoneVerificationDataModel.getMessage());
//                    } else if (phoneVerificationDataModel.getStatus() == 3) {
//                        view.showToast(phoneVerificationDataModel.getMessage());
//                    }
//                });
//    }

    private Observable<Boolean> edCode1Entered() {
        return view.edCode1Event().observeOn(AndroidSchedulers.mainThread())
                .map(charSequence -> {
                    if (!charSequence.isEmpty()) {
                        view.changeFocus1(true);
                        return true;
                    } else {
                        view.changeFocus1(false);
                        return false;
                    }
                });
    }

    private Observable<Boolean> edCode2Entered() {
        return view.edCode2Event().observeOn(AndroidSchedulers.mainThread())
                .map(charSequence -> {
                    if (!charSequence.isEmpty()) {
                        view.changeFocus2(true);
                        return true;
                    } else {
                        view.changeFocus2(false);
                        return false;
                    }
                });
    }

    private Observable<Boolean> edCode3Entered() {
        return view.edCode3Event().observeOn(AndroidSchedulers.mainThread())
                .map(charSequence -> {
                    if (!charSequence.isEmpty()) {
                        view.changeFocus3(true);
                        return true;
                    } else {
                        view.changeFocus3(false);
                        return false;
                    }
                });
    }

    private Observable<Boolean> edCode4Entered() {
        return view.edCode4Event().observeOn(AndroidSchedulers.mainThread())
                .map(charSequence -> {
                    if (!charSequence.isEmpty()) {
                        view.changeFocus4(true);
                        return true;
                    } else {
                        view.changeFocus4(false);

                        return false;
                    }
                });
    }

    private Disposable codeEntered() {
        return Observable.combineLatest(edCode1Entered(), edCode2Entered(), edCode3Entered(), edCode4Entered(),
                (PhoneVerify1, PhoneVerify2, PhoneVerify3, PhoneVerify4)
                        -> PhoneVerify1 && PhoneVerify2 && PhoneVerify3 && PhoneVerify4)
                .subscribe(aBoolean -> view.enableVerifyBtn(aBoolean)
                );
    }

//    private Disposable SignUpSubscription() {
//        return view.nextClick()
////                .filter(actionItem -> actionItem == R.id.addTask)
//                .flatMap(actionId -> validateRequest())
//                .flatMap(validationResponse -> validateNetwork())
////                .doOnNext(hasNetwork -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
//                .flatMap(hasNetwork -> SignUp())
//                .subscribe(SignUpResponse -> {
//                    view.hideLoadingDialog();
//                    if (SignUpResponse.getSuccess()) {
//                        model.finish();
//                    } else {
//                        String errorMessage =
//                                null == SignUpResponse.getFailReason() ? model.getString(R.string.error_signUp)
//                                        : SignUpResponse.getFailReason();
//                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
//                    }
//                }, throwable -> {
//                    view.hideLoadingDialog();
//                    UiUtils.handleThrowable(throwable);
//                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
//                });
//    }

    public void onDestroy() {
        subscriptions.clear();
    }

}
