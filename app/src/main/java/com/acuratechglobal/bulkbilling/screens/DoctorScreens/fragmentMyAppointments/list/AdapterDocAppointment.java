package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.list;

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

public class AdapterDocAppointment extends RecyclerView.Adapter<DocAppointmentsAdapterViewHolder> {

    private final PublishSubject<Integer> detailClick = PublishSubject.create();
    private List<String> timeSlots;

    public AdapterDocAppointment(List<String> timeslotlist) {
        this.timeSlots= timeslotlist;
    }

    public Observable<Integer> detailClicks() {
        return detailClick;
    }

    private ArrayList<BookAppointmentModel> list = new ArrayList<>();

    public void notifyAdapter(List<BookAppointmentModel> selectedList) {
        this.list.clear();
        this.list.addAll(selectedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DocAppointmentsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_adapter_home_appointments, parent, false);
        return new DocAppointmentsAdapterViewHolder(view ,detailClick);
    }

    @Override
    public void onBindViewHolder(@NonNull DocAppointmentsAdapterViewHolder holder, int position) {
        holder.bind(list.get(position),timeSlots.get(list.get(position).getTimeSlot()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}