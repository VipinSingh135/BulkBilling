package com.acuratechglobal.bulkbilling.utils.Validations;

import android.text.TextUtils;

import com.acuratechglobal.bulkbilling.api.request.CreateProfileApiRequest;
import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;

public class CreateProfileValidations {

    public static ValidationResponse validateCreateProfileRequest(CreateProfileApiRequest request) {
        ValidationResponse response = new ValidationResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing request object!");
            return response;
        }

        if (request.getDoctorSpecialization() == null || request.getDoctorSpecialization().size() == 0) {
            response.setSuccess(false);
            response.setFailReason("Please select specialization");
            return response;
        }

        if (TextUtils.isEmpty(request.getClinicName())) {
            response.setSuccess(false);
            response.setFailReason("Please enter clinic name");
            return response;
        }
        if (TextUtils.isEmpty(request.getClinicAddress())) {
            response.setSuccess(false);
            response.setFailReason("Please enter clinic address");
            return response;
        }
        if (request.getExperience() == 0) {
            response.setSuccess(false);
            response.setFailReason("Please select experience");
            return response;
        }

        if (request.getQualifications() == null || request.getQualifications().size() == 0) {
            response.setSuccess(false);
            response.setFailReason("Please select qualifications");
            return response;
        }


        if (!request.getDoctorAvailability().getSunday() &&
                !request.getDoctorAvailability().getMonday() &&
                !request.getDoctorAvailability().getTuesday() &&
                !request.getDoctorAvailability().getWednesday() &&
                !request.getDoctorAvailability().getThursday() &&
                !request.getDoctorAvailability().getFriday() &&
                !request.getDoctorAvailability().getSaturday()){
            response.setSuccess(false);
            response.setFailReason("Please select available days");
            return response;
        }

        if (TextUtils.isEmpty(request.getOpenTime())) {
            response.setSuccess(false);
            response.setFailReason("Please select open time");
            return response;
        }

        if (TextUtils.isEmpty(request.getCloseTime())) {
            response.setSuccess(false);
            response.setFailReason("Please select close time");
            return response;
        }

            response.setSuccess(true);
        return response;
    }
}
