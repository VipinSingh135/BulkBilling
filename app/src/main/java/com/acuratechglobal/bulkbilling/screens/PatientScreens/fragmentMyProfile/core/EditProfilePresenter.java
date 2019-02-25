package com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetPatientProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.models.PatientProfileModel;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.CreateProfileValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;
import static com.acuratechglobal.bulkbilling.utils.ImageUtils.REQUEST_CAMERA;
import static com.acuratechglobal.bulkbilling.utils.ImageUtils.REQUEST_GALLERY;

public class EditProfilePresenter {

    private final EditProfileView view;
    private final EditProfileModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;
    private final RxPermissions rxPermissions;
    
    public EditProfilePresenter(EditProfileView view, EditProfileModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers, RxPermissions rxPermissions) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
        this.rxPermissions = rxPermissions;
    }

    public void onCreate(Bundle extras) {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        view.setProgressDialog();
        view.hideLoadingDialog();
        if (extras!=null){
            view.bindViews(extras);
        }
//        subscriptions.add(GetProfileSubscription());
        subscriptions.add(EditProfileSubscription());

        subscriptions.add(backClicked());

        subscriptions.add(addImageClicked());

        subscriptions.add(addressClicked());

    }

    //GetProfile
//    private Disposable GetProfileSubscription() {
//        return model.networkAvailable()
//                .subscribeOn(rxSchedulers.network())
//                .observeOn(rxSchedulers.androidUI())
//                .doOnNext(networkAvailable -> {
//                    if (!networkAvailable) {
//                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_no_internet),
//                                Snackbar.LENGTH_SHORT);
//                    }
//                })
//                .filter(networkAvailable -> networkAvailable)
//                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
//                .flatMap(profileResponse -> getProfile())
//                .subscribeOn(rxSchedulers.network())
//                .observeOn(rxSchedulers.androidUI())
//                .subscribe(profileResponse -> {
//                    view.hideLoadingDialog();
//                    if ( profileResponse.getStatus()== 1) {
////                        UiUtils.showSnackbar(view.getView(), profileResponse.getMessage(), Snackbar.LENGTH_SHORT);
//                        view.bindViews(profileResponse.getObject());
//
//
//                    } else {
//                        String errorMessage =
//                                null == profileResponse.getMessage() ? model.getString(R.string.error_signUp)
//                                        : profileResponse.getMessage();
//                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
//                    }
//                }, throwable -> {
//                    view.hideLoadingDialog();
//                    UiUtils.handleThrowable(throwable);
////                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
//                });
//    }
//    private Observable<GetPatientProfileApiResponse> getProfile(){
//        return model.performGetProfile()
//                .observeOn(rxSchedulers.network())
//                .subscribeOn(rxSchedulers.io());
//    }

    //ButtonClicks

    private Disposable backClicked() {
        return view.backClicked().subscribe(aVoid -> model.finish());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    //Image
    private Disposable addImageClicked() {
        return view.addImageClick()
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .subscribe(granted -> {
                    if (granted) {
                        model.selectImage();
                    }
                });
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        view.setProgressDialog();
        view.hideLoadingDialog();
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                model.cropCameraImage();
            } else if (requestCode == REQUEST_GALLERY) {
                if (data != null) {
                    model.cropGalleryImage(data.getData());
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
//                Uri resultUri= data.getParcelableExtra(EXTRA_OUTPUT_URI);

                view.setProfileImage(data,model.getProfileImage());
            }else if (requestCode == 6){
                view.setClinicAddresss(data);
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
//            throw cropError;
        }
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

    //Address
    private Disposable addressClicked(){
        return view.clinicAddressClicked()
                .throttleFirst(3,TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                   view.hideLoadingDialog();
                   model.showGooglePlaces();});

    }

    public void onResume() {
        view.setProgressDialog();
        view.hideLoadingDialog();
    }

    //EditProfile
    private Disposable EditProfileSubscription() {
        return view.nextClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .flatMap(isNetworkAvailable -> validateRequest())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(LoginApiResponse -> EditProfile())
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
                        model.gotoHomeScreen(response.getMessage());
                    
                    } else {
                        String errorMessage =
                                null == response.getMessage() ? model.getString(R.string.error_signUp)
                                        : response.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(EditProfileSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<CommonApiResponse> EditProfile() {
        return model.performEditProfile(getEditProfileRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(CreateProfileValidations.validatePatientProfileRequest(getEditProfileRequest()))
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
    private PatientProfileModel getEditProfileRequest() {
        return view.getParams();
    }


}
