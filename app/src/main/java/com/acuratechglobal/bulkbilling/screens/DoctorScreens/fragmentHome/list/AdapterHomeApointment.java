package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.list.PatHomeAdapterViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AdapterHomeApointment extends RecyclerView.Adapter<HomeAdapterViewHolder> {

    private final PublishSubject<Integer> detailClick = PublishSubject.create();
    private final PublishSubject<Integer> acceptClick = PublishSubject.create();
    private final PublishSubject<Integer> rejectClick = PublishSubject.create();
    private final PublishSubject<Integer> suggestClick = PublishSubject.create();
    private List<String> timeSlots;

    public AdapterHomeApointment(List<String> timeslotlist) {
        this.timeSlots= timeslotlist;
    }

    public Observable<Integer> detailClicks() {
        return detailClick;
    }
    public Observable<Integer> acceptClicks() {
        return acceptClick;
    }
    public Observable<Integer> rejectClicks() {
        return rejectClick;
    }
    public Observable<Integer> suggestClicks() {
        return suggestClick;
    }

    ArrayList<BookAppointmentModel> list = new ArrayList<>();

    public void notifyAdapter(List<BookAppointmentModel> selectedList) {
        this.list.clear();
        this.list.addAll(selectedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_adapter_home_appointments, parent, false);
        return new HomeAdapterViewHolder(view ,suggestClick,acceptClick,rejectClick,detailClick);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapterViewHolder holder, int position) {
        holder.bind(list.get(position),timeSlots.get(list.get(position).getTimeSlot()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}