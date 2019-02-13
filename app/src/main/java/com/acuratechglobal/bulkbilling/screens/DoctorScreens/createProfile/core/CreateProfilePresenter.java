package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core;

import android.Manifest;
import android.content.Intent;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.Validations.CreateProfileValidations;
import com.acuratechglobal.bulkbilling.utils.Validations.ValidationResponse;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;
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

public class CreateProfilePresenter {

    private final CreateProfileView view;
    private final CreateProfileModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;
    private final RxPermissions rxPermissions;

    private List<SpecializationModel> listLevel1 = new ArrayList<>();
    private List<SpecializationModel> listLevel2 = new ArrayList<>();
    private List<SpecializationModel> listLevel3 = new ArrayList<>();
//    private List<SpecializationModel> selectedlistLevel3 = new ArrayList<>();

    private List<SpecializationModel> qualificationList = new ArrayList<>();
    private List<SpecializationModel> selectedQualifications = new ArrayList<>();

    public CreateProfilePresenter(CreateProfileView view, CreateProfileModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers, RxPermissions rxPermissions) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
        this.rxPermissions = rxPermissions;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        view.setProgressDialog();
        view.hideLoadingDialog();

        subscriptions.add(GetProfileSubscription());
        subscriptions.add(CreateProfileSubscription());
        subscriptions.add(rdBtn03Clicked());
        subscriptions.add(rdBtn05Clicked());
        subscriptions.add(rdBtn10Clicked());
        subscriptions.add(rdBtn15Clicked());
        subscriptions.add(rdBtnAboveClicked());
        subscriptions.add(backClicked());

        subscriptions.add(specializationArea());
        subscriptions.add(specializationField());
        subscriptions.add(respondToItemClickLevel1());
        subscriptions.add(respondToItemClickLevel2());

        subscriptions.add(speciality());
        subscriptions.add(respondToItemClickLevel3());
        subscriptions.add(doneClicked());
        subscriptions.add(respondToItemClickSelectedLevel3());

        subscriptions.add(Qualification());
        subscriptions.add(done2Clicked());
        subscriptions.add(respondToItemClickQualification());
        subscriptions.add(respondToItemClickSelectedQualification());

        subscriptions.add(addImageClicked());

        subscriptions.add(selectDays());
        subscriptions.add(respondToItemClickDays());
        subscriptions.add(done3Clicked());
        subscriptions.add(respondToItemClickSelectedDays());

