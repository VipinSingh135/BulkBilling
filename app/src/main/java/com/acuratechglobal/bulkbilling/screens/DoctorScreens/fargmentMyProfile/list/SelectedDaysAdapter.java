package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedDaysAdapter  extends RecyclerView.Adapter<SelectedDaysViewHolder> {


    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> listDays = new ArrayList<>();

    public SelectedDaysAdapter() {
        list.clear();
        list.add("Sunday");
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
    }

    public void setAdapterList(List<String> strList) {
        this.listDays.clear();
        this.listDays.addAll(strList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SelectedDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_selected_items, parent, false);
        return new SelectedDaysViewHolder(view );
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedDaysViewHolder holder, int position) {
        String model = list.get(position);
        if (listDays.contains(list.get(position))) {
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
