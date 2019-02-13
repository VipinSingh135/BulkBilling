package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.api.response.CommonApiResponse;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.BookAppointmentActivity;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import io.reactivex.Observable;

public class AppointmentModel {
    private static final int REQUEST_PLACE_PICKER = 6;
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final BookAppointmentActivity activity;
    private final Api apis;


    UserData data;
    SharedPrefsUtil prefs;
    public AppointmentModel(BookAppointmentActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;


        this.prefs= prefs;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

    void finish() {
        activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<CommonApiResponse> performBookAppointment(BookAppointmentModel request) {
        return  apis.apiBookAppointment(request);
    }
}
