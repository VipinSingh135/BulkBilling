package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.core;

import android.app.ProgressDialog;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.BookAppointmentActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.list.TimeSlotsAdapter;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding3.view.RxView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import devs.mulham.horizontalcalendar.HorizontalCalendar;
//import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;

public class AppointmentView {

    private View view;

    private TextView tvName, tvPhone;
    private ImageView imgProfile;
    private EditText edHealthIssue;
    private ImageButton btnBack;
    private Button btnSubmit;
    private RecyclerView recyclerTimeSlot;
    private final BookAppointmentActivity activity;
    private ProgressDialog progressDialog;
    private TimeSlotsAdapter adapter;

    private String selectedTime=null;
    private String selectedDate=null;
    private UserData userData;
    private DoctorProfileModel docData;
    private List<String> timeStampList= new ArrayList<>();
    private List<String> list= new ArrayList<>();

    public AppointmentView(BookAppointmentActivity context, SharedPrefsUtil prefs) {
        this.activity = context;
        if (prefs!=null){
            userData= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_activity_book_appointment, parent, true);

        tvName = view.findViewById(R.id.tvName);
        edHealthIssue = view.findViewById(R.id.edHealthIssue);
        tvPhone = view.findViewById(R.id.tvPhone);
        imgProfile = view.findViewById(R.id.imgProfile);
        recyclerTimeSlot = view.findViewById(R.id.recyclerTimeSlot);
        btnBack = view.findViewById(R.id.btnBack);
        btnSubmit = view.findViewById(R.id.btnSubmit);

    }

    void bindViews(DoctorProfileModel data) {
        this.docData=data;
        tvName.setText(data.getFirstName() + " " + data.getLastName());
        if (data.getPhone()!=null)
            tvPhone.setText(data.getPhone());

        if (data.getsPhotoPath()!=null){
            Glide.with(activity).load(Uri.parse(data.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        }

        setCalenderView();
        setTimestamplist();
        if (data.getOpenTime()!=null && data.getCloseTime()!=null){
            setRecyclerView(data.getOpenTime(),data.getCloseTime());
        }
    }

    private void setTimestamplist() {
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
    }

    private void setRecyclerView(String openTime, String closeTime) {
        for (String time: timeStampList){
            if (TimeUtils.checktimings(openTime,time) && TimeUtils.checktimings(time,closeTime)){
                list.add(time);
            }
        }

        adapter = new TimeSlotsAdapter();
        recyclerTimeSlot.setLayoutManager(new GridLayoutManager(activity,4));
        recyclerTimeSlot.setAdapter(adapter);
        adapter.setAdapterList(list);
    }

    private void setCalenderView() {

        Date startDate = Calendar.getInstance().getTime();

        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        Date endDate = calendar.getTime();

        DatePickerTimeline timeline = view.findViewById(R.id.timeline);
        timeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("MMM");
                return timeStampFormat.format(calendar.getTime());
            }
        });

        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                String strDay= String.valueOf(day);
                if (day<10)
                    strDay = "0" + day;

                String strMonth= String.valueOf(month+1);
                if ((month+1)<10)
                    strMonth = "0" + (month + 1);

                selectedDate= String.format("%d/%s/%s", year, strMonth, strDay);
            }
        });

        timeline.setFirstVisibleDate(startDate.getYear()+1900, startDate.getMonth(), startDate.getDate());
        timeline.setLastVisibleDate(endDate.getYear()+1900, endDate.getMonth(), endDate.getDate());

    }

    void setProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
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

    Observable<Unit> backClicked() {
        return RxView.clicks(btnBack);
    }
    Observable<Unit> submitClicked() {
        return RxView.clicks(btnSubmit);
    }

    Observable<Integer> itemclicked(){
        return adapter.observeClicks();
    }

    void setCurrentItem(int pos){
        adapter.setSelectedPos(pos);
        selectedTime= list.get(pos);
    }

    //Set Params
    BookAppointmentModel getParams() {
        BookAppointmentModel request= new BookAppointmentModel();
        request.setFkDoctorId(docData.getDocID());
        request.setFkDoctorName(docData.getFirstName()+" "+docData.getLastName());
        request.setFkPatientId(userData.getPatID());
        request.setFkPatientName(userData.getFirstName()+" "+userData.getLastName());
        request.setIssue(getInputText(edHealthIssue));
        int slotPos= timeStampList.indexOf(selectedTime);
        request.setTimeSlot(slotPos);
        request.setDate(selectedDate);
        return request;
    }

    void showToast(String msg){
        Toasty.success(activity,msg).show();
    }

}
