package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import kotlin.Unit;


public class OptionsView {

    private View view;
    private TextView textView;
    private LinearLayout linearLayout2;
    private Button btnDoctor;
    private Button btnPatient;

    public OptionsView(OptionsActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_login_options, parent, true);

        textView=view.findViewById(R.id.textView);
        linearLayout2=view.findViewById(R.id.linearLayout2);
        btnDoctor=view.findViewById(R.id.btnDoctor);
        btnPatient=view.findViewById(R.id.btnPatient);

        textView.setVisibility(View.VISIBLE);
        linearLayout2.setVisibility(View.VISIBLE);
        btnDoctor.setVisibility(View.VISIBLE);
        btnPatient.setVisibility(View.VISIBLE);

    }

    public View constructView() {
        return view;
    }

    Observable<Unit> doctorClick(){
        return RxView.clicks(btnDoctor);
    }
    Observable<Unit> patientClick(){
        return RxView.clicks(btnPatient);
    }

//    void showOptions(){
//
//        textView.setVisibility(View.VISIBLE);
//        linearLayout2.setVisibility(View.VISIBLE);
//        btnDoctor.setVisibility(View.VISIBLE);
//        btnPatient.setVisibility(View.VISIBLE);
//    }

}
