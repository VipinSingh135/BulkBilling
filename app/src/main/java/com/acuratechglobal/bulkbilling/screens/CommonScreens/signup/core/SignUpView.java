package com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.SignUpActivity;
import com.facebook.GraphResponse;
import com.jakewharton.rxbinding3.view.RxView;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getColor;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getDrawable;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.playFailureAnimation;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.CONTACT_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.CONTACT_INVALID;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.FIRSTNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.FIRSTNAME_INVALID;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.LASTNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.LASTNAME_INVALID;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.USERNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.SignUpValidations.USERNAME_INAVALID;

public class SignUpView {

    private View view;

    private EditText edEmail;
    private EditText edFirstName;
    private EditText edLastName;
    private EditText edContactNumber;
    private Button btnLogin;
//    private Button btnDoctor, btnPatient;
    private Button btnSignUpFB;
    private Button btnNext;
    private ImageButton btnBack;
    //    private RadioButton linearEmail,linearSMS;
    private LinearLayout linearEmail, linearSMS;
    private ImageView imgEmail, imgSMS;
    private CheckBox checkbox;
    private int notifyType = 1;
//    private int userType = 2;
    private final SignUpActivity activity;
    private final ProgressDialog progressDialog;
    private boolean isEmail = false, isSMS = false;
    private SignUpApiRequest task;

    public SignUpView(SignUpActivity context, ProgressDialog dialog) {
        this.activity = context;
        this.progressDialog = dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_signup, parent, true);
        btnLogin = view.findViewById(R.id.btnLogin);
        edEmail = view.findViewById(R.id.edEmail);
        edFirstName = view.findViewById(R.id.edFirstName);
        edLastName = view.findViewById(R.id.edLastName);
        edContactNumber = view.findViewById(R.id.edContactNumber);
//        btnDoctor = view.findViewById(R.id.btnDoctor);
//        btnPatient = view.findViewById(R.id.btnPatient);
        btnSignUpFB = view.findViewById(R.id.btnSignUpFB);
        btnNext = view.findViewById(R.id.btnNext);
        btnBack = view.findViewById(R.id.btnBack);
        linearEmail = view.findViewById(R.id.linearEmail);
        linearSMS = view.findViewById(R.id.linearSMS);
        imgEmail = view.findViewById(R.id.imgChkEmail);
        imgSMS = view.findViewById(R.id.imgChkSms);
        checkbox = view.findViewById(R.id.checkbox);
        btnBack = view.findViewById(R.id.btnBack);

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

    Observable<Unit> nextClick() {
        return RxView.clicks(btnNext);
    }

    Observable<Unit> loginClick() {
        return RxView.clicks(btnLogin);
    }

//    Observable<Unit> signupFbClick() {
//        return RxView.clicks(btnSignUpFB);
//    }

//    Observable<Unit> doctorClick() {
//        return RxView.clicks(btnDoctor);
//    }
//
//    Observable<Unit> patientClick() {
//        return RxView.clicks(btnPatient);
//    }

    Observable<Unit> emailClick() {
        return RxView.clicks(linearEmail);
    }

    Observable<Unit> smsClick() {
        return RxView.clicks(linearSMS);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }

    String getPhone() {
        return getInputText(edContactNumber);
    }

    public Observable<Boolean> isChecked() {
        return Observable.just(checkbox.isChecked());
    }

    void isEmail() {
        if (isEmail) {
            isEmail = false;
            imgEmail.setImageResource(R.drawable.radio_off);
        } else {
            isEmail = true;
            imgEmail.setImageResource(R.drawable.radio_on);
        }
    }

    void isSms() {
        if (isSMS) {
            isSMS = false;
            imgSMS.setImageResource(R.drawable.radio_off);
        } else {
            isSMS = true;
            imgSMS.setImageResource(R.drawable.radio_on);
        }
    }

//    void toggleTabs(boolean isDoctor) {
//        if (isDoctor) {
//            btnDoctor.setBackground(getDrawable(activity, R.drawable.bg_button_green));
//            btnDoctor.setTextColor(Color.WHITE);
//            btnDoctor.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity, R.drawable.doctor_selected), null, null, null);
//            btnPatient.setBackground(getDrawable(activity, R.drawable.bg_curved_green_outline));
//            btnPatient.setTextColor(getColor(activity, R.color.colorTextGreen));
//            btnPatient.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity, R.drawable.patient_select), null, null, null);
//            userType = 2;
//        } else {
//            btnDoctor.setBackground(getDrawable(activity, R.drawable.bg_curved_green_outline));
//            btnDoctor.setTextColor(getColor(activity, R.color.colorTextGreen));
//            btnDoctor.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity, R.drawable.doctor_select), null, null, null);
//            btnPatient.setBackground(getDrawable(activity, R.drawable.bg_button_green));
//            btnPatient.setTextColor(Color.WHITE);
//            btnPatient.setCompoundDrawablesWithIntrinsicBounds(getDrawable(activity, R.drawable.patient_selected), null, null, null);
//            userType = 3;
//        }
//    }

    SignUpApiRequest getParams() {
        task = new SignUpApiRequest();
        task.setEmail(getInputText(edEmail));
        task.setFirstName(getInputText(edFirstName));
        task.setLastName(getInputText(edLastName));
        task.setPhone(getInputText(edContactNumber));
        task.setSignUpType(1);
        task.setUserType(AppController.getUserType());
        if (isEmail && isSMS) {
            notifyType = 3;
        } else if (isSMS) {
            notifyType = 2;
        } else if (isEmail) {
            notifyType = 1;
        } else {
            notifyType = 0;
        }
        task.setNotificationType(notifyType);
        return task;
    }

    void handleErrorCode(Integer errorCode) {
        if (null == errorCode) {
            return;
        }

        switch (errorCode) {
            case FIRSTNAME_EMPTY:
                playFailureAnimation(edFirstName);
                break;

            case FIRSTNAME_INVALID:
                playFailureAnimation(edFirstName);
                break;

            case LASTNAME_EMPTY:
                playFailureAnimation(edLastName);
                break;

            case LASTNAME_INVALID:
                playFailureAnimation(edLastName);
                break;

            case USERNAME_EMPTY:
                playFailureAnimation(edEmail);
                break;
            case USERNAME_INAVALID:
                playFailureAnimation(edEmail);
//                playFailureAnimation(edPassword);
                break;
            case CONTACT_EMPTY:
                playFailureAnimation(edContactNumber);
//                playFailureAnimation(edPassword);
                break;

            case CONTACT_INVALID:
                playFailureAnimation(edContactNumber);
//                playFailureAnimation(edPassword);
                break;

            default:
                //no-op
                break;
        }
    }

    void setLoginFbRequest(JSONObject object, GraphResponse response) {
        if (object != null) {
            task = new SignUpApiRequest();
//            HashMap<String,String> data=new HashMap<>();

            try {
                if (object.has("id")) {
                    task.setFacebookUID(object.getString("id"));
                }
                if (object.has("first_name"))
                    task.setFirstName(object.getString("first_name"));
                if (object.has("last_name"))
                    task.setLastName(object.getString("last_name"));
                if (object.has("email"))
                    task.setEmail(object.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            task.setUserType(AppController.getUserType());
            task.setNotificationType(notifyType);
            task.setSignUpType(2);
        }
    }

    public SignUpApiRequest getParamsFb() {
        return task;
    }

}
