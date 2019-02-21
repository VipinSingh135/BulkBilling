package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core;

import android.app.ProgressDialog;
import android.net.Uri;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.DoctorRatingModel;
import com.acuratechglobal.bulkbilling.models.RatingDataModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.list.AdapterDocMyRatings;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

public class DocRatingsView {

    private View view;
    ImageButton btnMenu;
    RecyclerView recyclerRatings;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private TextView tvName, tvPhone, tvEmail;
    private TextView tv5,tv4,tv3,tv2,tv1;
    private View view1,view2,view3,view4,view5;
    private ImageView imgProfile,star1,star2,star3,star4,star5;
    UserData userData;
    AdapterDocMyRatings adapter;

    public DocRatingsView(MainActivity context, ProgressDialog dialog, SharedPrefsUtil prefs) {
        this.activity= context;
        this.progressDialog= dialog;
        if (prefs!=null){
            userData= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_fragment_my_ratings, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        tvName= view.findViewById(R.id.tvName);
        tvPhone= view.findViewById(R.id.tvPhone);
        tvEmail= view.findViewById(R.id.tvEmail);
        imgProfile= view.findViewById(R.id.imgProfile);
        recyclerRatings= view.findViewById(R.id.recyclerRatings);
        star1 = view.findViewById(R.id.star1);
        star2 = view.findViewById(R.id.star2);
        star3 = view.findViewById(R.id.star3);
        star4 = view.findViewById(R.id.star4);
        star5 = view.findViewById(R.id.star5);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);
        view1 = view.findViewById(R.id.view1);
        view2 = view.findViewById(R.id.view2);
        view3 = view.findViewById(R.id.view3);
        view4 = view.findViewById(R.id.view4);
        view5 = view.findViewById(R.id.view5);

        recyclerRatings.setLayoutManager(new LinearLayoutManager(activity));
        adapter=new AdapterDocMyRatings();
        recyclerRatings.setAdapter(adapter);

        if (userData!=null) {
            tvName.setText(userData.getFirstName() + " " + userData.getLastName());
            tvEmail.setText(userData.getEmail());
//            tvEmail.setText(userData.getPhone());
            if (userData.getPhone() != null)
                tvPhone.setText(userData.getPhone());
            if (userData.getsPhotoPath() != null) {
                Glide.with(activity).load(Uri.parse(userData.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
            }
        }
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

    @Nullable
    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }

    public void setAdapterList(List<RatingDataModel> list) {
        adapter.notifyAdapter(list);
    }

    void bindRatings(DoctorRatingModel object) {


        if (object!=null) {
            setRatingStars(object.getAvgRate());
            tv1.setText(object.getOneStar() + " people");
            tv2.setText(object.getTwoStar() + " people");
            tv3.setText(object.getThreeStar() + " people");
            tv4.setText(object.getFourStar() + " people");
            tv5.setText(object.getFiveStar() + " people");

//        Display display = activity.getWindowManager().getDefaultDisplay();
            int width = UiUtils.dpToPx(view5.getWidth());

            int totalRange = object.getTotalPeople();
            int rangeBlocks = width / totalRange;

            int w1 = rangeBlocks * object.getOneStar();
            int w2 = rangeBlocks * object.getTwoStar();
            int w3 = rangeBlocks * object.getThreeStar();
            int w4 = rangeBlocks * object.getFourStar();
            int w5 = rangeBlocks * object.getFiveStar();

            view1.setLayoutParams(new LinearLayout.LayoutParams(UiUtils.pxToDp(w1), 15));
            view2.setLayoutParams(new LinearLayout.LayoutParams(UiUtils.pxToDp(w2), 15));
            view3.setLayoutParams(new LinearLayout.LayoutParams(UiUtils.pxToDp(w3), 15));
            view4.setLayoutParams(new LinearLayout.LayoutParams(UiUtils.pxToDp(w4), 15));
            view5.setLayoutParams(new LinearLayout.LayoutParams(UiUtils.pxToDp(w5), 15));
        }else{
            setRatingStars(0);
            tv1.setText(0 + " people");
            tv2.setText(0 + " people");
            tv3.setText(0 + " people");
            tv4.setText(0 + " people");
            tv5.setText(0 + " people");

            view1.setLayoutParams(new LinearLayout.LayoutParams(0, 15));
            view2.setLayoutParams(new LinearLayout.LayoutParams(0, 15));
            view3.setLayoutParams(new LinearLayout.LayoutParams(0, 15));
            view4.setLayoutParams(new LinearLayout.LayoutParams(0, 15));
            view5.setLayoutParams(new LinearLayout.LayoutParams(0, 15));
        }
        tv1.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
        view3.setVisibility(View.VISIBLE);
        tv4.setVisibility(View.VISIBLE);
        view4.setVisibility(View.VISIBLE);
        tv5.setVisibility(View.VISIBLE);
        view5.setVisibility(View.VISIBLE);

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

}
