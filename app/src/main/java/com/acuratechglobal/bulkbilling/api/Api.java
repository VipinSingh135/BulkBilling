package com.acuratechglobal.bulkbilling.api;

import com.acuratechglobal.bulkbilling.api.request.ChangePassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.CreateProfileApiRequest;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.api.request.LoginFbApiRequest;
import com.acuratechglobal.bulkbilling.api.request.ResetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SendOtpRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPassApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SetPlanApiRequest;
import com.acuratechglobal.bulkbilling.api.request.SignUpApiRequest;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.api.response.ResetPassApiResponse;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;

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
    Observable<LoginApiResponse> apiCreateProfile(@Body CreateProfileApiRequest request);

    @GET(WebConstants.URL_GET_PROFILE)
    Observable<GetProfileApiResponse> apiGetProfile(@Query(WebConstants.PARAM_DOC2UID) String id);

    @POST(WebConstants.URL_CHANGE_PASSWORD)
    Observable<CommonApiResponse> apiChangePassword(@Body ChangePassApiRequest request);


}
