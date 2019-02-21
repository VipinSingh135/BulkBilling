package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.list;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;

import java.text.ParseException;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

class DocAppointmentsAdapterViewHolder extends RecyclerView.ViewHolder {
    private View view;

    private TextView tvName;
    private TextView tvDay,tvDate,tvTime,tvMonth;
    private TextView tvDescription;
    private TextView tvViewDetail;
    private Button btnSuggest,btnAccept,btnReject;

    DocAppointmentsAdapterViewHolder(View itemView, PublishSubject<Integer> detailClick) {
        super(itemView);
        this.view = itemView;
        tvName = view.findViewById(R.id.tvName);
        tvDay = view.findViewById(R.id.tvDay);
        tvDate = view.findViewById(R.id.tvDate);
        tvTime = view.findViewById(R.id.tvTime);
        tvMonth = view.findViewById(R.id.tvMonth);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvViewDetail = view.findViewById(R.id.tvViewDetail);
        btnSuggest = view.findViewById(R.id.btnSuggest);
        btnAccept = view.findViewById(R.id.btnAccept);
        btnReject = view.findViewById(R.id.btnReject);

        btnAccept.setVisibility(View.GONE);
        btnReject.setVisibility(View.GONE);
        btnSuggest.setVisibility(View.GONE);
        tvViewDetail.setOnClickListener(v -> detailClick.onNext(getAdapterPosition()));

    }

    void bind(BookAppointmentModel model, String s) {

        tvName.setText(TextUtils.isEmpty(model.getFkPatientName()) ? "missing name" : model.getFkPatientName());
        tvDescription.setText(TextUtils.isEmpty(model.getIssue()) ? "missing name" : model.getIssue());
        tvTime.setText(TextUtils.isEmpty(s) ? "missing name" : s);

        try {
            String dayOfTheWeek = (String) DateFormat.format("EEEE", TimeUtils.stringToDate(model.getDate())); // Thursday
            String day          = (String) DateFormat.format("dd",   TimeUtils.stringToDate(model.getDate())); // 20
            String monthString  = (String) DateFormat.format("MMM",  TimeUtils.stringToDate(model.getDate())); // Jun
            String year         = (String) DateFormat.format("yyyy", TimeUtils.stringToDate(model.getDate())); // 2013

            tvDay.setText(dayOfTheWeek);
            tvDate.setText(day);
            tvMonth.setText(monthString+" "+year);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
