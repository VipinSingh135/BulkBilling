package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.SettingsFragment;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;

public class SettingsModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final SettingsFragment fragment;
    private final Api apis;

    public SettingsModel(SettingsFragment fragment, Api api) {
        this.fragment = fragment;
        this.apis = api;
    }

    void openDrawer() {
       MainActivity activity= (MainActivity)fragment.getActivity();
       activity.OpenDrawer();
    }

    void gotoChangePass() {
        Intent intent= new Intent(fragment.getActivity(), ChangePassActivity.class);
        fragment.startActivity(intent);
    }

    void gotoNotification() {
//        Intent intent= new Intent(fragment.getActivity(), ForgotPassActivity.class);
//        fragment.startActivity(intent);
    }

    void gotoMyProfile() {
        Intent intent = new Intent(fragment.getContext(), CreateProfileActivity.class);
        intent.putExtra("isUpdate",true);
        fragment.getContext().startActivity(intent);
    }

    void gotoUpdatePayment() {
    }
}
