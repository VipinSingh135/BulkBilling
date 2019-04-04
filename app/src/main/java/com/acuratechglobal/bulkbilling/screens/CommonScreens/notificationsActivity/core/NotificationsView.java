package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.models.FavouriteDoctorModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.list.AdapterFavourites;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

public class FavouritesView {

    private View view;
//    private ImageButton btnMenu;
    private RecyclerView recyclerFavourites;
    private AdapterFavourites adapter;
    private List<DoctorProfileModel> dataList= new ArrayList<>();

    private final MainTabActivity activity;
    private final ProgressDialog progressDialog;

    public FavouritesView(MainTabActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_my_favourites, parent, true);
//        btnMenu= view.findViewById(R.id.btnMenu);
        recyclerFavourites= view.findViewById(R.id.recyclerFavourites);
        recyclerFavourites.setLayoutManager(new LinearLayoutManager(activity));
        adapter= new AdapterFavourites();
        recyclerFavourites.setAdapter(adapter);
//        ButterKnife.bind(view, activity);
    }

    public View getView() {
        return this.view;
    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

//    Observable<Unit> menuClick() {
//        return RxView.clicks(btnMenu);
//    }

    Observable<Integer> itemClick() {
        return adapter.observeClicks();
    }

    void setAdapterList(List<DoctorProfileModel> list){
        dataList.clear();
        dataList.addAll(list);
        adapter.notifyAdapter(list);
    }


     void gotoDocProfile(Integer pos) {
         Intent intent= new Intent(activity, DoctorProfileActivity.class);
         String strData= new Gson().toJson(dataList.get(pos));
         intent.putExtra("data",strData);
         activity.startActivity(intent);
    }
}
