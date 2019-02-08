package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.list;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.CreateProfileApiRequest;
import com.acuratechglobal.bulkbilling.models.SideMenuListModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class DocHomeAdapterViewHolder extends RecyclerView.ViewHolder {
    View view;
    Context context;
    TextView tvExperience,tvAddress,tvName,tvQualifications,tvSpecialization;
    ImageView imgfav;
    ImageView imgProfile;

    public DocHomeAdapterViewHolder(View itemView, PublishSubject<Integer> clickSubject,Context context) {
        super(itemView);
        this.context=context;
        this.view = itemView;
        imgProfile = itemView.findViewById(R.id.imgProfile);
        imgfav = itemView.findViewById(R.id.imgFav);
        tvExperience = itemView.findViewById(R.id.tvExperience);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(CreateProfileApiRequest model) {

        Glide.with(context).load(model.getsPhotoPath()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        imgfav.setVisibility(View.INVISIBLE);
        tvExperience.setVisibility(View.VISIBLE);



    }

}
