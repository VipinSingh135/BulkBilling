package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.BookAppointmentValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AppointmentPresenter {

    private final AppointmentView view;
    private final AppointmentModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public AppointmentPresenter(AppointmentView view, AppointmentModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate(Bundle extras) {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        view.setProgressDialog();
        view.hideLoadingDialog();

        if (extras!=null){
            DoctorProfileModel data= new Gson().fromJson(extras.getString("data"), DoctorProfileModel.class);
            view.bindViews(data);
        }
        subscriptions.add(backClicked());
        subscriptions.add(itemClicked());
        subscriptions.add(BookAppointmentSubscription());

    }

    private Disposable backClicked(){
        return view.backClicked().subscribe(aVoid -> model.finish());
    }
    private Disposable itemClicked(){
        return view.itemclicked().subscribe(view::setCurrentItem);
    }
    public void onDestroy() {
        subscriptions.clear();
    }


    //BookAppointment
    private Disposable BookAppointmentSubscription() {
        return view.submitClicked()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .flatMap(isNetworkAvailable -> validateRequest())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(Common -> BookAppointment())
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
                    subscriptions.add(BookAppointmentSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<CommonApiResponse> BookAppointment() {
        return model.performBookAppointment(getRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(BookAppointmentValidations.validateBookAppointmentRequest(getRequest()))
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
    private BookAppointmentModel getRequest() {
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
