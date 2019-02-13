package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;

public class BookAppointmentValidations {

    public static ValidationResponse validateBookAppointmentRequest(BookAppointmentModel request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (request.getDate() == null || TextUtils.isEmpty(request.getDate())) {
            response.setSuccess(false);
            response.setFailReason("Please select date");
            return response;
        }

        if (request.getTimeSlot() == null || request.getTimeSlot()<0) {
            response.setSuccess(false);
            response.setFailReason("Please select time");
            return response;
        }

        response.setSuccess(true);

        return response;
    }
}
