package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AdapterFavourites extends RecyclerView.Adapter<FavouriteAdapterViewHolder> {

    private final PublishSubject<Integer> itemClick = PublishSubject.create();
    private List<DoctorProfileModel> modelList= new ArrayList<>();

    public void notifyAdapter(List<DoctorProfileModel> list) {
        this.modelList.clear();
        this.modelList.addAll(list);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClick;
    }

    @NonNull
    @Override
    public FavouriteAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pat_adapter_home, parent, false);
        return new FavouriteAdapterViewHolder(view,parent.getContext(),itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapterViewHolder holder, int position) {
        holder.bind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();

    }




}