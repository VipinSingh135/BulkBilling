package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.BookAppointmentModel;
import com.acuratechglobal.bulkbilling.models.RatingDataModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AdapterDocMyRatings extends RecyclerView.Adapter<MyRatingsViewHolder> {

    private ArrayList<RatingDataModel> list = new ArrayList<>();

    public void notifyAdapter(List<RatingDataModel> selectedList) {
        this.list.clear();
        this.list.addAll(selectedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRatingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_adapter_ratings, parent, false);
        return new MyRatingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRatingsViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}