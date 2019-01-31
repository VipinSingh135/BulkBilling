package com.acuratechglobal.bulkbilling.utils.FacebookLoginUtils;

import com.facebook.GraphResponse;

import org.json.JSONObject;

public interface FacebookLoginListener {

        void onFbLoginSuccess();

        void onFbLoginCancel();

        void onFbLoginError();

        void onGetprofileSuccess(JSONObject object, GraphResponse response);

}
