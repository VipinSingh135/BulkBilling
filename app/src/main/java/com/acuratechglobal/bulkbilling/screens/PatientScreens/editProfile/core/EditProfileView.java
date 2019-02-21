package com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.core;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import com.acuratechglobal.bulkbilling.models.PatientProfileModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.EditProfileActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
public class EditProfileView {

    private View view;
    private EditText edFirstName, edLastName;
    private TextView tvAddress;
    private TextView tvName, tvPhone, tvEmail;
    private ImageView imgProfile;
    private Button btnSave;
    private ImageButton btnBack, btnAddImage;
    private final EditProfileActivity activity;
    private ProgressDialog progressDialog;

    private PatientProfileModel profileData = new PatientProfileModel();

    private File profileImage = null;

    public EditProfileView(EditProfileActivity context, SharedPrefsUtil prefs) {
        this.activity = context;
        if (prefs != null) {
            UserData data = prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA, UserData.class);
        }
        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_activity_edit_profile, parent, true);

        edFirstName = view.findViewById(R.id.edFirstName);

        tvAddress = view.findViewById(R.id.tvAddress);

        edLastName = view.findViewById(R.id.edLastName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);

        imgProfile = view.findViewById(R.id.imgProfile);

        btnSave = view.findViewById(R.id.btnSave);
        btnBack = view.findViewById(R.id.btnBack);
        btnAddImage = view.findViewById(R.id.btnAddImage);
    }

    void bindViews(Bundle bundle) {
        profileData= new Gson().fromJson(bundle.getString("data"),PatientProfileModel.class);
//        this.profileData = data;

        tvName.setText(profileData.getFirstName()+" "+profileData.getLastName());
        tvEmail.setText(profileData.getEmail());
        if (profileData.getPhone() != null)
            tvPhone.setText(profileData.getPhone());
        if (profileData.getFirstName() != null)
            edFirstName.setText(profileData.getFirstName());
        if (profileData.getLastName() != null)
            edLastName.setText(profileData.getLastName());
        if (profileData.getAddress() != null)
            tvAddress.setText(profileData.getAddress());

        if (profileData.getsPhotoPath() != null) {
            Glide.with(activity).load(Uri.parse(profileData.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
//            AppController.setUserImage(profileData.getsPhotoPath());
        }

    }

    void setProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
    }

    public View getView() {
        return this.view;
    }

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

    Observable<Unit> addImageClick() {
        return RxView.clicks(btnAddImage);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }

    Observable<Unit> clinicAddressClicked() {
        return RxView.clicks(tvAddress);
    }

    //Base 64 image
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
        String base = Base64.encodeToString(bytes, Base64.DEFAULT);
        ;
        return base;
    }

    void setProfileImage(Intent data, File profileImage) {
        this.profileImage = profileImage;
        final Uri resultUri = UCrop.getOutput(data);
        Glide.with(activity).load(resultUri).apply(RequestOptions.circleCropTransform()).into(imgProfile);
    }

    //Clinic Address
    void setClinicAddresss(Intent data) {

        Place place = PlacePicker.getPlace(data, activity);
//        lat = place.getLatLng().latitude;
//        lon = place.getLatLng().longitude;
        tvAddress.setText(place.getName());
//        tvAddress.setError(null);
    }


    //Set Params
    PatientProfileModel getParams() {
        profileData.setAddress(getInputText(tvAddress));
        profileData.setFirstName(getInputText(edFirstName));
        profileData.setLastName(getInputText(edLastName));
        if (profileImage != null) {
            profileData.setBase64Photo(getBase64Image());
        }

        return profileData;
    }

}
