package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acuratechglobal.bulkbilling.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapterHome extends RecyclerView.Adapter<adapterHome.MyViewHolder> {

    Context context;

    public adapterHome(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pat_adapter_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(R.drawable.doctor_img).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(holder.imgProfile);
    }

    @Override
    public int getItemCount() {

        return 2;

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProfile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
    }
}