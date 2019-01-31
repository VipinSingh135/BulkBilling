package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.core;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.api.request.LoginApiRequest;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.list.adapterHome;
import com.jakewharton.rxbinding3.view.RxView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getColor;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getDrawable;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;
import static com.acuratechglobal.bulkbilling.utils.UiUtils.playFailureAnimation;
import static com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations.PASSWORD_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations.USERNAME_EMPTY;
import static com.acuratechglobal.bulkbilling.utils.Validations.LoginValidations.USERNAME_INAVALID;

public class HomeView {

    private View view;
    ImageButton btnMenu;
    RecyclerView recyclerHome;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;

    public HomeView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.doc_fragment_home, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        recyclerHome= view.findViewById(R.id.recyclerHome);
        recyclerHome.setLayoutManager(new LinearLayoutManager(activity));
        recyclerHome.setAdapter(new adapterHome());
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
