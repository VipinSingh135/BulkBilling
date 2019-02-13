package com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.SetPassActivity;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.playFailureAnimation;
import static com.acuratechglobal.bulkbilling.utils.Validations.SetPasswordValidation.CONFIRM_PASS_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SetPasswordValidation.PASSWORD_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SetPasswordValidation.PASSWORD_INVALID;
import static com.acuratechglobal.bulkbilling.utils.Validations.SetPasswordValidation.PASSWORD_NOT_MATCH;

public class SetPassView {

    private View view;

    private EditText edPassword;
    private EditText edConfirmPassword;
    private Button btnDone;
    private ImageButton btnBack;

//    private int userType= 0;

    private final SetPassActivity activity;
    private final ProgressDialog progressDialog;

    public SetPassView(SetPassActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_set_password, parent, true);
        btnDone= view.findViewById(R.id.btnDone);
        edPassword= view.findViewById(R.id.edPassword);
        edConfirmPassword= view.findViewById(R.id.edConfirmPassword);
        btnBack= view.findViewById(R.id.btnBack);
//        ButterKnife.bind(view, activity);

    }

//    public int getUserType() {
//        return userType;
//    }
//
//    public void setUserType(int userType) {
//        this.userType = userType;
//    }

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


    SetPassApiRequest getParams() {
            SetPassApiRequest task = new SetPassApiRequest();
            task.setNewPassword(getInputText(edPassword));
            task.setConfirmPassword(getInputText(edConfirmPassword));
            task.setUserUniqueID(AppController.getUserID());
            return task;
    }

    void handleErrorCode(Integer errorCode) {
        if (null == errorCode) {
            return;
        }

        switch (errorCode) {
            case PASSWORD_EMPTY:
                playFailureAnimation(edPassword);
                break;

             case PASSWORD_INVALID:
                playFailureAnimation(edPassword);
                break;

            case CONFIRM_PASS_EMPTY:
                playFailureAnimation(edConfirmPassword);
                break;

            case PASSWORD_NOT_MATCH:
                playFailureAnimation(edConfirmPassword);
                break;

            default:
                //no-op
                break;
        }
    }

    SetPlanApiRequest getPlanParams() {
        SetPlanApiRequest task = new SetPlanApiRequest();

        task.setMemberShipPlanID(1);

        task.setUserUniqueID(AppController.getUserID());
        return task;
    }
}
