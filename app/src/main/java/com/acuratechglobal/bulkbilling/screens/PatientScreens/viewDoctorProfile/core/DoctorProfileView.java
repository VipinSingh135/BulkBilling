package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.CreateProfileApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.models.DoctorSpecializationModel;
import com.acuratechglobal.bulkbilling.models.QualificationModel;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.MultiSelectAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.MultiSelectDaysAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.OptionsAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.SelectedDaysAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.SelectedItemAdapter;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.jakewharton.rxbinding3.view.RxView;
import com.salah.rxdatetimepicker.RxDateConverters;
import com.salah.rxdatetimepicker.RxDateTimePicker;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.playFailureAnimation;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.CONTACT_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.FIRSTNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.FIRSTNAME_INVALID;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.USERNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.USERNAME_INAVALID;

public class CreateProfileView {

    private View view;
    private OptionsAdapter optionAdapter, optionAdapter2;

    private EditText edClinicName;
    private TextView tvClinicAddress,tvHeaderTitle, tvQualifications;
    private TextView tvSelectDays, tvOpenTime, tvCloseTime;
    private TextView tvName, tvPhone, tvEmail;
    private TextView tvSpclArea, tvSpclField, tvSpecialty;
    private RecyclerView recyclerSpeciality, recyclerQualify;
    private ImageView imgProfile;
    private Button btnSave;
    private ImageButton btnBack, btnAddImage;
    private RadioButton rdBtnAbove, rdBtn03, rdBtn05, rdBtn10, rdBtn15;
    private final CreateProfileActivity activity;
    private ProgressDialog progressDialog;

    Dialog dialog, dialogMultiSelect, dialogMultiSelect2, dialogMultiSelect3;
    private TextView tvTitle, tvTitle2, tvTitle3;
    private Button btnDone, btnDone2, btnDone3;
    private RecyclerView recyclerMultiselect, recyclerMultiselect2, recyclerMultiselect3, recyclerSelectDays;
    private MultiSelectAdapter multiSelectAdapter, multiSelectAdapter2;
    private MultiSelectDaysAdapter multiSelectAdapter3;

    private SelectedItemAdapter adapterLevel3, adapterQualifications;
    private SelectedDaysAdapter adapterSelectedDays;

    int experience = 0;

    CreateProfileApiRequest profileData = new CreateProfileApiRequest();

    Double lat = 0.0;
    Double lon = 0.0;

    private File profileImage = null;
    private List<SpecializationModel> selectedlistLevel3 = new ArrayList<>();
    private List<SpecializationModel> selectedQualifications = new ArrayList<>();
    private List<String> selectedDays = new ArrayList<>();
    UserData data;

    public CreateProfileView(CreateProfileActivity context, SharedPrefsUtil prefs) {
        this.activity = context;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_activity_create_profile, parent, true);

        tvSpclArea = view.findViewById(R.id.tvSpclArea);
        tvSpclField = view.findViewById(R.id.tvSpclField);
        tvSpecialty = view.findViewById(R.id.tvSpecialty);

        recyclerSpeciality = view.findViewById(R.id.recyclerSpeciality);
        recyclerQualify = view.findViewById(R.id.recyclerQualify);
        recyclerSelectDays = view.findViewById(R.id.recyclerSelectDays);

        edClinicName = view.findViewById(R.id.edClinicName);

        tvClinicAddress = view.findViewById(R.id.tvClinicAddress);
        tvQualifications = view.findViewById(R.id.tvQualifications);

        tvSelectDays = view.findViewById(R.id.tvSelectDays);
        tvOpenTime = view.findViewById(R.id.tvOpenTime);
        tvCloseTime = view.findViewById(R.id.tvCloseTime);

