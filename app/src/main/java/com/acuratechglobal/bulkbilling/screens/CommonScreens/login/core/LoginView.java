package com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.request.LoginFbApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.facebook.GraphResponse;
import com.jakewharton.rxbinding3.view.RxView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getColor;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getDrawable;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.playFailureAnimation;
import static com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations.PASSWORD_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations.USERNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations.USERNAME_INAVALID;

public class LoginView {

    private View view;
    private EditText edEmail;
    private EditText edPassword;
    private Button btnLogin;
    private ImageButton btnShowPass,btnBack;
    private Button btnForgotPassword;
    private Button btnloginFB;
    private Button btnSignUp;
//    private Button btnDoctor,btnPatient;

    private final LoginActivity activity;
    private final ProgressDialog progressDialog;
    boolean isShown=false;
    private LoginFbApiRequest requestFb ;

    public LoginView(LoginActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_login, parent, true);
        btnLogin= view.findViewById(R.id.btnLogin);
        btnShowPass= view.findViewById(R.id.btnShowPass);
        btnForgotPassword= view.findViewById(R.id.btnForgotPassword);
        btnloginFB= view.findViewById(R.id.btnLoginFB);
        btnSignUp= view.findViewById(R.id.btnSignup);
        edEmail= view.findViewById(R.id.edEmail);
        edPassword= view.findViewById(R.id.edPassword);
//        btnDoctor= view.findViewById(R.id.btnDoctor);
//        btnPatient= view.findViewById(R.id.btnPatient);
        btnBack= view.findViewById(R.id.btnBack);
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

    Observable<Unit> forgotPasswdClick() {
        return RxView.clicks(btnForgotPassword);
    }

    Observable<Unit> loginClick() {
        return RxView.clicks(btnLogin);
    }

    Observable<Unit> btnShowPass() {
        return RxView.clicks(btnShowPass);
    }

    Observable<Unit> signupClick() {
        return RxView.clicks(btnSignUp);
    }

    Observable<Unit> btnBack() {
        return RxView.clicks(btnBack);
    }

//    Observable<Unit> doctorClick() {
//        return RxView.clicks(btnDoctor);
//    }
//
//    Observable<Unit> patientClick() {
//        return RxView.clicks(btnPatient);
//    }

    LoginApiRequest getParams() {
            LoginApiRequest task = new LoginApiRequest();
            task.setUserName(getInputText(edEmail));
            task.setPassword(getInputText(edPassword));
            task.setUserType(AppController.getUserType());

            return task;
    }

//    void toggleTabs(boolean isDoctor){
//        if (isDoctor){
//            btnDoctor.setBackground(getDrawable(activity,R.drawable.bg_button_green));
//            btnDoctor.setTextColor(Color.WHITE);
//            btnDoctor.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity,R.drawable.doctor_selected),null,null,null);
//            btnPatient.setBackground(getDrawable(activity,R.drawable.bg_curved_green_outline));
//            btnPatient.setTextColor(getColor(activity,R.color.colorTextGreen));
//            btnPatient.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity,R.drawable.patient_select),null,null,null);
//            userType=2;
//        }else{
//            btnDoctor.setBackground(getDrawable(activity,R.drawable.bg_curved_green_outline));
//            btnDoctor.setTextColor(getColor(activity,R.color.colorTextGreen));
//            btnDoctor.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity,R.drawable.doctor_select),null,null,null);
//            btnPatient.setBackground(getDrawable(activity,R.drawable.bg_button_green));
//            btnPatient.setTextColor(Color.WHITE);
//            btnPatient.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity,R.drawable.patient_selected),null,null,null);
//            userType=3;
//        }
//    }

    void showPass(){
        if (isShown){
            isShown=false;
            btnShowPass.setImageDrawable(getDrawable(activity,R.drawable.view));
            edPassword.setTransformationMethod(new PasswordTransformationMethod());
        }else{
            isShown=true;
            btnShowPass.setImageDrawable(getDrawable(activity,R.drawable.hide));
            edPassword.setTransformationMethod(null);
        }
    }

    void setLoginFbRequest(JSONObject object, GraphResponse response) {
        if (object != null) {
            requestFb = new LoginFbApiRequest();
//            HashMap<String,String> data=new HashMap<>();
            if (object.has("id")) {
                try {
                    requestFb.setFbUniqueId(object.getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestFb.setUserType(AppController.getUserType());
                requestFb.setDevice_ID(1);
                requestFb.setDevice_Token("");
//                data.put(WebConstants.PARAM_SOCIAL_ID,fbId);
            }
        }
    }



    void handleErrorCode(Integer errorCode) {
        if (null == errorCode) {
            return;
        }

        switch (errorCode) {
            case USERNAME_EMPTY:
                playFailureAnimation(edEmail);
                break;

            case USERNAME_INAVALID:
                playFailureAnimation(edEmail);
                break;

            case PASSWORD_EMPTY:
                playFailureAnimation(edPassword);
                break;

            default:
                //no-op
                break;
        }
    }

    LoginFbApiRequest getParamsFb() {
        return requestFb;
    }

}
