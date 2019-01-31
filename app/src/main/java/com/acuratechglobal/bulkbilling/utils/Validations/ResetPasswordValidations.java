package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;

public class ResetPasswordValidations {


    public static final int OTP_EMPTY = 0;
    public static final int OTP_INVALID = 1;
    public static final int PASSWORD_EMPTY = 2;
    public static final int PASSWORD_INVALID = 3;
    public static final int CONFIRM_PASS_EMPTY = 4;
    public static final int PASSWORD_NOT_MATCH = 5;


    public static ValidationResponse validateResetPassRequest(ResetPassApiRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (TextUtils.isEmpty(request.getOtp())) {
            response.setSuccess(false);
            response.setFailReason("Please enter OTP");
            response.setFailCode(OTP_EMPTY);
            return response;
        }
         if (!ValidationUtils.isValidOTP(request.getOtp())) {
            response.setSuccess(false);
            response.setFailReason("Invalid OTP");
            response.setFailCode(OTP_INVALID);
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
