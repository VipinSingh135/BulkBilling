package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.list;

import android.graphics.Color;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;

import java.text.ParseException;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class PatAppointmentAdapterViewHolder extends RecyclerView.ViewHolder {
    private View view;

    private TextView tvName;
    private TextView tvDay,tvDate,tvStatus,tvTime,tvMonth;
    private TextView tvDescription,tvNewDateTime;
    private TextView tvViewDetail;
    private Button btnOkay;
    private LinearLayout linearStatus;
    private RelativeLayout relativeSuggest;

    PatAppointmentAdapterViewHolder(View itemView, PublishSubject<Integer> detailClick, PublishSubject<Integer> confirmClick) {
        super(itemView);
        this.view = itemView;
        tvName = view.findViewById(R.id.tvName);
        tvDay = view.findViewById(R.id.tvDay);
        tvDate = view.findViewById(R.id.tvDate);
        tvTime = view.findViewById(R.id.tvTime);
        tvMonth = view.findViewById(R.id.tvMonth);
        tvNewDateTime = view.findViewById(R.id.tvNewDateTime);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvNewDateTime = view.findViewById(R.id.tvNewDateTime);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvViewDetail = view.findViewById(R.id.tvViewDetail);
        btnOkay = view.findViewById(R.id.btnOkay);

        relativeSuggest= view.findViewById(R.id.relativeSuggest);
        tvStatus= view.findViewById(R.id.tvStatus);
        linearStatus= view.findViewById(R.id.linearStatus);

        tvViewDetail.setOnClickListener(v -> detailClick.onNext(getAdapterPosition()));
        btnOkay.setOnClickListener(v -> confirmClick.onNext(getAdapterPosition()));

    }

    void bind(BookAppointmentModel model, String s)  {

        tvName.setText(TextUtils.isEmpty(model.getFkDoctorName()) ? "missing name" : model.getFkDoctorName());
        tvDescription.setText(TextUtils.isEmpty(model.getIssue()) ? "missing description" : model.getIssue());
        tvTime.setText(TextUtils.isEmpty(s) ? "missing time" : s);

        try {
            String dayOfTheWeek;
            String day;
            String monthString;
            String year;

            if(model.getSuggested()!=null&&model.getConfirmed()!=null&&model.getSuggested()&&model.getConfirmed()&&model.getStatus()==1){
                 dayOfTheWeek = (String) DateFormat.format("EEEE", TimeUtils.stringToDate(model.getSuggestedOn())); // Thursday
                 day          = (String) DateFormat.format("dd",   TimeUtils.stringToDate(model.getSuggestedOn())); // 20
                 monthString  = (String) DateFormat.format("MMM",  TimeUtils.stringToDate(model.getSuggestedOn())); // Jun
                 year         = (String) DateFormat.format("yyyy", TimeUtils.stringToDate(model.getSuggestedOn())); // 2013

            }else{
                 dayOfTheWeek = (String) DateFormat.format("EEEE", TimeUtils.stringToDate(model.getDate())); // Thursday
                 day          = (String) DateFormat.format("dd",   TimeUtils.stringToDate(model.getDate())); // 20
                 monthString  = (String) DateFormat.format("MMM",  TimeUtils.stringToDate(model.getDate())); // Jun
                 year         = (String) DateFormat.format("yyyy", TimeUtils.stringToDate(model.getDate())); // 2013

            }
            tvDay.setText(dayOfTheWeek);
            tvDate.setText(day);
            tvMonth.setText(monthString+" "+year);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (model.getStatus()==null || model.getStatus()==0){
            linearStatus.setVisibility(View.VISIBLE);
            relativeSuggest.setVisibility(View.GONE);
            tvStatus.setText("Pending");
            tvStatus.setTextColor(Color.parseColor("#375ee2"));
        }else if (model.getStatus()==1){
            linearStatus.setVisibility(View.VISIBLE);
            relativeSuggest.setVisibility(View.GONE);
            tvStatus.setText("Confirmed");
            tvStatus.setTextColor(Color.parseColor("#11983c"));
        }else if (model.getStatus()==2){
            linearStatus.setVisibility(View.VISIBLE);
            relativeSuggest.setVisibility(View.GONE);
            tvStatus.setText("Rejected");
            tvStatus.setTextColor(Color.parseColor("#e76060"));
        }else if (model.getStatus()==4){
            linearStatus.setVisibility(View.VISIBLE);
            relativeSuggest.setVisibility(View.GONE);
            tvStatus.setText("Canceled");
            tvStatus.setTextColor(Color.parseColor("#e76060"));
        }else{
            linearStatus.setVisibility(View.GONE);
            relativeSuggest.setVisibility(View.VISIBLE);
            try {
                tvNewDateTime.setText(TimeUtils.changeDateTimeFormat(model.getSuggestedOn()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}
