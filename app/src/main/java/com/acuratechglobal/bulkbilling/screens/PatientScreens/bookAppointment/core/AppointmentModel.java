package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core;

import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.request.CreateProfileApiRequest;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.DoctorAvailabilityModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;
import com.acuratechglobal.bulkbilling.utils.ImageUtils;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import io.reactivex.Observable;

import static com.acuratechglobal.bulkbilling.utils.ImageUtils.REQUEST_GALLERY;

public class DoctorProfileModel {
    private static final int REQUEST_PLACE_PICKER = 6;
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final DoctorProfileActivity activity;
    private final Api apis;

    private List<String> daysList=new ArrayList<>();

    UserData data;
    SharedPrefsUtil prefs;
    public DoctorProfileModel(DoctorProfileActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        daysList.clear();
        daysList.add("Sunday");
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");
        daysList.add("Saturday");

        this.prefs= prefs;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

    void finish() {
        activity.finish();
    }
}
