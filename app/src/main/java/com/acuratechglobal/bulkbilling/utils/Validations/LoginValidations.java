package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;

public class LoginValidations {
    public static final int USERNAME_EMPTY = 0;
    public static final int USERNAME_INAVALID =  1;
    public static final int PASSWORD_EMPTY = 3;

    private LoginValidations() {
        //no-op
    }

    public static ValidationResponse validateLoginRequest(LoginApiRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (TextUtils.isEmpty(request.getUserName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter email");
            response.setFailCode(USERNAME_EMPTY);
            return response;
        }

        if (!ValidationUtils.isValidEmail(request.getUserName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter valid email address");
            response.setFailCode(USERNAME_INAVALID);
            return response;
        }

        if (TextUtils.isEmpty(request.getPassword())) {
            response.setSuccess(false);
            response.setFailReason("Please enter password");
            response.setFailCode(PASSWORD_EMPTY);
            return response;
        }

        response.setSuccess(true);
        return response;
    }

}
