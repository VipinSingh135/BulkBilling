package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.gson.Gson;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DoctorProfilePresenter {

    private final DoctorProfileView view;
    private final com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfileModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;
    private DoctorProfileModel data;

    public DoctorProfilePresenter(DoctorProfileView view, com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core.DoctorProfileModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
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
            data= new Gson().fromJson(extras.getString("data"), DoctorProfileModel.class);
            view.bindViews(data);
        }

        subscriptions.add(backClicked());
        subscriptions.add(getAppointmentClicked());
    }

    private Disposable backClicked(){
        return view.backClicked().subscribe(aVoid -> model.finish());
    }

    private Disposable getAppointmentClicked(){
        return view.appointmentClick().subscribe(aVoid -> model.gotoGetAppoinment(data));
    }

    public void onDestroy() {
        subscriptions.clear();
    }


}
