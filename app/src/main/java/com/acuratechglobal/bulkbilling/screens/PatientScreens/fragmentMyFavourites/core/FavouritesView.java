package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.list.adapterFavourites;
import com.jakewharton.rxbinding3.view.RxView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

public class FavouritesView {

    private View view;
    ImageButton btnMenu;
    RecyclerView recyclerFavourites;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;

    public FavouritesView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_my_favourites, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        recyclerFavourites= view.findViewById(R.id.recyclerFavourites);
        recyclerFavourites.setLayoutManager(new LinearLayoutManager(activity));
        recyclerFavourites.setAdapter(new adapterFavourites(activity));
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

    @Nullable
    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }

}
