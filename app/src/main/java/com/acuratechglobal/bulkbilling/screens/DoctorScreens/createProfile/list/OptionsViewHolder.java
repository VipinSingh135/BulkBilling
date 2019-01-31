package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class OptionsViewHolder extends RecyclerView.ViewHolder {
    View view;

    TextView tvOptions;

    public OptionsViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        tvOptions= view.findViewById(R.id.tvOptions);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(SpecializationModel model) {
//        Glide.with(view.getContext()).load(hero.getImage()).into(imageHero);

        tvOptions.setText(TextUtils.isEmpty(model.getName()) ? "missing name" : model.getName());

    }

}
