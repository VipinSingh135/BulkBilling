package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AdapterPatAppointment extends RecyclerView.Adapter<PatAppointmentAdapterViewHolder> {

    private final PublishSubject<Integer> detailClick = PublishSubject.create();
    private final PublishSubject<Integer> confirmClick = PublishSubject.create();
    private List<String> timeSlots;

    public AdapterPatAppointment(List<String> timeslotlist) {
        this.timeSlots= timeslotlist;
    }

    public Observable<Integer> detailClicks() {
        return detailClick;
    }
    public Observable<Integer> confirmClick() {
        return confirmClick;
    }

    private ArrayList<BookAppointmentModel> list = new ArrayList<>();

    public void notifyAdapter(List<BookAppointmentModel> selectedList) {
        this.list.clear();
        this.list.addAll(selectedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatAppointmentAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pat_adapter_appointments, parent, false);
        return new PatAppointmentAdapterViewHolder(view ,detailClick,confirmClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PatAppointmentAdapterViewHolder holder, int position) {
        holder.bind(list.get(position),timeSlots.get(list.get(position).getTimeSlot()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}