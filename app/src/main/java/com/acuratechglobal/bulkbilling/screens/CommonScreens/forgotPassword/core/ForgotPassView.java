package com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SendOtpRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.ForgotPassActivity;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;

public class ForgotPassView {

    private View view;

    private EditText edPhone;
    private Button btnNext;
    private ImageButton btnBack;

    private final ForgotPassActivity activity;
    private final ProgressDialog progressDialog;

    public ForgotPassView(ForgotPassActivity context, ProgressDialog dialog) {
        this.activity = context;
        this.progressDialog = dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_forgot_password, parent, true);
        btnNext = view.findViewById(R.id.btnNext);
        edPhone = view.findViewById(R.id.edPhoneNumber);
        btnBack = view.findViewById(R.id.btnBack);
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

    Observable<Unit> doneClick() {
        return RxView.clicks(btnNext);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }


    SendOtpRequest getParams() {
        SendOtpRequest task = new SendOtpRequest();
        task.setPhone(getInputText(edPhone));
        task.setUserType(AppController.getUserType());
        return task;
    }

}
