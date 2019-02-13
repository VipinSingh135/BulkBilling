package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.acuratechglobal.bulkbilling.R;
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
    ImageButton btnMenu;
    RecyclerView recyclerHistory;
    ImageView imgProfile;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;

    public MyProfileView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_my_profile, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        imgProfile= view.findViewById(R.id.imgProfile);
        recyclerHistory= view.findViewById(R.id.recyclerHistory);

        Glide.with(activity).load(R.drawable.patient_img).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
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

}
