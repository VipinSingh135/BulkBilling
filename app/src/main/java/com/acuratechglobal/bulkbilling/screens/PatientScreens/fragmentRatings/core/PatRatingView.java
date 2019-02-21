package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.GetPatientRatingListRequest;
import com.acuratechglobal.bulkbilling.models.PatientRatingModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.list.PatRatingAdapter;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

public class PatRatingView {

    private View view;
    private ImageButton btnMenu;
    private RecyclerView recyclerHome;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private GetPatientRatingListRequest request;
    private PatRatingAdapter adapter;
    private List<PatientRatingModel> dataList;

    private UserData data;

    public PatRatingView(MainActivity context, ProgressDialog dialog,SharedPrefsUtil prefs) {
        this.activity = context;
        this.progressDialog = dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_rating, parent, true);
        btnMenu = view.findViewById(R.id.btnMenu);
        recyclerHome = view.findViewById(R.id.recyclerHome);

        adapter = new PatRatingAdapter();
        recyclerHome.setLayoutManager(new LinearLayoutManager(activity));
        recyclerHome.setAdapter(adapter);
//        ButterKnife.bind(view, activity);

        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);

            request = new GetPatientRatingListRequest();
            request.setPageNo(1);
            request.setPatientUID(data.getPatUID());
            request.setRecordsPerPage(100);
            request.setSortBy("addedon");
            request.setSortOrder("Desc");
        }
        dataList = new ArrayList<>();
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

    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }


    public GetPatientRatingListRequest getRequest() {
        return request;
    }

    void setAdapterList(List<PatientRatingModel> list) {
        dataList.clear();
        dataList.addAll(list);
        adapter.notifyAdapter(list);
    }

}
