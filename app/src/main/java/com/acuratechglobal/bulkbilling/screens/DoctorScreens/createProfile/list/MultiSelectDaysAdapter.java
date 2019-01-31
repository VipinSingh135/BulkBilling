package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list;

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

public class MultiSelectDaysAdapter extends RecyclerView.Adapter<MultiSelectDaysViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> selectedList = new ArrayList<>();

    public void setAdapterItems(List<String> list, List<String> selectedList) {
        this.list.clear();
        this.list.addAll(list);
        this.selectedList.clear();
        this.selectedList.addAll(selectedList);
        notifyDataSetChanged();
    }
    public void notifyAdapter(List<String> selectedList) {
        this.selectedList.clear();
        this.selectedList.addAll(selectedList);
        notifyDataSetChanged();
    }
    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @NonNull
    @Override
    public MultiSelectDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_multiselect, parent, false);
        return new MultiSelectDaysViewHolder(view ,itemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiSelectDaysViewHolder holder, int position) {
        String model = list.get(position);
        if (selectedList.size()==0) {
            holder.bind(model,false);
        }else if (selectedList.contains(model)) {
            holder.bind(model,true);
        }else{
            holder.bind(model,false);
        }
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
