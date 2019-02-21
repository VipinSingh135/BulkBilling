package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core;

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
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.list.AdapterHomeApointment;
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

public class HomeView {

    private View view;
    private ImageButton btnMenu;
    private RelativeLayout relativeDetail;
    private RecyclerView recyclerHome;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private AdapterHomeApointment adapter;
    private String suggestedDate=null;
    private List<BookAppointmentModel> list=new ArrayList<>();
    private PublishSubject<Integer> yesClick = PublishSubject.create();
    private Button btnClose;
    private ImageView imgProfile;
    private TextView tvName, tvDescription, tvTime,tvDate;
    private View viewShadow;
    private List<String> timeStampList= new ArrayList<>();

    private int currentPos=-1;
    public HomeView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_fragment_home, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        recyclerHome= view.findViewById(R.id.recyclerHome);
        relativeDetail= view.findViewById(R.id.relativeDetail);
        btnClose= view.findViewById(R.id.btnClose);
        tvName= view.findViewById(R.id.tvName);
        tvDescription= view.findViewById(R.id.tvDescription);
        tvTime= view.findViewById(R.id.tvTime);
        tvDate= view.findViewById(R.id.tvDateTime);
        viewShadow= view.findViewById(R.id.viewShadow);
        imgProfile= view.findViewById(R.id.imgProfile);

        setTimestamplist();
        adapter= new AdapterHomeApointment(setTimestamplist());
        recyclerHome.setLayoutManager(new LinearLayoutManager(activity));
        recyclerHome.setAdapter(adapter);
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
            if (model.getStatus()==null || model.getStatus()==0){
                this.list.add(model);
            }
        }
        adapter.notifyAdapter(this.list);
    }

    Observable<Integer> acceptClick() {
        return adapter.acceptClicks();
    }
    Observable<Integer> rejectClick() {
        return adapter.rejectClicks();
    }
    Observable<Integer> suggestClick() {
        return adapter.suggestClicks();
    }
    Observable<Integer> detailClick() {
        return adapter.detailClicks();
    }

    AcceptRejectApiRequest getParams(int status,int pos){
        AcceptRejectApiRequest request= new AcceptRejectApiRequest();
        request.setStatus(status);
        request.setAppointmentUID(list.get(pos).getUid());
        request.setSuggestedOn(suggestedDate);
        return request;
    }

    int getCurrentPos() {
        return currentPos;
    }

    void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    void setSuggestedDate(String suggestedDate) {
        this.suggestedDate = suggestedDate;
    }

    void showToast(String msg){
        Toasty.success(activity,msg).show();
    }

    void showAlertDialog(boolean isAccept){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // 2. Chain together various setter methods to set the dialog characteristics
        if (isAccept)
            builder.setMessage("Do you really want to accept ? ");
        else
            builder.setMessage("Do you really want to reject ? ");

        // Add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                if (isAccept) {
                    yesClick.onNext(1);
                }else{
                    yesClick.onNext(2);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    Observable<Integer> yesClicked() {
        return yesClick;
    }

    void removeFromList(int pos){
        list.remove(pos);
        adapter.notifyAdapter(list);
    }

    //DateTime
    RxDateTimePicker DateTimePicker() {
        return RxDateTimePicker
                .with(activity)
                .is24HourView(true);
    }

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
