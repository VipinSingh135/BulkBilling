package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectViewHolder> {


    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<SpecializationModel> list = new ArrayList<>();
    ArrayList<SpecializationModel> selectedList = new ArrayList<>();

    public void setAdapterItems(List<SpecializationModel> list,List<SpecializationModel> selectedList) {
        this.list.clear();
        this.list.addAll(list);
        this.selectedList.clear();
        this.selectedList.addAll(selectedList);
        notifyDataSetChanged();
    }
    public void notifyAdapter(List<SpecializationModel> selectedList) {
        this.selectedList.clear();
        this.selectedList.addAll(selectedList);
        notifyDataSetChanged();
    }
    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @NonNull
    @Override
    public MultiSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_multiselect, parent, false);
        return new MultiSelectViewHolder(view ,itemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiSelectViewHolder holder, int position) {
        SpecializationModel model = list.get(position);
        if (selectedList.size()==0) {
            holder.bind(model,false);
        }else {
            boolean isSelected=false;
            for (SpecializationModel obj:selectedList) {
                if (obj.getId()==model.getId()){
                    isSelected=true;
                }
            }
            holder.bind(model,isSelected);
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
