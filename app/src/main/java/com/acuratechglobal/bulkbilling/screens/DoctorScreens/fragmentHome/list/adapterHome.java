package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapterHome extends RecyclerView.Adapter<adapterHome.MyViewHolder> {

    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_adapter_home_appointments, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return 2;

    }
    
    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}