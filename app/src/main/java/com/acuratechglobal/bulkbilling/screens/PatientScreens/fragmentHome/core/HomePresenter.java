package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.core;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.response.GetDoctorListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter {

    private final HomeView view;
    private final HomeModel model;
    private final CompositeDisposable subscriptions;
    private final RxSchedulers rxSchedulers;

    private List<SpecializationModel> listLevel1 = new ArrayList<>();
    private List<SpecializationModel> listLevel2 = new ArrayList<>();
    private List<SpecializationModel> listLevel3 = new ArrayList<>();

    public HomePresenter(HomeView view, HomeModel model, CompositeDisposable subscriptions, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.subscriptions = subscriptions;
        this.rxSchedulers = rxSchedulers;
    }

    public void onCreate() {
//        view.setupView(model.getString(R.string.toolbar_add_task));
        subscriptions.add(menuClick());
        subscriptions.add(GetDoctorListSubscription());
        subscriptions.add(respondToItemClick());
        subscriptions.add(filterClick());
        subscriptions.add(shadowClick());
        subscriptions.add(specializationArea());
        subscriptions.add(specializationField());
        subscriptions.add(speciality());
        subscriptions.add(respondToItemClickLevel1());
        subscriptions.add(respondToItemClickLevel2());
        subscriptions.add(respondToItemClickLevel3());
        subscriptions.add(respondToItemClickSelectedLevel3());
        subscriptions.add(doneClicked());

        subscriptions.add(rdBtn03Clicked());
        subscriptions.add(rdBtn05Clicked());
        subscriptions.add(rdBtn10Clicked());
        subscriptions.add(rdBtn15Clicked());
        subscriptions.add(rdBtnAboveClicked());

        subscriptions.add(star1Clicked());
        subscriptions.add(star2Clicked());
        subscriptions.add(star3Clicked());
        subscriptions.add(star4Clicked());
        subscriptions.add(star5Clicked());

        subscriptions.add(applyClicked());
        subscriptions.add(clearClicked());

        subscriptions.add(searchDoctors());
    }

    private Disposable menuClick() {
        return view.menuClick().subscribe(aVoid -> model.openDrawer());
    }

    private Disposable filterClick() {
        return view.filterClick().subscribe(aVoid -> view.showFilter());
    }

    private Disposable shadowClick() {
        return view.shadowClick().mergeWith(view.cancelClick()).subscribe(aVoid -> view.hideFilter());
    }

    //GetDoctorList
    private Disposable GetDoctorListSubscription() {
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
                .flatMap(profileResponse -> getDoctors())
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

    private Observable<GetDoctorListApiResponse> getDoctors() {
        return model.performGetDocList(view.getRequest())
                .observeOn(rxSchedulers.network())
                .subscribeOn(rxSchedulers.io());
    }

    private Disposable respondToItemClick() {
        return view.itemClick().subscribe(view::gotoDocProfile);
    }

    public void onDestroy() {
        subscriptions.clear();
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
                        view.showDialogLevel1(specializationResponse.getList());
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
            view.setTvSpclArea(listLevel1.get(integer));
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
                        view.showDialogLevel2(specializationResponse.getList());
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
            view.setTvSpclField(listLevel2.get(integer));
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
            view.notifyItemChangedlevel3(listLevel3, pos);
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

    //Radio buttons
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

    //Star Toggle
    private Disposable star1Clicked() {
        return view.star1Click().subscribe(aVoid -> view.star1Toggle());
    }

    private Disposable star2Clicked() {
        return view.star2Click().subscribe(aVoid -> view.star2Toggle());
    }

    private Disposable star3Clicked() {
        return view.star3Click().subscribe(aVoid -> view.star3Toggle());
    }

    private Disposable star4Clicked() {
        return view.star4Click().subscribe(aVoid -> view.star4Toggle());
    }

    private Disposable star5Clicked() {
        return view.star5Click().subscribe(aVoid -> view.star5Toggle());
    }

    //Apply Filter
    private Disposable applyClicked() {
        return view.applyFilter().subscribe(aVoid -> {
            view.hideFilter();
            view.setFilterParams();
            subscriptions.add(GetDoctorListSubscription());
        });
    }

    private Disposable clearClicked() {
        return view.clearFilter().subscribe(aVoid -> {
            view.hideFilter();
            view.clearFilterParams();
            subscriptions.add(GetDoctorListSubscription());
        });
    }

    // Search Params
    private Disposable searchDoctors() {
        return view.searchDoctors().subscribe(query -> {
            view.setSearchParams(query);
            subscriptions.add(GetDoctorListSubscription());
        });
    }

}
