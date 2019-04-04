package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.list;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.SideMenuListModel;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

public class SideMenuViewHolder extends RecyclerView.ViewHolder {
    View view;

    TextView tvOptions;

    public SideMenuViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        tvOptions = view.findViewById(R.id.tvOptions);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(SideMenuListModel model) {
//        Glide.with(view.getContext()).load(hero.getImage()).into(imageHero);

        tvOptions.setText(TextUtils.isEmpty(model.getTitle()) ? "missing name" : model.getTitle());

        if (model.isIsSelected()){
            tvOptions.setBackgroundResource(R.color.colorPrimary);
            tvOptions.setTextColor(Color.WHITE);
        }else{
            tvOptions.setBackgroundColor(Color.WHITE);
            tvOptions.setTextColor(Color.parseColor("#11983c"));
        }
    }
}
