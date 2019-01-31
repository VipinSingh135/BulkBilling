package com.acuratechglobal.bulkbilling.api;

public interface WebConstants {

    String ACTION_BASE_URL = "http://apibulkbilling.acumobi.com/";
//    String ACTION_FCM_PUSH = "https://fcm.googleapis.com/fcm/";
//    String IMAGE_BASE_URL = "http://www.acuratechglobal.com/joinme/img/users/";
//    String IMAGE_EVENT_URL = "http://www.acuratechglobal.com/joinme/img/events/";

    //fcm sever key
    String FCM_SERVER_KEY = "key= AAAASzpV4js:APA91bHu2XuCr0C6b0KEoFo4P9DzjlTmmh6Ivpe3z-O6vsL9831uhObtObIAz0uiwl7UqBkEkGJuk1G8SxcWp1dt5AOK0lEpn-5BloBvh2oJIDVwKExkCSeTjd8wjHZRraG9C8V591U2";

    //google place Api
    int RETROFIT_SUCCESS = 1;
    String URL_SIGNUP = "api/Account/RegisterStep1";
    String URL_LOGIN = "api/Account/login";
    String URL_LOGIN_FB = "api/Account/FbRegister";
    String URL_UPDATE_PROFILE = "update_profile";
    String URL_FORGOT_PASSWORD = "api/Account/forgotpassword";
    String URL_SET_PASSWORD = "api/Account/SignUpStep3";
    String URL_SET_PLAN = "api/Account/SignUpStep4";
    String URL_RESET_PASSWORD = "api/Account/ResetPassword";
    String URL_CHANGE_PASSWORD = "api/Account/changepassword";
    String URL_LOGOUT = "logout";

    String URL_SPECIALIZATION_LEVEL1 = "api/specialization/getspecializationlevel1";
    String URL_SPECIALIZATION_LEVEL2 = "api/specialization/getspecializationlevel2";
    String URL_SPECIALIZATION_LEVEL3 = "api/specialization/getspecializationlevel3";

    String URL_QUALIFICATION_LIST = "api/Qualification/getQualificationList";

    String URL_CREATE_PROFILE = "api/Doctor/updateDoctorProfile";

    String URL_GET_PROFILE = "api/Doctor/getDoctorProfile";

    String PARAM_LEVEL1UID= "lvl1UID";
    String PARAM_LEVEL2UID= "lvl2UID";
    String PARAM_DOC2UID= "docUID";

}