        tvName = view.findViewById(R.id.tvName);
        tvHeaderTitle = view.findViewById(R.id.tvTitle);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);

        imgProfile = view.findViewById(R.id.imgProfile);

        btnSave = view.findViewById(R.id.btnSave);
        btnBack = view.findViewById(R.id.btnBack);
        btnAddImage = view.findViewById(R.id.btnAddImage);

        rdBtnAbove = view.findViewById(R.id.rdBtnAbove);
        rdBtn03 = view.findViewById(R.id.rdBtn03);
        rdBtn05 = view.findViewById(R.id.rdBtn05);
        rdBtn10 = view.findViewById(R.id.rdBtn10);
        rdBtn15 = view.findViewById(R.id.rdBtn15);

        optionAdapter = new OptionsAdapter();
        optionAdapter2 = new OptionsAdapter();

        dialogMultiSelect = createMultiSelectDialog();
        btnDone = dialogMultiSelect.findViewById(R.id.btnDone);
        tvTitle = dialogMultiSelect.findViewById(R.id.tvTitle);
        recyclerMultiselect = dialogMultiSelect.findViewById(R.id.recyclerView);
        multiSelectAdapter = new MultiSelectAdapter();

        dialogMultiSelect2 = createMultiSelectDialog();
        btnDone2 = dialogMultiSelect2.findViewById(R.id.btnDone);
        tvTitle2 = dialogMultiSelect2.findViewById(R.id.tvTitle);
        recyclerMultiselect2 = dialogMultiSelect2.findViewById(R.id.recyclerView);
        multiSelectAdapter2 = new MultiSelectAdapter();

        dialogMultiSelect3 = createMultiSelectDialog();
        btnDone3 = dialogMultiSelect3.findViewById(R.id.btnDone);
        tvTitle3 = dialogMultiSelect3.findViewById(R.id.tvTitle);
        recyclerMultiselect3 = dialogMultiSelect3.findViewById(R.id.recyclerView);
        multiSelectAdapter3 = new MultiSelectDaysAdapter();

        adapterLevel3 = new SelectedItemAdapter();
        adapterQualifications = new SelectedItemAdapter();
        recyclerSpeciality.setAdapter(adapterLevel3);
//        recyclerSpeciality.setLayoutManager(new GridLayoutManager(activity,2,RecyclerView.VERTICAL,false));
        recyclerSpeciality.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        recyclerQualify.setAdapter(adapterQualifications);
        recyclerQualify.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
//        recyclerQualify.setLayoutManager(new GridLayoutManager(activity,3,RecyclerView.VERTICAL,false));

        adapterSelectedDays = new SelectedDaysAdapter();
        recyclerSelectDays.setAdapter(adapterSelectedDays);
        recyclerSelectDays.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));

