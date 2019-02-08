package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.list;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class SelectedDaysViewHolder extends RecyclerView.ViewHolder {
    View view;

    TextView tvName;

    public SelectedDaysViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        tvName = view.findViewById(R.id.tvName);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(String model) {
        tvName.setText(TextUtils.isEmpty(model) ? "missing name" : model);
    }
}