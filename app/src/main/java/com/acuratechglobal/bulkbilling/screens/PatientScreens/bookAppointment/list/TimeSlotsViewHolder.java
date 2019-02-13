package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.list;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

class TimeSlotsViewHolder extends RecyclerView.ViewHolder {
    private View view;

    private TextView tvName;

    TimeSlotsViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        tvName = view.findViewById(R.id.tvName);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(String model,boolean isSelected) {
        tvName.setText(TextUtils.isEmpty(model) ? "missing name" : model);

        if (isSelected) {
            tvName.setBackgroundResource(R.drawable.bg_curved_grey);
            tvName.setTextColor(Color.parseColor("#ffffff"));
        }else {
            tvName.setBackgroundResource(R.drawable.bg_curved_grey_outline);
            tvName.setTextColor(Color.parseColor("#6c6c6c"));
        }
    }
}