package com.acuratechglobal.bulkbilling.utils.FacebookLoginUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookLogin {

    private Context mContext;
    FacebookLoginListener facebookLoginListener;
    private CallbackManager mCallbackManager;

    public FacebookLogin(Context context) {
        mContext = context;

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookLoginListener.onFbLoginSuccess();
            }

            @Override
            public void onCancel() {
                facebookLoginListener.onFbLoginCancel();
            }

            @Override
            public void onError(FacebookException exception) {
                facebookLoginListener.onFbLoginError();
            }
        });
    }

    public void performLogin(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity,
                Arrays.asList("email", "public_profile","user_birthday"));
    }

    public void getUserProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        facebookLoginListener.onGetprofileSuccess(object, response);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name,last_name,email,gender,picture,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void setFacebookLoginListener(FacebookLoginListener facebookLoginListener){
        this.facebookLoginListener = facebookLoginListener;
    }

    public void performLogout() {
        LoginManager.getInstance().logOut();
    }
}


