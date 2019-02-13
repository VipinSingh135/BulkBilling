package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetAppointmentListApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class PatAppointmentPresenter {

    private final PatAppiontmentView view;
    private final PatAppointmentModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public PatAppointmentPresenter(PatAppiontmentView view, PatAppointmentModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(GetAppointmentListSubscription());
        subscriptions.add(menuClick());
        subscriptions.add(detailClick());
        subscriptions.add(hideDetail());
        subscriptions.add(cancelClick());
        subscriptions.add(AcceptRejectSubscription());
        subscriptions.add(ConfirmSubscription());
    }

    private Disposable detailClick() {
        return view.detailClick().subscribe(pos -> {
            view.setCurrentPos(pos);
            view.showDetails();
        });
    }

    private Disposable menuClick() {
        return view.menuClick().subscribe(aVoid -> model.openDrawer());
    }

    private Disposable hideDetail() {
        return view.shadowClick().mergeWith(view.closeClick()).subscribe(aVoid -> view.hideDetails());
    }

    private Disposable cancelClick() {
        return view.cancelClick().subscribe(aVoid -> {
            view.hideDetails();
            if (view.getDetails().getStatus()!=null && view.getDetails().getStatus()==1){
                model.gotoAddRatingScreen(view.getDetails());
            }
        });
    }

    //GetAppointmentList
    private Disposable GetAppointmentListSubscription() {
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
                .flatMap(profileResponse -> getAppointments())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(profileResponse -> {
                    view.hideLoadingDialog();
                    if (profileResponse.getStatus() == 1) {
//                        UiUtils.showSnackbar(view.getView(), profileResponse.getMessage(), Snackbar.LENGTH_SHORT);
                        view.setAdapterList(profileResponse.getList());

                    } else {
                        String errorMessage =
                                null == profileResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : profileResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                });
    }

    private Observable<GetAppointmentListApiResponse> getAppointments() {
        return model.performGetAppointmentList()
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

    public void onDestroy() {
        subscriptions.clear();
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



    //AcceptRejectApi
    private Disposable ConfirmSubscription() {
        return view.confirmClicked()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(networAvailable -> AcceptRejectAppointment(view.getCurrentPos()))
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
                        view.hideDetails();
                        view.showToast(response.getMessage());
                        view.updateList();
                    } else {
                        String errorMessage =
                                null == response.getMessage() ? model.getString(R.string.error_signUp)
                                        : response.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(ConfirmSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }

    private Disposable AcceptRejectSubscription() {
        return view.confirmClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(this::AcceptRejectAppointment)
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
                        view.showToast(response.getMessage());
                        view.updateList();
                    } else {
                        String errorMessage =
                                null == response.getMessage() ? model.getString(R.string.error_signUp)
                                        : response.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(AcceptRejectSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }

    private Observable<CommonApiResponse> AcceptRejectAppointment(Integer pos) {
        return model.performAcceptRejectTime(getRequest(pos))
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private AcceptRejectApiRequest getRequest(Integer pos) {
        return view.getParams(pos);
    }



}
