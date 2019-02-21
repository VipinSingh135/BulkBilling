package com.acuratechglobal.bulkbilling.api;

import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.api.request.AddRatingRequest;
import com.acuratechglobal.bulkbilling.api.request.GetPatientRatingListRequest;
import com.acuratechglobal.bulkbilling.api.response.GetAppointmentListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetMyRatingsApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetPatientProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetPatientRatingApiResponse;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.api.request.ChangePassApiRequest;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.api.request.GetDoctorListRequest;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.request.LoginFbApiRequest;
import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SendOtpRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;
import com.acuratechglobal.bulkbilling.api.response.GetDoctorListApiResponse;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.api.response.ResetPassApiResponse;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.PatientProfileModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @POST(WebConstants.URL_LOGIN)
    Observable<LoginApiResponse> apiLogin(@Body LoginApiRequest request);

    @POST(WebConstants.URL_LOGIN)
    Observable<LoginApiResponse> apiLoginFb(@Body LoginFbApiRequest request);

    @POST(WebConstants.URL_SIGNUP)
    Observable<CommonApiResponse> apiSignUp(@Body SignUpApiRequest request);

    @POST(WebConstants.URL_SET_PASSWORD)
    Observable<CommonApiResponse> apiSetPass(@Body SetPassApiRequest request);

    @POST(WebConstants.URL_SET_PLAN)
    Observable<CommonApiResponse> apiSetPlan(@Body SetPlanApiRequest request);

    @POST(WebConstants.URL_FORGOT_PASSWORD)
    Observable<ResetPassApiResponse> apiForgotPass(@Body SendOtpRequest request);

    @POST(WebConstants.URL_RESET_PASSWORD)
    Observable<CommonApiResponse> apiResetPass(@Body ResetPassApiRequest request);

    @GET(WebConstants.URL_SPECIALIZATION_LEVEL1)
    Observable<SpecializationResponse> apiSpecialization1();

    @GET(WebConstants.URL_SPECIALIZATION_LEVEL2)
    Observable<SpecializationResponse> apiSpecialization2(@Query(WebConstants.PARAM_LEVEL1UID) String id);

    @GET(WebConstants.URL_SPECIALIZATION_LEVEL3)
    Observable<SpecializationResponse> apiSpecialization3(@Query(WebConstants.PARAM_LEVEL2UID) String id);

    @GET(WebConstants.URL_QUALIFICATION_LIST)
    Observable<SpecializationResponse> apiQualificationList();

    @POST(WebConstants.URL_CREATE_PROFILE)
    Observable<LoginApiResponse> apiCreateProfile(@Body DoctorProfileModel request);

    @GET(WebConstants.URL_GET_PROFILE)
    Observable<GetProfileApiResponse> apiGetProfile(@Query(WebConstants.PARAM_DOCUID) String id);

    @POST(WebConstants.URL_CHANGE_PASSWORD)
    Observable<CommonApiResponse> apiChangePassword(@Body ChangePassApiRequest request);

    @POST(WebConstants.URL_GET_DOCTOR_LIST)
    Observable<GetDoctorListApiResponse> apiGetDoctorList(@Body GetDoctorListRequest request);

    @POST(WebConstants.URL_BOOK_APPOINTMENT)
    Observable<CommonApiResponse> apiBookAppointment(@Body BookAppointmentModel request);

    @GET(WebConstants.URL_DOCTOR_APPOINTMENT_LIST)
    Observable<GetAppointmentListApiResponse> apiGetAppointmentList(@Query(WebConstants.PARAM_DOCUID) String id);

    @POST(WebConstants.URL_DOCTOR_ACCEPT_REJECT_APP)
    Observable<CommonApiResponse> apiAcceptReject(@Body AcceptRejectApiRequest request);

    @GET(WebConstants.URL_PATIENT_APPOINTMENT_LIST)
    Observable<GetAppointmentListApiResponse> apiGetPatientAppointmentList(@Query(WebConstants.PARAM_PATUID) String id);

    @POST(WebConstants.URL_PATIENT_ACCEPT_REJECT_LIST)
    Observable<CommonApiResponse> apiAcceptRejectTime(@Body AcceptRejectApiRequest request);

    @POST(WebConstants.URL_ADD_RATING)
    Observable<CommonApiResponse> apiAddRating(@Body AddRatingRequest request);

    @GET(WebConstants.URL_DOCTOR_GET_RATINGS)
    Observable<GetMyRatingsApiResponse> apiGetMyRatings(@Query(WebConstants.PARAM_DOCUID) String id);

    @POST(WebConstants.URL_EDIT_PATIENT_PROFILE)
    Observable<CommonApiResponse> apiEditProfile(@Body PatientProfileModel request);

    @GET(WebConstants.URL_GET_PATIENT_PROFILE)
    Observable<GetPatientProfileApiResponse> apiGetPatientProfile(@Query(WebConstants.PARAM_PUID) String id);

    @POST(WebConstants.URL_GET_PATIENT_RATING)
    Observable<GetPatientRatingApiResponse> apiGetPatientRating(@Body GetPatientRatingListRequest request);

}
