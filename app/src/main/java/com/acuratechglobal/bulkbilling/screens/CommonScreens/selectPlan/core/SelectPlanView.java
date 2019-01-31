package com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.SelectPlanActivity;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;


public class SelectPlanView {

    private View view;

    private RadioButton rdBtnBasic,rdBtnPremium;
    private Button btnDone;
    private ImageButton btnBack;

    private boolean isBasic= true;

    private final SelectPlanActivity activity;
    private final ProgressDialog progressDialog;

    public SelectPlanView(SelectPlanActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_subscription_plans, parent, true);
        btnDone= view.findViewById(R.id.btnDone);
        rdBtnBasic= view.findViewById(R.id.rdBtnBasic);
        rdBtnPremium= view.findViewById(R.id.rdBtnPremium);
        btnBack= view.findViewById(R.id.btnBack);
//        ButterKnife.bind(view, activity);
        setPlan(isBasic);
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

    Observable<Unit> doneClick() {
        return RxView.clicks(btnDone);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }

    Observable<Unit> basicClicked() {
        return RxView.clicks(rdBtnBasic);
    }

    Observable<Unit> premiumClicked() {
        return RxView.clicks(rdBtnPremium);
    }

    void setPlan(Boolean isBasic){
        if (isBasic){
            rdBtnBasic.setChecked(true);
            rdBtnPremium.setChecked(false);
            this.isBasic=true;
        }else{
            rdBtnBasic.setChecked(false);
            rdBtnPremium.setChecked(true);
            this.isBasic=false;
        }
    }

    SetPlanApiRequest getParams() {
            SetPlanApiRequest task = new SetPlanApiRequest();
            if (isBasic){
                task.setMemberShipPlanID(1);
            }else{
                task.setMemberShipPlanID(2);
            }
            task.setUserUniqueID(AppController.getUserID());
            return task;
    }

}
