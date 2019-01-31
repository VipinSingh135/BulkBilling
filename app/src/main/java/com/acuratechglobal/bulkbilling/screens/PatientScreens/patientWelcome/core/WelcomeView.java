package com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.WelcomeActivity;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;


public class WelcomeView {

    private View view;

    private Button btnContinue;

    private final WelcomeActivity activity;

    public WelcomeView(WelcomeActivity context, ProgressDialog dialog) {
        this.activity= context;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_activity_registered_successfully, parent, true);
        btnContinue= view.findViewById(R.id.btnContinue);
    }

    public View getView() {
        return this.view;
    }


    Observable<Unit> doneClick() {
        return RxView.clicks(btnContinue);
    }


}
