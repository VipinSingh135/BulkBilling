package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.core;

import android.app.ProgressDialog;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.models.DoctorAvailabilityModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;

public class DoctorProfileView {

    private View view;

    private TextView tvClinicAddress,tvClinicName,tvHeaderTitle, tvQualifications;
    private TextView tvAvailibility;
    private TextView tvName, tvPhone, tvEmail;
    private TextView tvSpecialty,tvExperience;
    private ImageView imgProfile,imgFav;
    private Button btnGetAppointment,btnRecommend;
    private ImageButton btnBack;
    private final DoctorProfileActivity activity;
    private ProgressDialog progressDialog;
  
    UserData data;

    public DoctorProfileView(DoctorProfileActivity context, SharedPrefsUtil prefs) {
        this.activity = context;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_activity_doc_profile_info, parent, true);

      
        tvSpecialty = view.findViewById(R.id.tvSpecialty);
        tvClinicName = view.findViewById(R.id.tvClinicName);
        tvClinicAddress = view.findViewById(R.id.tvClinicAddress);
        tvQualifications = view.findViewById(R.id.tvQualifications);
        tvExperience = view.findViewById(R.id.tvExperience);
        tvAvailibility = view.findViewById(R.id.tvAvailibility);
        tvName = view.findViewById(R.id.tvName);
        tvHeaderTitle = view.findViewById(R.id.tvTitle);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        imgProfile = view.findViewById(R.id.imgProfile);

        btnGetAppointment = view.findViewById(R.id.btnGetAppointment);
        btnRecommend = view.findViewById(R.id.btnRecommend);
        btnBack = view.findViewById(R.id.btnBack);
        imgFav = view.findViewById(R.id.imgFav);

    }

    void bindViews(DoctorProfileModel data){
       
        tvName.setText(data.getFirstName() + " " + data.getLastName());
        tvEmail.setText(data.getEmail());
        if (data.getPhone()!=null)
            tvPhone.setText(data.getPhone());
        if (data.getClinicName()!=null)
            tvClinicName.setText(data.getClinicName());
        if (data.getClinicAddress()!=null)
            tvClinicAddress.setText(data.getClinicAddress());
        if (data.getDoctorSpecialization()!=null && data.getDoctorSpecialization().size()>0){
            String strSpec="";
            for (int i=0; i<data.getDoctorSpecialization().size() ;++i) {
                strSpec+=data.getDoctorSpecialization().get(i).getName();
                if (i!=data.getDoctorSpecialization().size()-1){
                    strSpec+=" ,";
                }
            }
            tvQualifications.setText(strSpec);
        }
        if (data.getQualifications()!=null && data.getQualifications().size()>0){
            String strQalf="";
            for (int i=0; i<data.getQualifications().size() ;++i) {
                strQalf+=data.getQualifications().get(i).getName();
                if (i!=data.getQualifications().size()-1){
                    strQalf+=" ,";
                }
            }
            tvQualifications.setText(strQalf);
        }

        if (data.getsPhotoPath()!=null){
            Glide.with(activity).load(Uri.parse(data.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        }
        
        if (data.getExperience()!=null) {
            setExperience(data.getExperience());
        }
        if (data.getDoctorAvailability()!=null){
            setAvailibility(data.getDoctorAvailability());
        }
    }

    private void setAvailibility(DoctorAvailabilityModel doctorAvailability) {
        String strDays="";
        if (doctorAvailability.getSunday()){
            strDays+="Sun, ";
        }
        if (doctorAvailability.getMonday()){
            strDays+="Mon, ";
        }
        if (doctorAvailability.getTuesday()){
            strDays+="Tue, ";
        }
        if (doctorAvailability.getWednesday()){
            strDays+="Wed, ";
        }
        if (doctorAvailability.getThursday()){
            strDays+="Thu, ";
        }
        if (doctorAvailability.getFriday()){
            strDays+="Fri, ";
        }
        if (doctorAvailability.getSaturday()){
            strDays+="Sat";
        }
        
        if (strDays.endsWith(", ")){
            tvAvailibility.setText(strDays.substring(0,strDays.length()-2));
        }else
        tvAvailibility.setText(strDays);
    }

    private void setExperience(Integer experience) {
        switch (experience) {
            case 1:
                tvExperience.setText("Below 3 years");
                break;
            case 2:
                tvExperience.setText("Above 3 years");
                break;
            case 3:
                tvExperience.setText("Above 5 years");
                break;
            case 4:
                tvExperience.setText("Above 10 years");
                break;
            case 5:
                tvExperience.setText("Above 15 years");
                break;
        }
    }
  
    void setProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
    }

    public View getView() {
        return this.view;
    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    Observable<Unit> appointmentClick() {
        return RxView.clicks(btnGetAppointment);
    }

  
    Observable<Unit> favClick() {
        return RxView.clicks(imgFav);
    }

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }
    
    Observable<Unit> reccomend() {
        return RxView.clicks(btnRecommend);
    }
    

}
