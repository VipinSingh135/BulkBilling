package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.list;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.RatingDataModel;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class MyRatingsViewHolder extends RecyclerView.ViewHolder {
    private View itemView;

    private TextView tvName;
    private ImageView imgProfile,star1,star2,star3,star4,star5;

    public MyRatingsViewHolder(View view) {
        super(view);
        this.itemView = view;
        tvName = itemView.findViewById(R.id.tvName);
        imgProfile = itemView.findViewById(R.id.imgProfile);
        star1 = itemView.findViewById(R.id.star1);
        star2 = itemView.findViewById(R.id.star2);
        star3 = itemView.findViewById(R.id.star3);
        star4 = itemView.findViewById(R.id.star4);
        star5 = itemView.findViewById(R.id.star5);

    }


    public void bind(RatingDataModel ratingDataModel) {


        tvName.setText(ratingDataModel.getPatientName());
//        Glide.with(context).load(ratingDataModel.get()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);

        switch (ratingDataModel.getRate()){
            case 1:
                star1.setImageResource(R.drawable.star_small_selected);
                star2.setImageResource(R.drawable.star_small_normal);
                star3.setImageResource(R.drawable.star_small_normal);
                star4.setImageResource(R.drawable.star_small_normal);
                star5.setImageResource(R.drawable.star_small_normal);
                break;
            case 2:
                star1.setImageResource(R.drawable.star_small_selected);
                star2.setImageResource(R.drawable.star_small_selected);
                star3.setImageResource(R.drawable.star_small_normal);
                star4.setImageResource(R.drawable.star_small_normal);
                star5.setImageResource(R.drawable.star_small_normal);
                break;
            case 3:
                star1.setImageResource(R.drawable.star_small_selected);
                star2.setImageResource(R.drawable.star_small_selected);
                star3.setImageResource(R.drawable.star_small_selected);
                star4.setImageResource(R.drawable.star_small_normal);
                star5.setImageResource(R.drawable.star_small_normal);
                break;
            case 4:
                star1.setImageResource(R.drawable.star_small_selected);
                star2.setImageResource(R.drawable.star_small_selected);
                star3.setImageResource(R.drawable.star_small_selected);
                star4.setImageResource(R.drawable.star_small_selected);
                star5.setImageResource(R.drawable.star_small_normal);
                break;
            case 5:
                star1.setImageResource(R.drawable.star_small_selected);
                star2.setImageResource(R.drawable.star_small_selected);
                star3.setImageResource(R.drawable.star_small_selected);
                star4.setImageResource(R.drawable.star_small_selected);
                star5.setImageResource(R.drawable.star_small_selected);
                break;

        }
    }
}

