package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.SetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;

public class SetPasswordValidation {

    public static final int PASSWORD_EMPTY = 0;
    public static final int CONFIRM_PASS_EMPTY = 1;
    public static final int PASSWORD_NOT_MATCH = 2;
    public static final int PASSWORD_INVALID = 3;


    public static ValidationResponse validateSetPassRequest(SetPassApiRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (TextUtils.isEmpty(request.getNewPassword())) {
            response.setSuccess(false);
            response.setFailReason("Please enter password");
            response.setFailCode(PASSWORD_EMPTY);
            return response;
        }
        if (TextUtils.isEmpty(request.getConfirmPassword())) {
            response.setSuccess(false);
            response.setFailReason("Please confirm your password");
            response.setFailCode(CONFIRM_PASS_EMPTY);
            return response;
        }
        if (!ValidationUtils.isValidPassword(request.getNewPassword())) {
            response.setSuccess(false);
            response.setFailReason("Password should have minimum 6 characters");
            response.setFailCode(PASSWORD_INVALID);
            return response;
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            response.setSuccess(false);
            response.setFailReason("Password did not match");
            response.setFailCode(PASSWORD_NOT_MATCH);
            return response;
        }

        response.setSuccess(true);
        return response;
    }
}
