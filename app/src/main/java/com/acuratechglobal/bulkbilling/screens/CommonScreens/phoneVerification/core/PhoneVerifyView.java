package com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.PhoneVerifyActivity;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getColor;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getDrawable;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;

public class PhoneVerifyView {

    private View view;

    private EditText edCode1,edCode2,edCode3,edCode4;
    private Button btnResend;
    private Button btnVerify;
    private ImageButton btnBack;

    private final PhoneVerifyActivity activity;
    private final ProgressDialog progressDialog;
//    int userType= 0;
    public PhoneVerifyView(PhoneVerifyActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_verify_number, parent, true);
        btnResend= view.findViewById(R.id.btnResend);
        edCode1= view.findViewById(R.id.edCode1);
        edCode2= view.findViewById(R.id.edCode2);
        edCode3= view.findViewById(R.id.edCode3);
        edCode4= view.findViewById(R.id.edCode4);
        btnVerify= view.findViewById(R.id.btnVerify);
        btnBack= view.findViewById(R.id.btnBack);
        btnBack= view.findViewById(R.id.btnBack);
        btnVerify.setClickable(false);
    }

    public String getCode () {
        String code= getInputText(edCode1).concat(getInputText(edCode2)).concat(getInputText(edCode3)).concat(getInputText(edCode4));
        return code;
    }

    public View getView() {
        return this.view;
    }

//    public int getUserType() {
//        return userType;
//    }
//
//    public void setUserType(int userType) {
//        this.userType = userType;
//    }

    Observable<String> edCode1Event() {
        return RxTextView.textChangeEvents(edCode1).map(new Function<TextViewTextChangeEvent, String>() {
            @Override
            public String apply(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                return textViewTextChangeEvent.getText().toString();
            }
        });
    }
    Observable<String> edCode2Event() {
        return RxTextView.textChangeEvents(edCode2).map(new Function<TextViewTextChangeEvent, String>() {
            @Override
            public String apply(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                return textViewTextChangeEvent.getText().toString();
            }
        });
    }
    Observable<String> edCode3Event() {
        return RxTextView.textChangeEvents(edCode3).map(new Function<TextViewTextChangeEvent, String>() {
            @Override
            public String apply(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                return textViewTextChangeEvent.getText().toString();
            }
        });
    }
    Observable<String> edCode4Event() {
        return RxTextView.textChangeEvents(edCode4).map(new Function<TextViewTextChangeEvent, String>() {
            @Override
            public String apply(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                return textViewTextChangeEvent.getText().toString();
            }
        });
    }

    void changeFocus1(Boolean isFocused){
        if (isFocused){
            edCode2.requestFocus();
        }else{
            edCode1.requestFocus();
        }
    }
    void changeFocus2(Boolean isFocused){
        if (isFocused){
            edCode3.requestFocus();
        }else{
            edCode1.requestFocus();
        }
    }
    void changeFocus3(Boolean isFocused){
        if (isFocused){
            edCode4.requestFocus();
        }else{
            edCode2.requestFocus();
        }
    }
    void changeFocus4(Boolean isFocused){
        if (isFocused){
            UiUtils.hideKeyboard(activity);
        } else {
                    /*
                    If enough text for  all fields, no need cursor
                    Otherwise, focus previous edit text
                     */
            if (!UiUtils.getInputText(edCode1).isEmpty() && !UiUtils.getInputText(edCode2).isEmpty() && !UiUtils.getInputText(edCode3).isEmpty()) {
                edCode3.requestFocus();
            } else {
                edCode1.clearFocus();
                edCode2.clearFocus();
                edCode3.clearFocus();
                edCode4.clearFocus();
            }
        }
    }

    Observable<Unit> verifyClick() {
        return RxView.clicks(btnVerify);
    }

    Observable<Unit> resendClick() {
        return RxView.clicks(btnResend);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }

    void enableVerifyBtn(Boolean doEnable){
        if (doEnable) {
            btnVerify.setClickable(true);
            btnVerify.setBackground(getDrawable(activity, R.drawable.bg_button_yellow));
            btnVerify.setTextColor(getColor(activity,R.color.colorTextGreen));
        }else{
            btnVerify.setClickable(false);
            btnVerify.setBackground(getDrawable(activity, R.drawable.bg_button_grey_outline));
            btnVerify.setTextColor(getColor(activity,R.color.colorGreyDark));
        }
    }

    void showToast(String msg){
        Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
    }

}
