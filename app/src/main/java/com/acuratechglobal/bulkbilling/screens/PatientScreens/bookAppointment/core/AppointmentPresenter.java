package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.CreateProfileApiRequest;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.CreateProfileValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;
import static com.acuratechglobal.bulkbilling.utils.ImageUtils.REQUEST_CAMERA;
import static com.acuratechglobal.bulkbilling.utils.ImageUtils.REQUEST_GALLERY;

public class DoctorProfilePresenter {

    private final DoctorProfileView view;
    private final DoctorProfileModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;


    public DoctorProfilePresenter(DoctorProfileView view, DoctorProfileModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
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
            CreateProfileApiRequest data= new Gson().fromJson(extras.getString("data"),CreateProfileApiRequest.class);
            view.bindViews(data);
        }

    }

    private Disposable backClicked(){
        return view.backClicked().subscribe(aVoid -> model.finish());
    }
    public void onDestroy() {
        subscriptions.clear();
    }


}
