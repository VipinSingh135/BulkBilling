package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.core;

import android.app.ProgressDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.list.AdapterPatAppointment;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import kotlin.Unit;

public class PatAppiontmentView {
    private View view;
    private ImageButton btnMenu;
    private RelativeLayout relativeDetail;
    private RecyclerView recyclerAppointments;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private AdapterPatAppointment adapter;
    private List<BookAppointmentModel> list=new ArrayList<>();
    private Button btnClose;
    private ImageView imgProfile;
    private TextView tvName, tvDescription, tvTime,tvDate;
    private View viewShadow;
    private List<String> timeStampList= new ArrayList<>();

    private TextView tvStatusTitle,tvStatus,tvCancel,tvNewDateTime;
    private LinearLayout linearNewDateTime;

    private int currentPos=-1;
    public PatAppiontmentView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_my_appointments, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        recyclerAppointments= view.findViewById(R.id.recyclerAppointments);
        relativeDetail= view.findViewById(R.id.relativeDetail);
        btnClose= view.findViewById(R.id.btnClose);
        tvName= view.findViewById(R.id.tvName);
        tvDescription= view.findViewById(R.id.tvDescription);
        tvTime= view.findViewById(R.id.tvTime);
        tvDate= view.findViewById(R.id.tvDateTime);
        viewShadow= view.findViewById(R.id.viewShadow);
        imgProfile= view.findViewById(R.id.imgProfile);
        tvStatusTitle= view.findViewById(R.id.tvStatusTitle);
        tvStatus= view.findViewById(R.id.tvStatus);
        tvCancel= view.findViewById(R.id.tvCancel);
        tvNewDateTime= view.findViewById(R.id.tvNewDateTime);
        linearNewDateTime= view.findViewById(R.id.linearNewDateTime);

        setTimestamplist();
        adapter= new AdapterPatAppointment(setTimestamplist());
        recyclerAppointments.setLayoutManager(new LinearLayoutManager(activity));
        recyclerAppointments.setAdapter(adapter);
        relativeDetail.setVisibility(View.INVISIBLE);
//        ButterKnife.bind(view, activity);
    }

    private List<String> setTimestamplist() {
        timeStampList.clear();
        timeStampList.add("00:00 AM");
        timeStampList.add("00:30 AM");
        timeStampList.add("01:00 AM");
        timeStampList.add("01:30 AM");
        timeStampList.add("02:00 AM");
        timeStampList.add("02:30 AM");
        timeStampList.add("03:00 AM");
        timeStampList.add("03:30 AM");
        timeStampList.add("04:00 AM");
        timeStampList.add("04:30 AM");
        timeStampList.add("05:00 AM");
        timeStampList.add("05:30 AM");
        timeStampList.add("06:00 AM");
        timeStampList.add("06:30 AM");
        timeStampList.add("07:00 AM");
        timeStampList.add("07:30 AM");
        timeStampList.add("08:00 AM");
        timeStampList.add("08:30 AM");
        timeStampList.add("09:00 AM");
        timeStampList.add("09:30 AM");
        timeStampList.add("10:00 AM");
        timeStampList.add("10:30 AM");
        timeStampList.add("11:00 AM");
        timeStampList.add("11:30 AM");
        timeStampList.add("12:00 PM");
        timeStampList.add("12:30 PM");
        timeStampList.add("01:00 PM");
        timeStampList.add("01:30 PM");
        timeStampList.add("02:00 PM");
        timeStampList.add("02:30 PM");
        timeStampList.add("03:00 PM");
        timeStampList.add("03:30 PM");
        timeStampList.add("04:00 PM");
        timeStampList.add("04:30 PM");
        timeStampList.add("05:00 PM");
        timeStampList.add("05:30 PM");
        timeStampList.add("06:00 PM");
        timeStampList.add("06:30 PM");
        timeStampList.add("07:00 PM");
        timeStampList.add("07:30 PM");
        timeStampList.add("08:00 PM");
        timeStampList.add("08:30 PM");
        timeStampList.add("09:00 PM");
        timeStampList.add("09:30 PM");
        timeStampList.add("10:00 PM");
        timeStampList.add("10:30 PM");
        timeStampList.add("11:00 PM");
        timeStampList.add("11:30 PM");
        return timeStampList;
    }

    public View getView() {
        return this.view;
    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }

    Observable<Unit> closeClick() {
        return RxView.clicks(btnClose);
    }

    Observable<Unit> shadowClick() {
        return RxView.clicks(viewShadow);
    }

    Observable<Unit> confirmClicked() {
        return RxView.clicks(linearNewDateTime);
    }

//    Observable<Unit> cancelClicked() {
//        return RxView.clicks(tvCancel);
//    }

    public void setAdapterList(List<BookAppointmentModel> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyAdapter(this.list);
    }


    Observable<Integer> detailClick() {
        return adapter.detailClicks();
    }

    Observable<Unit> cancelClick() {
        return RxView.clicks(tvCancel);
    }

