package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;


public class SplashView {

    private View view;

    public SplashView(SplashActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_splash, parent, true);
    }

    public View constructView() {
        return view;
    }

}
