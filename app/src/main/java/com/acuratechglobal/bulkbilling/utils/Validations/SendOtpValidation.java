package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.SendOtpRequest;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;

public class SendOtpValidation {

    public static final int PHONE_EMPTY = 0;
    public static final int PHONE_INAVALID =  3;


    public static ValidationResponse validateSendOtp(SendOtpRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (TextUtils.isEmpty(request.getPhone())) {
            response.setSuccess(false);
            response.setFailReason("Please enter phone number");
            response.setFailCode(PHONE_EMPTY);
            return response;
        }

        if (!ValidationUtils.isValidPhone(request.getPhone())) {
            response.setSuccess(false);
            response.setFailReason("Please enter valid phone number");
            response.setFailCode(PHONE_INAVALID);
            return response;
        }

        response.setSuccess(true);
        return response;
    }
}