        subscriptions.add(openTimeClicked());
        subscriptions.add(closeTimeClicked());
        subscriptions.add(addressClicked());

    }

    //GetProfile
    private Disposable GetProfileSubscription() {
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
                .flatMap(profileResponse -> getProfile())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(profileResponse -> {
                    view.hideLoadingDialog();
                    if ( profileResponse.getStatus()== 1) {
//                        UiUtils.showSnackbar(view.getView(), profileResponse.getMessage(), Snackbar.LENGTH_SHORT);
                        view.bindViews(profileResponse.getObject());
                        model.setDays(profileResponse.getObject().getDoctorAvailability());
                        model.setLevel1Id(profileResponse.getObject().getSelectedLevel1().getUid());
                        model.setLevel2Id(profileResponse.getObject().getSelectedLevel2().getUid());
                        view.setDaysAdapter(model.getSelectedDaysList());
                        selectedQualifications.clear();
                        selectedQualifications.addAll(view.getSelectedQualifications());


                    } else {
                        String errorMessage =
                                null == profileResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : profileResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
//                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<GetProfileApiResponse> getProfile(){
        return model.performGetProfile()
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

    //ButtonClicks
    private Disposable openTimeClicked() {
        return view.openTimeClick().subscribe(aVoid -> {
//            model.showTimePicker();
            subscriptions.add(view.openTimePicker());
        });
    }
    private Disposable closeTimeClicked() {
        return view.closeTimeClick().subscribe(aVoid -> {
//            model.showTimePicker();
            subscriptions.add(view.closeTimePicker());
        });
    }
    private Disposable backClicked() {
        return view.backClicked().subscribe(aVoid -> model.finish());
    }
    private Disposable rdBtn03Clicked() {
        return view.rdBtn03Click().subscribe(aVoid -> view.toggleRdBtns(1));
    }
    private Disposable rdBtn05Clicked() {
        return view.rdBtn05Click().subscribe(aVoid -> view.toggleRdBtns(2));
    }
    private Disposable rdBtn10Clicked() {
        return view.rdBtn10Click().subscribe(aVoid -> view.toggleRdBtns(3));
    }
    private Disposable rdBtn15Clicked() {
        return view.rdBtn15Click().subscribe(aVoid -> view.toggleRdBtns(4));
    }
    private Disposable rdBtnAboveClicked() {
        return view.rdBtnAboveClick().subscribe(aVoid -> view.toggleRdBtns(5));
    }

    //Specialization Level1
    private Disposable specializationArea() {
        return view.spclAreaClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(specialization1 -> Specialization1())
                .subscribe(specializationResponse -> {
                    view.hideLoadingDialog();
                    if (specializationResponse.getStatus() == 1) {
                        view.showDialogLevel1("Select Specialization Area", specializationResponse.getList());
                        listLevel1.clear();
                        listLevel1.addAll(specializationResponse.getList());
                    } else {
                        String errorMessage =
                                null == specializationResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : specializationResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(specializationArea());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<SpecializationResponse> Specialization1() {
        return model.callLevel1Api()
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Observable<Boolean> validateLevel1() {
        return model.isLevel1Selected()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.select_level1),
                                Snackbar.LENGTH_SHORT);
                    }
                })
                .filter(networkAvailable -> networkAvailable);
    }
    private Disposable respondToItemClickLevel1() {
        return view.itemClicksLevel1().subscribe(integer -> {
            model.setLevel1Id(listLevel1.get(integer).getUid());
            view.setTvSpclArea(listLevel1.get(integer).getName());
        });
    }

    //Specialization Level2
    private Disposable specializationField() {
        return view.spclFieldClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateLevel1())
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(specialization1 -> Specialization2())
                .subscribe(specializationResponse -> {
                    view.hideLoadingDialog();
                    if (specializationResponse.getStatus() == 1) {
                        view.showDialogLevel2("Select Specialization Field", specializationResponse.getList());
                        listLevel2.clear();
                        listLevel2.addAll(specializationResponse.getList());
                    } else {
                        String errorMessage =
                                null == specializationResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : specializationResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(specializationField());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<SpecializationResponse> Specialization2() {
        return model.callLevel2Api(model.getLevel1Id())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Observable<Boolean> validateLevel2() {
        return model.isLevel2Selected()
                .doOnNext(isSelected -> {
                    if (!isSelected) {
                        UiUtils.showSnackbar(view.getView(), model.getString(R.string.select_level2),
                                Snackbar.LENGTH_SHORT);
                    }
                })
                .filter(networkAvailable -> networkAvailable);
    }
    private Disposable respondToItemClickLevel2() {
        return view.itemClicksLevel2().subscribe(integer -> {
            model.setLevel2Id(listLevel2.get(integer).getUid());
            view.setTvSpclField(listLevel2.get(integer).getName());
        });
    }

    //Specialization Level3
    private Disposable speciality() {
        return view.specialityClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateLevel2())
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(specialization3 -> Specialization3())
                .subscribe(specializationResponse -> {
                    view.hideLoadingDialog();
                    if (specializationResponse.getStatus() == 1) {
                        view.showDialogLevel3("Select Specialization Field", specializationResponse.getList());
                        listLevel3.clear();
                        listLevel3.addAll(specializationResponse.getList());
                    } else {
                        String errorMessage =
                                null == specializationResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : specializationResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(speciality());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<SpecializationResponse> Specialization3() {
        return model.callLevel3Api(model.getLevel2Id())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Disposable respondToItemClickLevel3() {
        return view.itemClicksLevel3().subscribe(pos -> {

//            if (selectedlistLevel3.contains(listLevel3.get(pos))) {
//                selectedlistLevel3.remove(listLevel3.get(pos));
//            } else {
//                selectedlistLevel3.add(listLevel3.get(pos));
//            }
            view.notifyItemChangedlevel3(listLevel3,pos);
//            model.setLevel2Id(listLevel2.get(integer).getUid());
//            view.setTvSpclField(listLevel2.get(integer).getName());
        });
    }
    private Disposable respondToItemClickSelectedLevel3() {
        return view.itemClickslevel3Selected().throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateLevel2())
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(specialization3 -> Specialization3())
                .subscribe(specializationResponse -> {
                    view.hideLoadingDialog();
                    if (specializationResponse.getStatus() == 1) {
                        view.showDialogLevel3("Select Specialization Field", specializationResponse.getList());
                        listLevel3.clear();
                        listLevel3.addAll(specializationResponse.getList());
                    } else {
                        String errorMessage =
                                null == specializationResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : specializationResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Disposable doneClicked() {
        return view.doneClick().subscribe(aVoid -> view.setSpecialityAdapter());
    }

    //Qualifications
    private Disposable Qualification() {
        return view.qualificationsClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(qualifications -> QualificationApi())
                .subscribe(qualificationResponse -> {
                    view.hideLoadingDialog();
                    if (qualificationResponse.getStatus() == 1) {
                        view.showDialogQualifications("Select Qualifications", qualificationResponse.getList(), selectedQualifications);
                        qualificationList.clear();
                        qualificationList.addAll(qualificationResponse.getList());
                    } else {
                        String errorMessage =
                                null == qualificationResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : qualificationResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(Qualification());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<SpecializationResponse> QualificationApi() {
        return model.callQualificationApi()
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Disposable respondToItemClickQualification() {
        return view.itemClicksQualification().subscribe(pos -> {
            boolean isSelected=false;
            for (SpecializationModel obj:selectedQualifications) {
                if (obj.getId()==qualificationList.get(pos).getId()){
                    isSelected=true;
                    selectedQualifications.remove(obj);
                    break;
                }
            }
            if (!isSelected) {
//                selectedQualifications.remove(qualificationList.get(pos));
                selectedQualifications.add(qualificationList.get(pos));
            }
            view.notifyItemChangedQualifications(selectedQualifications);
//            model.setLevel2Id(listLevel2.get(integer).getUid());
//            view.setTvSpclField(listLevel2.get(integer).getName());
        });
    }
    private Disposable respondToItemClickSelectedQualification() {
        return view.itemClicksQualificationSelected().throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(qualifications -> QualificationApi())
                .subscribe(qualificationResponse -> {
                    view.hideLoadingDialog();
                    if (qualificationResponse.getStatus() == 1) {
                        view.showDialogQualifications("Select Qualifications", qualificationResponse.getList(), selectedQualifications);
                        qualificationList.clear();
                        qualificationList.addAll(qualificationResponse.getList());
                    } else {
                        String errorMessage =
                                null == qualificationResponse.getMessage() ? model.getString(R.string.error_signUp)
                                        : qualificationResponse.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Disposable done2Clicked() {
        return view.done2Click().subscribe(aVoid -> view.setQualificationAdapter(selectedQualifications));

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

    //SelectDays
    private Disposable selectDays() {
        return view.selectDaysClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .subscribe(aVoid -> {
                    view.showDialogDays("Select days", model.getDaysList(),model.getSelectedDaysList());
                });
    }
    private Disposable respondToItemClickDays() {
        return view.itemClicksDays().subscribe(pos -> {
            if (model.getSelectedDaysList().contains(model.getDaysList().get(pos))) {
                model.getSelectedDaysList().remove(model.getDaysList().get(pos));
            } else {
                model.getSelectedDaysList().add(model.getDaysList().get(pos));
            }
            view.notifyItemChangedDays(model.getSelectedDaysList());
        });
    }
    private Disposable done3Clicked() {
        return view.done3Click().subscribe(aVoid -> view.setDaysAdapter(model.getSelectedDaysList()));
    }
    private Disposable respondToItemClickSelectedDays() {
        return view.itemClicksDaysSelected().subscribe(pos -> {
            view.showDialogDays("Select days", model.getDaysList(), model.getSelectedDaysList());
        });
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

//    public void onPause() {
//        view.setProgressDialog();
//    }

    public void onResume() {
        view.setProgressDialog();
        view.hideLoadingDialog();
    }

    //CreateProfile
    private Disposable CreateProfileSubscription() {
        return view.nextClick()
                .throttleFirst(1, TimeUnit.SECONDS)// maybe you want to ignore multiple clicks
                .flatMap(isNetworkAvailable -> validateNetwork())
                .flatMap(isNetworkAvailable -> validateRequest())
                .doOnNext(showDialog -> view.showLoadingDialog(model.getString(R.string.loading_add_task)))
                .flatMap(LoginApiResponse -> CreateProfile())
                .subscribe(response -> {
                    view.hideLoadingDialog();
                    if (response.getStatus() == 1) {
//                        AppController.setUserName(response.getUserData().getFirstName()+" "+ response.getUserData().getLastName());
//                        AppController.setUserEmail(response.getUserData().getEmail());
//                        AppController.setUserImage(response.getUserData().getsPhotoPath());

//                        UiUtils.showSnackbar(view.getView(),SignUpResponse.getMessage() , Snackbar.LENGTH_SHORT);
                        model.gotoHomeScreen(response.getUserData(),response.getMessage());
//                        AppController.setUserID(SignUpResponse.getSUID());
//                        model.finish();
                    } else {
                        String errorMessage =
                                null == response.getMessage() ? model.getString(R.string.error_signUp)
                                        : response.getMessage();
                        UiUtils.showSnackbar(view.getView(), errorMessage, Snackbar.LENGTH_SHORT);
                    }
                }, throwable -> {
                    subscriptions.add(CreateProfileSubscription());
                    view.hideLoadingDialog();
                    UiUtils.handleThrowable(throwable);
                    UiUtils.showSnackbar(view.getView(), model.getString(R.string.error_signUp), Snackbar.LENGTH_SHORT);
                });
    }
    private Observable<LoginApiResponse> CreateProfile() {
        return model.performCreateProfile(getCreateProfileRequest())
                .subscribeOn(rxSchedulers.network())
                .observeOn(rxSchedulers.androidUI());
    }
    private Observable<ValidationResponse> validateRequest() {
        return Observable.just(CreateProfileValidations.validateCreateProfileRequest(getCreateProfileRequest()))
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
    private DoctorProfileModel getCreateProfileRequest() {
        return view.getParams();
    }


}
