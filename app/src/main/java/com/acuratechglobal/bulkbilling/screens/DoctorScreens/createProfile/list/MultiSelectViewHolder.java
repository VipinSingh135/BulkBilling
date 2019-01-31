package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class MultiSelectViewHolder extends RecyclerView.ViewHolder {
    View view;

    TextView tvName;
    ImageView imgCheck;

    public MultiSelectViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        tvName= view.findViewById(R.id.tvName);
        imgCheck= view.findViewById(R.id.imgCheck);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(SpecializationModel model,boolean isSelected) {
//        Glide.with(view.getContext()).load(hero.getImage()).into(imageHero);
        tvName.setText(TextUtils.isEmpty(model.getName()) ? "missing name" : model.getName());

        if (isSelected){
            imgCheck.setImageResource(R.drawable.radio_on);
        }else{
            imgCheck.setImageResource(R.drawable.radio_off);
        }
    }
}