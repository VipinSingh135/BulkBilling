package com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.ResetPassActivity;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;

public class ResetPassView {

    private View view;

    private EditText edOTP,edPassword,edConfirmPassword;
    private Button btnDone;
    private ImageButton btnBack;

    private final ResetPassActivity activity;
    private final ProgressDialog progressDialog;
    int userType=0;

    public ResetPassView(ResetPassActivity context, ProgressDialog dialog) {
        this.activity = context;
        this.progressDialog = dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_reset_password, parent, true);
        btnDone = view.findViewById(R.id.btnDone);
        edOTP = view.findViewById(R.id.edOTP);
        edPassword = view.findViewById(R.id.edPassword);
        edConfirmPassword = view.findViewById(R.id.edConfirmPassword);
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
        return RxView.clicks(btnDone);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }

    ResetPassApiRequest getParams() {
        ResetPassApiRequest task = new ResetPassApiRequest();
        task.setOtp(getInputText(edOTP));
        task.setConfirmPassword(getInputText(edConfirmPassword));
        task.setNewPassword(getInputText(edPassword));
        task.setUserUniqueID(AppController.getUserID());
        task.setUserType(userType);
        return task;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
