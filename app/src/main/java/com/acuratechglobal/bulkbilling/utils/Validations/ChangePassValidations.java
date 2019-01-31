package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.ChangePassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;

public class ChangePassValidations {


    public static final int OLD_PASS_EMPTY = 0;
    public static final int OLD_PASS_INVALID = 1;
    public static final int PASSWORD_EMPTY = 2;
    public static final int PASSWORD_INVALID = 3;
    public static final int CONFIRM_PASS_EMPTY = 4;
    public static final int PASSWORD_NOT_MATCH = 5;


    public static ValidationResponse validateChangePassRequest(ChangePassApiRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (TextUtils.isEmpty(request.getOldPassword())) {
            response.setSuccess(false);
            response.setFailReason("Please enter old password");
            response.setFailCode(OLD_PASS_EMPTY);
            return response;
        }
        if (!ValidationUtils.isValidPassword(request.getOldPassword())) {
            response.setSuccess(false);
            response.setFailReason("Password should have minimum 6 characters");
            response.setFailCode(OLD_PASS_INVALID);
            return response;
        }
        if (TextUtils.isEmpty(request.getNewPassword())) {
            response.setSuccess(false);
            response.setFailReason("Please enter new password");
            response.setFailCode(PASSWORD_EMPTY);
            return response;
        }

        if (!ValidationUtils.isValidPassword(request.getNewPassword())) {
            response.setSuccess(false);
            response.setFailReason("Password should have minimum 6 characters");
            response.setFailCode(PASSWORD_INVALID);
            return response;
        }
        if (TextUtils.isEmpty(request.getConfirmPassword())) {
            response.setSuccess(false);
            response.setFailReason("Please confirm your password");
            response.setFailCode(CONFIRM_PASS_EMPTY);
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
