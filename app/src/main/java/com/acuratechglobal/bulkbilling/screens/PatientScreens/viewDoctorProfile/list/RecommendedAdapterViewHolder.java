package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.list;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class PatHomeAdapterViewHolder extends RecyclerView.ViewHolder {
    View view;
    Context context;
    TextView tvExperience,tvAddress,tvName,tvQualifications,tvSpecialization;
    ImageView imgfav;
    ImageView star1,star2,star3,star4,star5;
    ImageView imgProfile;

    public PatHomeAdapterViewHolder(View itemView, PublishSubject<Integer> clickSubject, Context context) {
        super(itemView);
        this.context=context;
        this.view = itemView;
        imgProfile = itemView.findViewById(R.id.imgProfile);
        imgfav = itemView.findViewById(R.id.imgFav);
        star1 = itemView.findViewById(R.id.star1);
        star2 = itemView.findViewById(R.id.star2);
        star3 = itemView.findViewById(R.id.star3);
        star4 = itemView.findViewById(R.id.star4);
        star5 = itemView.findViewById(R.id.star5);
        tvExperience = itemView.findViewById(R.id.tvExperience);
        tvAddress = itemView.findViewById(R.id.tvAddress);
        tvName = itemView.findViewById(R.id.tvName);
        tvQualifications = itemView.findViewById(R.id.tvQualifications);
        tvSpecialization = itemView.findViewById(R.id.tvSpecialization);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(DoctorProfileModel model) {

        Glide.with(context).load(model.getsPhotoPath()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        imgfav.setVisibility(View.INVISIBLE);
        tvExperience.setVisibility(View.VISIBLE);

        tvAddress.setText(model.getClinicAddress());

        if (model.getExperience()!=null){
            switch (model.getExperience()) {
                case 1:
                    tvExperience.setText("Exp:- <3Yrs");
                    break;
                case 2:
                    tvExperience.setText("Exp:- 3Yrs");
                    break;
                case 3:
                    tvExperience.setText("Exp:- 5Yrs");
                    break;
                case 4:
                    tvExperience.setText("Exp:- 10Yrs");
                    break;
                case 5:
                    tvExperience.setText("Exp:- 15Yrs");
                    break;
            }
        }

        tvName.setText("Dr. "+ model.getFirstName()+" "+model.getLastName());
        if (model.getQualifications()!=null) {
            String strQual = "";
            for (int i = 0; i < model.getQualifications().size(); ++i) {
                strQual += model.getQualifications().get(i).getName();
                if (i != model .getQualifications().size() - 1) {
                    strQual += ", ";
                }
            }
            tvQualifications.setText(strQual);
        }

        if (model.getDoctorSpecialization()!=null) {
            String strSpec = "";
            for (int i = 0; i < model.getDoctorSpecialization().size(); ++i) {
                strSpec += model.getDoctorSpecialization().get(i).getName();
                if (i != model.getDoctorSpecialization().size() - 1) {
                    strSpec += ", ";
                }
            }
            tvSpecialization.setText("Specialist in " + strSpec);
        }

        if (model.getRating()!=null){
            setRatingStars(model.getRating());
        }else {
            setRatingStars(0);
        }
    }

    private void setRatingStars(int rating) {
        switch (rating) {
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
