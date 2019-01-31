package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;

public class SignUpValidations {

    public static final int FIRSTNAME_EMPTY = 0;
    public static final int FIRSTNAME_INVALID = 1;
    public static final int LASTNAME_EMPTY = 7;
    public static final int LASTNAME_INVALID = 6;
    public static final int USERNAME_EMPTY = 2;
    public static final int USERNAME_INAVALID =  3;
    public static final int CONTACT_EMPTY = 4;
    public static final int CONTACT_INVALID = 5;


    public static ValidationResponse validateSignUpRequest(SignUpApiRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (TextUtils.isEmpty(request.getFirstName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter first name");
            response.setFailCode(FIRSTNAME_EMPTY);
            return response;
        }
        if (!ValidationUtils.isValidName(request.getFirstName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter valid first name");
            response.setFailCode(FIRSTNAME_INVALID);
            return response;
        }

        if (TextUtils.isEmpty(request.getLastName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter last name");
            response.setFailCode(LASTNAME_EMPTY);
            return response;
        }
        if (!ValidationUtils.isValidName(request.getLastName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter valid last name");
            response.setFailCode(LASTNAME_INVALID);
            return response;
        }

        if (TextUtils.isEmpty(request.getEmail())) {
            response.setSuccess(false);
            response.setFailReason("Please enter email");
            response.setFailCode(USERNAME_EMPTY);
            return response;
        }
        if (!ValidationUtils.isValidEmail(request.getEmail())) {
            response.setSuccess(false);
            response.setFailReason("Please enter valid email address");
            response.setFailCode(USERNAME_INAVALID);
            return response;
        }

        if (TextUtils.isEmpty(request.getPhone())) {
            response.setSuccess(false);
            response.setFailReason("Please enter phone number");
            response.setFailCode(CONTACT_EMPTY);
            return response;
        }
        if (!ValidationUtils.isValidPhone(request.getPhone())) {
            response.setSuccess(false);
            response.setFailReason("Please enter valid phone number");
            response.setFailCode(CONTACT_INVALID);
            return response;
        }




        response.setSuccess(true);
        return response;
    }

}
