package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.core;

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
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.list.SelectedDaysAdapter;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.reactivex.Observable;
import kotlin.Unit;

public class MyProfileView {

    private View view;
    private TextView tvClinicName,tvClinicAddress,tvExperience, tvQualifications;
    private TextView tvOpenTime, tvCloseTime;
    private TextView tvName, tvPhone, tvEmail;
    private RecyclerView recyclerDays;
    private ImageView imgProfile,star1,star2,star3,star4,star5;
    private ImageButton btnMenu;
    private Button btnEdit;
    private final MainActivity activity;
    UserData data;
    DoctorProfileModel profileData ;
    private ProgressDialog progressDialog;

    public MyProfileView(MainActivity context, SharedPrefsUtil prefs) {
        this.activity= context;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_fragment_my_profile, parent, true);
        tvClinicName= view.findViewById(R.id.tvClinicName);
        tvClinicAddress= view.findViewById(R.id.tvClinicAddress);
        tvExperience= view.findViewById(R.id.tvExperience);
        tvQualifications= view.findViewById(R.id.tvQualifications);
        tvOpenTime= view.findViewById(R.id.tvOpenTime);
        tvCloseTime= view.findViewById(R.id.tvCloseTime);
        tvName= view.findViewById(R.id.tvName);
        tvPhone= view.findViewById(R.id.tvPhone);
        tvEmail= view.findViewById(R.id.tvEmail);
        recyclerDays= view.findViewById(R.id.recyclerDays);
        imgProfile= view.findViewById(R.id.imgProfile);
        btnMenu= view.findViewById(R.id.btnMenu);
        btnEdit= view.findViewById(R.id.btnEdit);
        star1= view.findViewById(R.id.star1);
        star2= view.findViewById(R.id.star2);
        star3= view.findViewById(R.id.star3);
        star4= view.findViewById(R.id.star4);
        star5= view.findViewById(R.id.star5);

        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class); }
    }

    String getDocId(){
        return data.getDocUID();
    }

    void bindViews(DoctorProfileModel data){
        this.profileData = data;
        tvName.setText(profileData.getFirstName() + " " + profileData.getLastName());
        tvEmail.setText(profileData.getEmail());
        if (profileData.getPhone()!=null)
            tvPhone.setText(profileData.getPhone());
        if (profileData.getCloseTime()!=null)
            tvCloseTime.setText(profileData.getCloseTime());
        if (profileData.getOpenTime()!=null)
            tvOpenTime.setText(profileData.getOpenTime());
        if (profileData.getClinicName()!=null)
            tvClinicName.setText(profileData.getClinicName());
        if (profileData.getClinicAddress()!=null)
            tvClinicAddress.setText(profileData.getClinicAddress());
        if (profileData.getQualifications()!=null && profileData.getQualifications().size()>0){
            String strQalf="";
            for (int i=0; i<profileData.getQualifications().size() ;++i) {
                strQalf+=profileData.getQualifications().get(i).getName();
                if (i!=profileData.getQualifications().size()-1){
                    strQalf+=" ,";
                }
            }
            tvQualifications.setText(strQalf);
//            setQualificationAdapter(selectedQualifications);
        }

        if (profileData.getsPhotoPath()!=null){
            Glide.with(activity).load(Uri.parse(profileData.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        }
        if (profileData.getExperience()!=null){
            setExperience(profileData.getExperience());
        }
        if (profileData.getRating()!=null){
            setRatingStars(profileData.getRating());
        }else
            setRatingStars(0);

//            toggleRdBtns(profileData.getExperience());
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

    void setDaysAdapter(List<String> selectedDaysList) {
        recyclerDays.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        SelectedDaysAdapter selectedDaysAdapter= new SelectedDaysAdapter();
        recyclerDays.setAdapter(selectedDaysAdapter);
        selectedDaysAdapter.setAdapterList(selectedDaysList);
    }

    private void setRatingStars(int rating){
        switch (rating){
            case 0:
                star1.setImageResource(R.drawable.info_star_select);
                star2.setImageResource(R.drawable.info_star_select);
                star3.setImageResource(R.drawable.info_star_select);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                break;
            case 1:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_select);
                star3.setImageResource(R.drawable.info_star_select);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                break;
            case 2:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_select);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                break;
            case 3:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_selected);
                star4.setImageResource(R.drawable.info_star_select);
                star5.setImageResource(R.drawable.info_star_select);
                break;
            case 4:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_selected);
                star4.setImageResource(R.drawable.info_star_selected);
                star5.setImageResource(R.drawable.info_star_select);
                break;
            case 5:
                star1.setImageResource(R.drawable.info_star_selected);
                star2.setImageResource(R.drawable.info_star_selected);
                star3.setImageResource(R.drawable.info_star_selected);
                star4.setImageResource(R.drawable.info_star_selected);
                star5.setImageResource(R.drawable.info_star_selected);
                break;

        }
    }
    public View getView() {
        return this.view;
    }

    void setProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    Observable<Unit> btnMenu() {
        return RxView.clicks(btnMenu);
    }

    Observable<Unit> btnEdit() {
        return RxView.clicks(btnEdit);
    }

}
