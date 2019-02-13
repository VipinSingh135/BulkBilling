package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsViewHolder> {


    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<String> list = new ArrayList<>();
    private int selectedPos=-1;
    public void setAdapterList(List<String> strList) {
        this.list.clear();
        this.list.addAll(strList);
        notifyDataSetChanged();
    }

    public void setSelectedPos(int pos){
        this.selectedPos=pos;
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @NonNull
    @Override
    public TimeSlotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pat_adapter_timeslots, parent, false);
        return new TimeSlotsViewHolder(view ,itemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotsViewHolder holder, int position) {
        String model = list.get(position);
        if (position==selectedPos) {
            holder.bind(model, true);
        }else
            holder.bind(model, false);
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

}
