package com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.AddRatingRequest;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.AddRatingActivity;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import kotlin.Unit;

public class AddRatingView {

    private View view;
    private ImageButton btnBack;
    private Button btnSubmit,btnRateUs;
    private ImageView imgProfile,star1,star2,star3,star4,star5;
    private TextView tvName,tvRatingText,tvContactNumber,tvQualifications,tvSpecialization ;
    private final AddRatingActivity activity;
    private final ProgressDialog progressDialog;
    private int docId,patId,appointmentId,rating;
    private UserData userData;

    public AddRatingView(AddRatingActivity context, ProgressDialog dialog, SharedPrefsUtil prefs) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_activity_add_ratings, parent, true);
        btnBack= view.findViewById(R.id.btnBack);
        btnRateUs= view.findViewById(R.id.btnRateUs);
        btnSubmit= view.findViewById(R.id.btnSubmit);
        imgProfile= view.findViewById(R.id.imgProfile);
        star1= view.findViewById(R.id.star1);
        star2= view.findViewById(R.id.star2);
        star3= view.findViewById(R.id.star3);
        star4= view.findViewById(R.id.star4);
        star5= view.findViewById(R.id.star5);
        tvName= view.findViewById(R.id.tvName);
        tvRatingText= view.findViewById(R.id.tvRatingText);
//        tvContactNumber= view.findViewById(R.id.tvContactNumber);
        tvQualifications= view.findViewById(R.id.tvQualifications);
        tvSpecialization= view.findViewById(R.id.tvSpecialization);

        userData= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        patId= userData.getPatID();
        setRatingStars(5);
    }

    public View getView() {
        return this.view;
    }


    public void bindData(Bundle extras) {
        if (extras!=null){
            BookAppointmentModel data= new Gson().fromJson(extras.getString("data"),BookAppointmentModel.class);
            docId=data.getFkDoctorId();
            appointmentId=data.getId();
            tvName.setText(data.getFkDoctorName());
            Glide.with(activity).load(data.getDoctorPhotoPath()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);

            if (data.getQualifications()!=null && data.getQualifications().size()>0){
                String strQalf="";
                for (int i=0; i<data.getQualifications().size() ;++i) {
                    strQalf+=data.getQualifications().get(i).getName();
                    if (i!=data.getQualifications().size()-1){
                        strQalf+=" ,";
                    }
                }
                tvQualifications.setText(strQalf);
            }else {
                tvQualifications.setText("Qualification not mentioned");
            }

            if (data.getDoctorSpecialization()!=null && data.getDoctorSpecialization().size()>0){
                String strSpec="";
                for (int i=0; i<data.getDoctorSpecialization().size() ;++i) {
                    strSpec+=data.getDoctorSpecialization().get(i).getName();
                    if (i!=data.getDoctorSpecialization().size()-1){
                        strSpec+=" ,";
                    }
                }
                tvSpecialization.setText("Dr. "+data.getFkDoctorName()+" is specialized in "+strSpec);
            }else {
                tvSpecialization.setText("Specialization not mentioned");
            }

        }
    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    Observable<Unit> backClick() {
        return RxView.clicks(btnBack);
    }

    Observable<Unit> star1() {
        return RxView.clicks(star1);
    }
    Observable<Unit> star2() {
        return RxView.clicks(star2);
    }
    Observable<Unit> star3() {
        return RxView.clicks(star3);
    }
    Observable<Unit> star4() {
        return RxView.clicks(star4);
    }
    Observable<Unit> star5() {
        return RxView.clicks(star5);
    }

    Observable<Unit> submitClicked() {
        return RxView.clicks(btnSubmit);
    }

    AddRatingRequest getParams(){
        AddRatingRequest request= new AddRatingRequest();
        request.setComments("");
        request.setFkDoctorId(docId);
        request.setFkPatientId(patId);
        request.setFkAppointmentId(appointmentId);
        request.setRate(rating);
        return request;
    }
    void setRatingStars(int rating){
        switch (rating){
            case 1:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_select);
                star3.setImageResource(R.drawable.info_star_select);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                this.rating=1;
                tvRatingText.setText("Not Satisfied");
                tvRatingText.setTextColor(activity.getResources().getColor(R.color.colorRed));
                break;
            case 2:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_select);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                this.rating=2;
                tvRatingText.setText("Okay");
                tvRatingText.setTextColor(activity.getResources().getColor(R.color.colorYellow));
                break;
            case 3:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_selected);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                tvRatingText.setText("Satisfactory");
                tvRatingText.setTextColor(activity.getResources().getColor(R.color.colorYellow));
                this.rating=3;
                break;
            case 4:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_selected);
                star4.setImageResource(R.drawable.info_star_selected);
                star5.setImageResource(R.drawable.info_star_select);
                tvRatingText.setText("Good");
                tvRatingText.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
                this.rating=4;
                break;
            case 5:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_selected);
                star4.setImageResource(R.drawable.info_star_selected);
                star5.setImageResource(R.drawable.info_star_selected);
                tvRatingText.setText("Excellent");
                tvRatingText.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
                this.rating=5;
                break;

        }
    }

    public void showToast(String message) {
        Toasty.success(activity,message).show();
    }
}
