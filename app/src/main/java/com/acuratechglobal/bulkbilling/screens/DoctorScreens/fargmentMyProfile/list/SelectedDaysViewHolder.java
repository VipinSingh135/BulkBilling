package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.list;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class SelectedDaysViewHolder extends RecyclerView.ViewHolder {
    View view;

    TextView tvName;

    public SelectedDaysViewHolder(View itemView ) {
        super(itemView);
        this.view = itemView;
        tvName = view.findViewById(R.id.tvName);
    }

    void bind(String model,Boolean isSelected) {
        if (isSelected) {
            tvName.setBackgroundResource(R.drawable.bg_curved_yellow);
            tvName.setTextColor(Color.parseColor("#4f4f4f"));
        }else {
            tvName.setBackgroundResource(R.drawable.bg_curved_grey_outline);
            tvName.setTextColor(Color.parseColor("#b0b0b0"));
        }
        tvName.setText(TextUtils.isEmpty(model) ? "missing name" : model);
    }

}