package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.list.adapterDocAppointment;
import com.jakewharton.rxbinding3.view.RxView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

public class DocAppiontmentView {

    private View view;
    ImageButton btnMenu;
    RecyclerView recyclerAppointments;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;

    public DocAppiontmentView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_fragment_my_appointments, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        recyclerAppointments= view.findViewById(R.id.recyclerAppointments);
        recyclerAppointments.setLayoutManager(new LinearLayoutManager(activity));
        recyclerAppointments.setAdapter(new adapterDocAppointment());
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

    @Nullable
    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }


}
