package com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.PhoneVerifyActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.SignUpActivity;
import com.acuratechglobal.bulkbilling.utils.FacebookLoginUtils.FacebookLogin;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;

import io.reactivex.Observable;

public class SignUpModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final SignUpActivity activity;
    private final Api apis;
    FacebookLogin mFacebookLogin ;

    public SignUpModel(SignUpActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
        this.mFacebookLogin= new FacebookLogin(activity);
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<CommonApiResponse> performSignUp(SignUpApiRequest request) {
        return apis.apiSignUp(request);
    }

    void gotoPhoneVerification(String phone){
        Intent intent= new Intent(activity, PhoneVerifyActivity.class);
        intent.putExtra("phone",phone);
//        intent.putExtra("userType", userType);
        activity.startActivity(intent);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    void requestLoginFb(){
        mFacebookLogin.setFacebookLoginListener(activity);
        mFacebookLogin.performLogout();
        mFacebookLogin.performLogin(activity);
    }

    void setActivityResult(int requestCode, int resultCode, Intent data) {
        mFacebookLogin.onActivityResult(requestCode,resultCode,data);
    }

    void getFacebookProfile() {
        mFacebookLogin.getUserProfile();
    }

    Observable<CommonApiResponse> performSignUpFb(SignUpApiRequest request) {
        return apis.apiSignUp(request);
    }


}
