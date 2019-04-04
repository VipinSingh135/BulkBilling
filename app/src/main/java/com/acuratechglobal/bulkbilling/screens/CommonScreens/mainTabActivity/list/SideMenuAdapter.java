package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.SideMenuListModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SideMenuAdapter extends RecyclerView.Adapter<SideMenuViewHolder> {


    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<String> list = new ArrayList<>();
    int selectedPos=0;

    public void setAdapter(List<String> strList,int pos) {
        this.list.clear();
        this.list.addAll(strList);
        selectedPos= pos;
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }


    @NonNull
    @Override
    public SideMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_side_menu, parent, false);
        return new SideMenuViewHolder(view, itemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull SideMenuViewHolder holder, int position) {
        SideMenuListModel model= new SideMenuListModel();
        model.setTitle(list.get(position));
        if (selectedPos==position){
            model.setIsSelected(true);
        }else{
            model.setIsSelected(false);
        }
        holder.bind(model);
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