package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.GetPatientRatingApiResponse;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class PatRatingPresenter {

    private final PatRatingView view;
    private final PatRatingModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;

    private List<SpecializationModel> listLevel1 = new ArrayList<>();
    private List<SpecializationModel> listLevel2 = new ArrayList<>();
    private List<SpecializationModel> listLevel3 = new ArrayList<>();

    public PatRatingPresenter(PatRatingView view, PatRatingModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(menuClick());
        subscriptions.add(GetRatingsListSubscription());

    }

    private Disposable menuClick() {
        return view.menuClick().subscribe(aVoid -> model.openDrawer());
    }

    //GetRatingsList
    private Disposable GetRatingsListSubscription() {
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
                .flatMap(profileResponse -> getRatings())
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

    private Observable<GetPatientRatingApiResponse> getRatings() {
        return model.performGetRatingList(view.getRequest())
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }


    public void onDestroy() {
        subscriptions.clear();
    }



}
