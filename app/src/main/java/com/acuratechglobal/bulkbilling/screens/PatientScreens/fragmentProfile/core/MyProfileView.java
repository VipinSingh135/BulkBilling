package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core;

import android.app.ProgressDialog;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.PatientProfileModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.list.adapterHistory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

public class MyProfileView {

    private View view;
    private ImageButton btnMenu;
    private RecyclerView recyclerHistory;
    private ImageView imgProfile;
    private Button btnEdit;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private TextView tvName, tvPhone, tvEmail,tvAddress;

    private PatientProfileModel profileData;
    public MyProfileView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_my_profile, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        btnEdit= view.findViewById(R.id.btnEdit);
        imgProfile= view.findViewById(R.id.imgProfile);
        recyclerHistory= view.findViewById(R.id.recyclerHistory);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        tvAddress = view.findViewById(R.id.tvAddress);

//        Glide.with(activity).load(R.drawable.patient_img).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        recyclerHistory.setLayoutManager(new LinearLayoutManager(activity));
        recyclerHistory.setAdapter(new adapterHistory());
//        ButterKnife.bind(view, activity);
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

    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }

    Observable<Unit> editClick() {
        return RxView.clicks(btnEdit);
    }

    void bindViews(PatientProfileModel data) {
        profileData = data;

        tvName.setText(profileData.getFirstName()+" "+profileData.getLastName());
        tvEmail.setText(profileData.getEmail());
        tvAddress.setText(profileData.getAddress());
        if (profileData.getPhone() != null)
            tvPhone.setText(profileData.getPhone());

        if (profileData.getsPhotoPath() != null) {
            Glide.with(activity).load(Uri.parse(profileData.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
//            AppController.setUserImage(profileData.getsPhotoPath());
        }

    }

    PatientProfileModel getProfileData(){
        return profileData;
    }

}
