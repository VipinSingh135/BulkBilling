package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.SettingsFragment;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;

public class SettingsView {

    private View view;
    private TextView tvChangePassword;
    private TextView tvProfileSetting;
    private TextView tvNotifySetting;
    private TextView tvUpdatePayment;
    private View view1;
    private TextView tvUpgradeSubscription;
    private ImageButton btnMenu;

    private final SettingsFragment fragment;
    UserData data;

    public SettingsView(SettingsFragment context, SharedPrefsUtil prefs) {
        this.fragment= context;

        FrameLayout parent = new FrameLayout(fragment.getActivity());
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.doc_fragment_settings, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        tvChangePassword= view.findViewById(R.id.tvChangePassword);
        tvProfileSetting= view.findViewById(R.id.tvProfileSetting);
        tvNotifySetting= view.findViewById(R.id.tvNotifySetting);
        tvUpdatePayment= view.findViewById(R.id.tvUpdatePayment);
        tvUpgradeSubscription= view.findViewById(R.id.tvUpgradeSubscription);
        view1= view.findViewById(R.id.view1);
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
        if (data.getUserType()==3){
            tvProfileSetting.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
            tvUpdatePayment.setVisibility(View.GONE);
            tvUpgradeSubscription.setVisibility(View.GONE);
        }
    }


    public View getView() {
        return this.view;
    }


    Observable<Unit> btnMenu() {
        return RxView.clicks(btnMenu);
    }


    Observable<Unit> changePassClicked() {
        return RxView.clicks(tvChangePassword);
    }


    Observable<Unit> profileClicked() {
        return RxView.clicks(tvProfileSetting);
    }


    Observable<Unit> updateSubsClicked() {
        return RxView.clicks(tvUpgradeSubscription);
    }


    Observable<Unit> notifyClicked() {
        return RxView.clicks(tvNotifySetting);
    }

    Observable<Unit> updatePaymentClicked() {
        return RxView.clicks(tvUpdatePayment);
    }

}