//    int getCurrentPos() {
//        return currentPos;
//    }

    void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    BookAppointmentModel getDetails(){
        return list.get(currentPos);
    }

//    void showToast(String msg){
//        Toasty.success(activity,msg).show();
//    }

//    void removeFromList(int pos){
//        list.remove(pos);
//        adapter.notifyAdapter(list);
//    }
//

    void showDetails(){
        relativeDetail.setVisibility(View.VISIBLE);
        relativeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvName.setText(list.get(currentPos).getFkDoctorName());
        tvTime.setText(timeStampList.get(list.get(currentPos).getTimeSlot()));
        tvDescription.setText(list.get(currentPos).getIssue());
        tvDescription.setMovementMethod(ScrollingMovementMethod.getInstance());
        try {
            tvDate.setText(TimeUtils.changeDateFormat(list.get(currentPos).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(activity).load(list.get(currentPos).getDoctorPhotoPath()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        if (list.get(currentPos).getStatus()==null || list.get(currentPos).getStatus()==0){
            linearNewDateTime.setVisibility(View.GONE);
            tvStatusTitle.setText(activity.getString(R.string.strStatus));
            tvStatus.setText(activity.getString(R.string.strPending));
            tvStatus.setTextColor(activity.getResources().getColor(R.color.colorBlue));
            tvCancel.setText(activity.getString(R.string.strCancel));
            tvCancel.setVisibility(View.VISIBLE);
            tvCancel.setTextColor(activity.getResources().getColor(R.color.colorRed));

        }else if(list.get(currentPos).getStatus()==1){
            linearNewDateTime.setVisibility(View.GONE);
            tvStatusTitle.setText(activity.getString(R.string.strStatus));
            tvStatus.setText(activity.getString(R.string.strConfirmed));
            tvStatus.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
            tvCancel.setText(activity.getString(R.string.strAddRating));
            tvCancel.setTextColor(activity.getResources().getColor(R.color.colorTextGreen));
            if (list.get(currentPos).getRated()){
                tvCancel.setVisibility(View.INVISIBLE);
            }else{
                tvCancel.setVisibility(View.VISIBLE);
            }
        }else if(list.get(currentPos).getStatus()==2){
            linearNewDateTime.setVisibility(View.GONE);
            tvStatusTitle.setText(activity.getString(R.string.strStatus));
            tvStatus.setText(activity.getString(R.string.strRejected));
            tvStatus.setTextColor(activity.getResources().getColor(R.color.colorRed));
            tvCancel.setText(activity.getString(R.string.strCancel));
            tvCancel.setTextColor(activity.getResources().getColor(R.color.colorRed));
            tvCancel.setVisibility(View.VISIBLE);

        }else if(list.get(currentPos).getStatus()==3){
            linearNewDateTime.setVisibility(View.VISIBLE);
            tvStatusTitle.setText(activity.getString(R.string.strSuggested));
            try {
                tvStatus.setText(TimeUtils.changeDateTimeFormat(list.get(currentPos).getSuggestedOn()));
                tvNewDateTime.setText(TimeUtils.changeDateTimeFormat(list.get(currentPos).getSuggestedOn()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tvStatus.setTextColor(activity.getResources().getColor(R.color.colorBlue));
            tvCancel.setText(activity.getString(R.string.strCancel));
            tvCancel.setTextColor(activity.getResources().getColor(R.color.colorRed));
            tvCancel.setVisibility(View.VISIBLE);

        }else{
            linearNewDateTime.setVisibility(View.GONE);
            tvStatusTitle.setText(activity.getString(R.string.strStatus));
            tvStatus.setText(activity.getString(R.string.strCanceled));
            tvStatus.setTextColor(activity.getResources().getColor(R.color.colorRed));
            tvCancel.setText(activity.getString(R.string.strCancel));
            tvCancel.setTextColor(activity.getResources().getColor(R.color.colorRed));
            tvCancel.setVisibility(View.INVISIBLE);

        }

    }

    void hideDetails() {
        relativeDetail.setVisibility(View.INVISIBLE);
    }

    Observable<Integer> confirmClick() {
        return adapter.confirmClick();
    }

    AcceptRejectApiRequest getParams(int pos,int status){
        currentPos=pos;
        AcceptRejectApiRequest request= new AcceptRejectApiRequest();
        request.setStatus(status);
        request.setAppointmentUID(list.get(pos).getUid());
        return request;
    }

    void showToast(String msg){
        Toasty.success(activity,msg).show();
    }

    int getCurrentPos() {
        return currentPos;
    }

    void updateList(boolean b) {
        if (b) {
            list.get(currentPos).setStatus(1);
            list.get(currentPos).setConfirmed(true);
            list.get(currentPos).setSuggested(true);
        }else{
            list.get(currentPos).setStatus(4);
            list.get(currentPos).setConfirmed(null);
            list.get(currentPos).setSuggested(null);
        }
        adapter.notifyItemChanged(currentPos);
    }

}
