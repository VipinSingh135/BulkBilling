package com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan;

import android.os.Bundle;

import com.acuratechglobal.bulkbilling.application.AppController;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core.SelectPlanPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core.SelectPlanView;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.dagger.DaggerSelectPlanComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.dagger.SelectPlanModule;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectPlanActivity extends AppCompatActivity {


    @Inject
    SelectPlanView view;

    @Inject
    SelectPlanPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSelectPlanComponent.builder()
                .appComponent(AppController.getAppComponent())
                .selectPlanModule(new SelectPlanModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_task, menu);
//        return true;
//    }

}
