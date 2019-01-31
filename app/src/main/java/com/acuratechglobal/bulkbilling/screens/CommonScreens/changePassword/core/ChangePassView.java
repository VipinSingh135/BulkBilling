package com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.ChangePassApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.playFailureAnimation;
import static com.acuratechglobal.bulkbilling.utils.Validations.ChangePassValidations.PASSWORD_EMPTY;

public class ChangePassView {

    private View view;
    private EditText edOldPassword,edNewPassword,edConfirmPassword;
    private Button btnDone;
    private ImageButton btnBack;
    private UserData data;
    private final ChangePassActivity activity;
    private final ProgressDialog progressDialog;

    public ChangePassView(ChangePassActivity context, ProgressDialog dialog, SharedPrefsUtil prefs) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_activity_change_password, parent, true);
        btnDone= view.findViewById(R.id.btnDone);
        btnBack= view.findViewById(R.id.btnBack);
        edOldPassword= view.findViewById(R.id.edOldPassword);
        edNewPassword= view.findViewById(R.id.edNewPassword);
        edConfirmPassword= view.findViewById(R.id.edConfirmPassword);

        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
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

    ChangePassApiRequest getParams() {
            ChangePassApiRequest task = new ChangePassApiRequest();
            task.setOldPassword(getInputText(edOldPassword));
            task.setNewPassword(getInputText(edNewPassword));
            task.setConfirmPassword(getInputText(edConfirmPassword));
            task.setUserUniqueID(data.getUserUniqueId());
            task.setUserType(data.getUserType());

            return task;
    }

//    void  invalidPassword(){
//        playFailureAnimation(edOldPassword);
//    }

    void handleErrorCode(Integer errorCode) {
        if (null == errorCode) {
            return;
        }

        switch (errorCode) {

            case PASSWORD_EMPTY:
                playFailureAnimation(edOldPassword);
                break;

            default:
                //no-op
                break;
        }
    }

}
