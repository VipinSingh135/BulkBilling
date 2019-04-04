package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapterFavourites extends RecyclerView.Adapter<adapterFavourites.MyViewHolder> {

    Context context;

    public adapterFavourites(Context context) {
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
        holder.imgfav.setVisibility(View.VISIBLE);
        holder.tvExperience.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {

        return 2;

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvExperience;
        ImageView imgfav;
        ImageView imgProfile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            imgfav = itemView.findViewById(R.id.imgFav);
            tvExperience = itemView.findViewById(R.id.tvExperience);
        }
    }
}