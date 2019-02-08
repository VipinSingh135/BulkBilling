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

public class SelectedDaysAdapter  extends RecyclerView.Adapter<SelectedDaysViewHolder> {


    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<String> list = new ArrayList<>();

    public void setAdapterList(List<String> strList) {
        this.list.clear();
        this.list.addAll(strList);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @NonNull
    @Override
    public SelectedDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_selected_items, parent, false);
        return new SelectedDaysViewHolder(view ,itemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedDaysViewHolder holder, int position) {
        String model = list.get(position);
        holder.bind(model);
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
