package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class PatHomeAdapter extends RecyclerView.Adapter<PatHomeAdapterViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    ArrayList<DoctorProfileModel> list = new ArrayList<>();

    public void notifyAdapter(List<DoctorProfileModel> selectedList) {
        this.list.clear();
        this.list.addAll(selectedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatHomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pat_adapter_home, parent, false);
        return new PatHomeAdapterViewHolder(view ,itemClicks,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull PatHomeAdapterViewHolder holder, int position) {

        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {

        return list.size();

    }

}