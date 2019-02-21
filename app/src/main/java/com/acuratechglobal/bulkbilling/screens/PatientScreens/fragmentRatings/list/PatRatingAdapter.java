package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.PatientRatingModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class PatRatingAdapter extends RecyclerView.Adapter<PatRatingAdapterViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    ArrayList<PatientRatingModel> list = new ArrayList<>();

    public void notifyAdapter(List<PatientRatingModel> selectedList) {
        this.list.clear();
        this.list.addAll(selectedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatRatingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_adapter_ratings, parent, false);
        return new PatRatingAdapterViewHolder(view ,itemClicks,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull PatRatingAdapterViewHolder holder, int position) {

        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {

        return list.size();

    }


}