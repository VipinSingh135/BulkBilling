package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.list;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.models.PatientRatingModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class PatRatingAdapterViewHolder extends RecyclerView.ViewHolder {
    View view;
    Context context;
    TextView tvName;
    ImageView star1,star2,star3,star4,star5;
    ImageView imgProfile;

    public PatRatingAdapterViewHolder(View itemView, PublishSubject<Integer> clickSubject, Context context) {
        super(itemView);
        this.context=context;
        this.view = itemView;
        imgProfile = itemView.findViewById(R.id.imgProfile);
        star1 = itemView.findViewById(R.id.star1);
        star2 = itemView.findViewById(R.id.star2);
        star3 = itemView.findViewById(R.id.star3);
        star4 = itemView.findViewById(R.id.star4);
        star5 = itemView.findViewById(R.id.star5);
        tvName = itemView.findViewById(R.id.tvName);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(PatientRatingModel model) {

        Glide.with(context).load(model.getDocPhoto()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        
        tvName.setText("Dr. "+ model.getDocName());
       
        if (model.getRate()!=null){
            setRatingStars(model.getRate());
        }else {
            setRatingStars(0);
        }
    }

    private void setRatingStars(int rating) {
        switch (rating) {
            case 0:
                star1.setImageResource(R.drawable.star_small_normal);
                star2.setImageResource(R.drawable.star_small_normal);
                star3.setImageResource(R.drawable.star_small_normal);
                star4.setImageResource(R.drawable.star_small_normal);
                star5.setImageResource(R.drawable.star_small_normal);
                break;
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