//        setProgressDialog();
    }

    void bindViews(CreateProfileApiRequest data){
        this.profileData = data;
        tvName.setText(profileData.getFirstName() + " " + profileData.getLastName());
        tvEmail.setText(profileData.getEmail());
        if (profileData.getPhone()!=null)
            tvPhone.setText(profileData.getPhone());
        if (profileData.getCloseTime()!=null)
            tvCloseTime.setText(profileData.getCloseTime());
        if (profileData.getOpenTime()!=null)
            tvOpenTime.setText(profileData.getOpenTime());
        if (profileData.getClinicName()!=null)
            edClinicName.setText(profileData.getClinicName());
        if (profileData.getClinicAddress()!=null)
            tvClinicAddress.setText(profileData.getClinicAddress());
        if (profileData.getClinicAddressLat()!=null && profileData.getClinicAddressLat().length()>0)
            lat= Double.parseDouble(profileData.getClinicAddressLat());
        if (profileData.getClinicAddressLong()!=null && profileData.getClinicAddressLong().length()>0)
            lon= Double.parseDouble(profileData.getClinicAddressLong());
        if (profileData.getDoctorSpecialization()!=null && profileData.getDoctorSpecialization().size()>0){
            for (DoctorSpecializationModel model: profileData.getDoctorSpecialization()) {
                SpecializationModel obj= new SpecializationModel();
                obj.setName(model.getName());
                obj.setId(model.getId());
                obj.setUid(model.getUid());
                selectedlistLevel3.add(obj);
            }
            setSpecialityAdapter();
        }
        if (profileData.getQualifications()!=null && profileData.getQualifications().size()>0){
            List<SpecializationModel> selectedQualifications = new ArrayList<>();
            for (QualificationModel model: profileData.getQualifications()) {
                SpecializationModel obj= new SpecializationModel();
                obj.setName(model.getName());
                obj.setId(model.getId());
                obj.setUid(model.getUid());
                selectedQualifications.add(obj);
            }
            setQualificationAdapter(selectedQualifications);
        }
        if (profileData.getSelectedLevel1()!=null)
            tvSpclArea.setText(profileData.getSelectedLevel1().getName());
        if (profileData.getSelectedLevel2()!=null)
            tvSpclField.setText(profileData.getSelectedLevel2().getName());

        if (profileData.getsPhotoPath()!=null){
            Glide.with(activity).load(Uri.parse(profileData.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
//            AppController.setUserImage(profileData.getsPhotoPath());
        }
        if (profileData.getExperience()!=null)
            toggleRdBtns(profileData.getExperience());
    }

    void setProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
    }

    public View getView() {
        return this.view;
    }

//    public void setData(UserData data) {
//        tvName.setText(profileData.getFirstName() + " " + profileData.getLastName());
//        tvEmail.setText(profileData.getEmail());
//        tvPhone.setText(profileData.getPhone());
//    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    Observable<Unit> nextClick() {
        return RxView.clicks(btnSave);
    }

    Observable<Unit> doneClick() {
        return RxView.clicks(btnDone);
    }

    Observable<Unit> done2Click() {
        return RxView.clicks(btnDone2);
    }

    Observable<Unit> done3Click() {
        return RxView.clicks(btnDone3);
    }

    Observable<Unit> addImageClick() {
        return RxView.clicks(btnAddImage);
    }

    Observable<Unit> rdBtnAboveClick() {
        return RxView.clicks(rdBtnAbove);
    }

    Observable<Unit> rdBtn03Click() {
        return RxView.clicks(rdBtn03);
    }

    Observable<Unit> rdBtn05Click() {
        return RxView.clicks(rdBtn05);
    }

    Observable<Unit> rdBtn10Click() {
        return RxView.clicks(rdBtn10);
    }

    Observable<Unit> rdBtn15Click() {
        return RxView.clicks(rdBtn15);
    }

    void toggleRdBtns(int pos) {
        switch (pos) {
            case 1:
                rdBtn03.setChecked(true);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(false);
                experience = 1;
                break;
            case 2:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(true);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(false);
                experience = 2;
                break;
            case 3:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(true);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(false);
                experience = 3;
                break;
            case 4:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(true);
                rdBtnAbove.setChecked(false);
                experience = 4;
                break;
            case 5:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(true);
                experience = 5;
                break;
        }

    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }

    Observable<Unit> spclAreaClick() {
        return RxView.clicks(tvSpclArea);
    }

    Observable<Unit> spclFieldClick() {
        return RxView.clicks(tvSpclField);
    }

    Observable<Unit> specialityClick() {
        return RxView.clicks(tvSpecialty);
    }

    Observable<Unit> openTimeClick() {
        return RxView.clicks(tvOpenTime);
    }

    Observable<Unit> closeTimeClick() {
        return RxView.clicks(tvCloseTime);
    }

    Observable<Unit> selectDaysClick() {
        return RxView.clicks(tvSelectDays);
    }

    Observable<Unit> qualificationsClick() {
        return RxView.clicks(tvQualifications);
    }

    void showDialogLevel1(String title, List<SpecializationModel> strList) {
        dialog = createDialog();
        TextView tvTitle = dialog.findViewById(R.id.tVDialogTitle);
        RecyclerView listView;
        optionAdapter.setAdapter(strList);
        listView = dialog.findViewById(R.id.listView);
        listView.setAdapter(optionAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        listView.setLayoutManager(mLayoutManager);
        tvTitle.setText(title);
        dialog.show();
    }

    void showDialogLevel2(String title, List<SpecializationModel> strList) {
        dialog = createDialog();
        TextView tvTitle = dialog.findViewById(R.id.tVDialogTitle);
        RecyclerView listView;
        optionAdapter2.setAdapter(strList);
        listView = dialog.findViewById(R.id.listView);
        listView.setAdapter(optionAdapter2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        listView.setLayoutManager(mLayoutManager);
        tvTitle.setText(title);
        dialog.show();
    }

    void setTvSpclArea(String name) {
        if (!name.equals(getInputText(tvSpclArea))){
            tvSpclField.setText(null);
            tvSpecialty.setVisibility(View.VISIBLE);
            recyclerSpeciality.setVisibility(View.GONE);
            selectedlistLevel3.clear();
        }
        tvSpclArea.setText(name);
        dialog.dismiss();
    }

    void setTvSpclField(String name) {
        if (!name.equals(getInputText(tvSpclField))){
            tvSpecialty.setVisibility(View.VISIBLE);
            recyclerSpeciality.setVisibility(View.GONE);
            selectedlistLevel3.clear();
        }
        tvSpclField.setText(name);
        dialog.dismiss();
    }

    public Observable<Unit> clinicAddressClicked() {
        return RxView.clicks(tvClinicAddress);
    }

    void handleErrorCode(Integer errorCode) {
        if (null == errorCode) {
            return;
        }

        switch (errorCode) {
            case FIRSTNAME_EMPTY:
//                playFailureAnimation(edFirstName);
                break;

            case FIRSTNAME_INVALID:
//                playFailureAnimation(edFirstName);
                break;

            case USERNAME_EMPTY:
                //  playFailureAnimation(edEmail);
                break;
            case USERNAME_INAVALID:
//                playFailureAnimation(edEmail);
//                playFailureAnimation(edPassword);
                break;
            case CONTACT_EMPTY:
                playFailureAnimation(tvClinicAddress);
//                playFailureAnimation(edPassword);
                break;

            default:
                //no-op
                break;
        }
    }

    private Dialog createDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_options);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    public Observable<Integer> itemClicksLevel1() {
        return optionAdapter.observeClicks();
    }

    public Observable<Integer> itemClicksLevel2() {
        return optionAdapter2.observeClicks();
    }

    private Dialog createMultiSelectDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_multiselect);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    void showDialogLevel3(String title, List<SpecializationModel> list) {

        multiSelectAdapter.setAdapterItems(list, selectedlistLevel3);
        recyclerMultiselect.setAdapter(multiSelectAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerMultiselect.setLayoutManager(mLayoutManager);
        tvTitle.setText(title);
        dialogMultiSelect.show();
    }

    public Observable<Integer> itemClicksLevel3() {
        return multiSelectAdapter.observeClicks();
    }

    public void notifyItemChangedlevel3(List<SpecializationModel> list, Integer pos) {
        boolean isSelected=false;
        for (SpecializationModel obj:selectedlistLevel3) {
            if (obj.getId()==list.get(pos).getId()){
                isSelected=true;
                selectedlistLevel3.remove(obj);
                break;
            }
        }
        if (!isSelected) {
            selectedlistLevel3.add(list.get(pos));
        }

        multiSelectAdapter.notifyAdapter(selectedlistLevel3);
    }

    public void showDialogQualifications(String title, List<SpecializationModel> list, List<SpecializationModel> selectedlist) {
        multiSelectAdapter2.setAdapterItems(list, selectedlist);
        recyclerMultiselect2.setAdapter(multiSelectAdapter2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerMultiselect2.setLayoutManager(mLayoutManager);
        tvTitle2.setText(title);
        dialogMultiSelect2.show();
    }

    public Observable<Integer> itemClicksQualification() {
        return multiSelectAdapter2.observeClicks();
    }

    public void notifyItemChangedQualifications(List<SpecializationModel> list) {
        multiSelectAdapter2.notifyAdapter(list);
    }

    public void setSpecialityAdapter() {
        dialogMultiSelect.dismiss();
        if (selectedlistLevel3 == null || selectedlistLevel3.size() == 0) {
            tvSpecialty.setVisibility(View.VISIBLE);
            recyclerSpeciality.setVisibility(View.GONE);
        } else {
            tvSpecialty.setVisibility(View.GONE);
            recyclerSpeciality.setVisibility(View.VISIBLE);
        }
        adapterLevel3.setAdapterList(selectedlistLevel3);
//        selectedlistLevel3.clear();
//        selectedlistLevel3.addAll(selectedlistLevel3);
    }

    public void setQualificationAdapter(List<SpecializationModel> list) {
        dialogMultiSelect2.dismiss();
        if (list == null || list.size() == 0) {
            tvQualifications.setVisibility(View.VISIBLE);
            recyclerQualify.setVisibility(View.GONE);
        } else {
            tvQualifications.setVisibility(View.GONE);
            recyclerQualify.setVisibility(View.VISIBLE);
        }
        adapterQualifications.setAdapterList(list);
        selectedQualifications.clear();
        selectedQualifications.addAll(list);
    }

    public Observable<Integer> itemClicksQualificationSelected() {
        return adapterQualifications.observeClicks();
    }

    public Observable<Integer> itemClickslevel3Selected() {
        return adapterLevel3.observeClicks();
    }

    public void setProfileImage(Intent data, File profileImage) {
        this.profileImage = profileImage;
        final Uri resultUri = UCrop.getOutput(data);
        Glide.with(activity).load(resultUri).apply(RequestOptions.circleCropTransform()).into(imgProfile);
    }

    void showDialogDays(String title, List<String> list, List<String> selectedList) {

        multiSelectAdapter3.setAdapterItems(list, selectedList);
        recyclerMultiselect3.setAdapter(multiSelectAdapter3);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerMultiselect3.setLayoutManager(mLayoutManager);
        tvTitle3.setText(title);
        dialogMultiSelect3.show();
    }

    public Observable<Integer> itemClicksDays() {
        return multiSelectAdapter3.observeClicks();
    }

    public void notifyItemChangedDays(List<String> list) {
        multiSelectAdapter3.notifyAdapter(list);
    }

    public void setDaysAdapter(List<String> list) {
        dialogMultiSelect3.dismiss();
        if (list == null || list.size() == 0) {
            tvSelectDays.setVisibility(View.VISIBLE);
            recyclerSelectDays.setVisibility(View.GONE);
        } else {
            tvSelectDays.setVisibility(View.GONE);
            recyclerSelectDays.setVisibility(View.VISIBLE);
        }
        adapterSelectedDays.setAdapterList(list);
        selectedDays.clear();
        selectedDays.addAll(list);
    }

    public Observable<Integer> itemClicksDaysSelected() {
        return adapterSelectedDays.observeClicks();
    }

    public void setClinicAddresss(Intent data) {

        Place place = PlacePicker.getPlace(data, activity);
        lat = place.getLatLng().latitude;
        lon = place.getLatLng().longitude;
        tvClinicAddress.setText(place.getName());
//        tvClinicAddress.setError(null);
    }

    Disposable openTimePicker() {
        return RxDateTimePicker
                .with(activity)
                .pickTimeOnly()
                .show()
                .flatMap(date -> RxDateConverters.toString(date, "hh:mm a"))
                .subscribe(time ->
                        tvOpenTime.setText(time.toString())
                );
    }

    Disposable closeTimePicker() {
        return RxDateTimePicker
                .with(activity)
                .pickTimeOnly()
                .show()
                .flatMap(date -> RxDateConverters.toString(date, "hh:mm a"))
                .subscribe(time ->
                        tvCloseTime.setText(time.toString())
                );
    }

    CreateProfileApiRequest getParams() {
        profileData.setDocUID(profileData.getDocUID());
        profileData.setAddress(getInputText(tvClinicAddress));
        profileData.setClinicAddress(getInputText(tvClinicAddress));
        profileData.setClinicAddressLat(lat.toString());
        profileData.setClinicAddressLong(lon.toString());
        profileData.setClinicName(getInputText(edClinicName));
        profileData.setCloseTime(getInputText(tvCloseTime));
        profileData.setOpenTime(getInputText(tvOpenTime));
        profileData.setEmail(getInputText(tvEmail));
        profileData.setFirstName(profileData.getFirstName());
        profileData.setLastName(profileData.getLastName());
        profileData.setUserUniqueId(profileData.getUserUniqueId());
        profileData.setPhone(profileData.getPhone());
        profileData.setUserId(profileData.getUserId());
        profileData.setUserType(profileData.getUserType());
        profileData.setExperience(experience);

        List<DoctorSpecializationModel> modelList = new ArrayList<>();
        for (SpecializationModel model : selectedlistLevel3) {
            DoctorSpecializationModel specializationModel = new DoctorSpecializationModel();
            specializationModel.setId(model.getId());
            specializationModel.setUid(model.getUid());
            specializationModel.setName(model.getName());

            modelList.add(specializationModel);
        }

        List<QualificationModel> qualificationModelList = new ArrayList<>();
        for (SpecializationModel model : selectedQualifications) {
            QualificationModel qualificationModel = new QualificationModel();
            qualificationModel.setId(model.getId());
            qualificationModel.setUid(model.getUid());
            qualificationModel.setName(model.getName());
            qualificationModelList.add(qualificationModel);
        }

        profileData.setQualifications(qualificationModelList);
        profileData.setDoctorSpecialization(modelList);
        if (profileImage != null) {
            profileData.setBase64Photo(getBase64Image());
        }

        if (selectedDays.contains("Sunday"))
            profileData.getDoctorAvailability().setSunday(true);
        else
            profileData.getDoctorAvailability().setSunday(false);

        if (selectedDays.contains("Monday"))
            profileData.getDoctorAvailability().setMonday(true);
        else
            profileData.getDoctorAvailability().setMonday(false);

        if (selectedDays.contains("Tuesday"))
            profileData.getDoctorAvailability().setTuesday(true);
        else
            profileData.getDoctorAvailability().setTuesday(false);

        if (selectedDays.contains("Wednesday"))
            profileData.getDoctorAvailability().setWednesday(true);
        else
            profileData.getDoctorAvailability().setWednesday(false);

        if (selectedDays.contains("Thursday"))
            profileData.getDoctorAvailability().setThursday(true);
        else
            profileData.getDoctorAvailability().setThursday(false);

        if (selectedDays.contains("Friday"))
            profileData.getDoctorAvailability().setFriday(true);
        else
            profileData.getDoctorAvailability().setFriday(false);

        if (selectedDays.contains("Saturday"))
            profileData.getDoctorAvailability().setSaturday(true);
        else
            profileData.getDoctorAvailability().setSaturday(false);

        profileData.setSubscriptionId(1);

        return profileData;
    }

    private String getBase64Image() {
        InputStream inputStream = null;//You can get an inputStream using any IO API
        try {
            inputStream = new FileInputStream(profileImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        String base= Base64.encodeToString(bytes, Base64.DEFAULT);;
        return base;
    }

    public List<SpecializationModel> getSelectedlistLevel3() {
        return selectedlistLevel3;
    }

    public List<SpecializationModel> getSelectedQualifications() {
        return selectedQualifications;
    }

    public void setData(boolean isUpdate) {
        if (isUpdate) {
            btnSave.setText("Update");
            tvHeaderTitle.setText("My Profile");
        }else{
            btnSave.setText("Save");
            tvHeaderTitle.setText("Create Profile");
        }
    }
}
