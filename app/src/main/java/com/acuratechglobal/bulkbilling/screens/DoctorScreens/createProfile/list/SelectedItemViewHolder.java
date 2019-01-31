package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class SelectedItemViewHolder extends RecyclerView.ViewHolder {
    View view;

    TextView tvName;

    public SelectedItemViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        tvName= view.findViewById(R.id.tvName);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(SpecializationModel model) {

        tvName.setText(TextUtils.isEmpty(model.getName()) ? "missing name" : model.getName());

    }

}
