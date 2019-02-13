package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.core;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.AcceptRejectApiRequest;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.list.AdapterDocAppointment;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;
import com.salah.rxdatetimepicker.RxDateTimePicker;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kotlin.Unit;

public class DocAppiontmentView {

    private View view;
    private ImageButton btnMenu;
    private RelativeLayout relativeDetail;
    private RecyclerView recyclerAppointments;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private AdapterDocAppointment adapter;
    private List<BookAppointmentModel> list=new ArrayList<>();
    private Button btnClose;
    private ImageView imgProfile;
    private TextView tvName, tvDescription, tvTime,tvDate;
    private View viewShadow;
    private List<String> timeStampList= new ArrayList<>();

    private int currentPos=-1;

    public DocAppiontmentView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_fragment_my_appointments, parent, true);
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

        setTimestamplist();
        adapter= new AdapterDocAppointment(setTimestamplist());
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

    public void setAdapterList(List<BookAppointmentModel> list) {
        this.list.clear();
//        this.list.addAll(list);
        for (BookAppointmentModel model:list) {
            if (model.getStatus()!=null && model.getStatus()==1){
                this.list.add(model);
            }
        }
        adapter.notifyAdapter(this.list);
    }


    Observable<Integer> detailClick() {
        return adapter.detailClicks();
    }

//    int getCurrentPos() {
//        return currentPos;
//    }

    void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
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

        tvName.setText(list.get(currentPos).getFkPatientName());
        tvTime.setText(timeStampList.get(list.get(currentPos).getTimeSlot()));
        tvDescription.setText(list.get(currentPos).getIssue());
        tvDescription.setMovementMethod(ScrollingMovementMethod.getInstance());
        try {
            tvDate.setText(TimeUtils.changeDateFormat(list.get(currentPos).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(activity).load(list.get(currentPos)).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
    }

    void hideDetails() {
        relativeDetail.setVisibility(View.INVISIBLE);
    }
}
