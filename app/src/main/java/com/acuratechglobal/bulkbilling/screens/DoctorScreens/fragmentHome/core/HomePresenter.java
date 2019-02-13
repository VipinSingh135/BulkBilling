package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetAppointmentListApiResponse;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.BookAppointmentValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;
import com.salah.rxdatetimepicker.RxDateConverters;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter {

    private final HomeView view;
    private final HomeModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public HomePresenter(HomeView view, HomeModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(GetAppointmentListSubscription());
        subscriptions.add(menuClick());
        subscriptions.add(acceptRequest());
        subscriptions.add(rejectRequest());
        subscriptions.add(detailClick());
        subscriptions.add(hideDetail());
        subscriptions.add(SuggestClicked());
        subscriptions.add(AcceptRejectSubscription());
    }

    private Disposable acceptRequest() {
        return view.acceptClick().subscribe(pos -> {
            view.setCurrentPos(pos);
            view.showAlertDialog(true);
        });
    }

    private Disposable rejectRequest() {
        return view.rejectClick().subscribe(pos -> {
            view.setCurrentPos(pos);
            view.showAlertDialog(false);
        });
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
    private Disposable AcceptRejectSubscription() {
        return view.yesClicked()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(this::AcceptRejectAppointment)
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
                        view.showToast(response.getMessage());
                        view.removeFromList(view.getCurrentPos());
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

    private Observable<CommonApiResponse> AcceptRejectAppointment(Integer status) {
        return model.performAcceptRejectAppointment(getRequest(status))
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }

    private AcceptRejectApiRequest getRequest(Integer status) {
        return view.getParams(status, view.getCurrentPos());
    }


    //Suggestsubscription
    private Disposable SuggestClicked() {
        return view.suggestClick().subscribe(pos -> {
//            model.showTimePicker();
            view.setCurrentPos(pos);
            subscriptions.add(SelectDateSubscription());
        });
    }

    private Disposable SelectDateSubscription() {
        return view.DateTimePicker()
                .show()
                .flatMap(date -> RxDateConverters.toString(date, "yyyy-MM-dd'T'HH:mm:ss"))
                .subscribe(time -> {
                    view.setSuggestedDate(time);
                    subscriptions.add(SuggestSubscription());
                });
    }

    private Disposable SuggestSubscription() {
        return validateNetwork()
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(isNetworkAvailable -> AcceptRejectAppointment(3))
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
                        view.showToast(response.getMessage());
                        view.removeFromList(view.getCurrentPos());
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

}
