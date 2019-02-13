package com.acuratechglobal.bulkbilling.screens.CommonScreens.login.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.request.LoginFbApiRequest;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.ForgotPassActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.SignUpActivity;
import com.acuratechglobal.bulkbilling.utils.FacebookLoginUtils.FacebookLogin;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import io.reactivex.Observable;

public class LoginModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final LoginActivity activity;
    private final Api apis;
    private final SharedPrefsUtil prefs;


    FacebookLogin mFacebookLogin ;

    public LoginModel(LoginActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        this.mFacebookLogin= new FacebookLogin(activity);
        this.prefs = prefs;
    }

    void finish() {
        this.activity.finish();
    }

    void gotoSignUp() {
        Intent intent= new Intent(activity, SignUpActivity.class);
        activity.startActivity(intent);
    }

    void gotoForgotPass() {
        Intent intent= new Intent(activity, ForgotPassActivity.class);
        activity.startActivity(intent);
    }

    void gotoHome(UserData userData) {

        prefs.save(SharedPrefsUtil.PREFERENCE_USER_DATA,userData);

//        UserData data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);

        Intent intent;
        if (userData.getUserType()!=null && userData.getUserType()==3) {
            intent = new Intent(activity, MainActivity.class);
        }else if (userData.getNewUser()!=null && userData.getNewUser()){
            intent = new Intent(activity, CreateProfileActivity.class);
        }else {
            intent = new Intent(activity, MainActivity.class);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<LoginApiResponse> performLogin(LoginApiRequest request) {
        return apis.apiLogin(request);
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

    Observable<LoginApiResponse> performLoginFb(LoginFbApiRequest request) {
        return apis.apiLoginFb(request);
    }

}
