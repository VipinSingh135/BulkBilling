package com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.SelectPlanActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core.SelectPlanModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core.SelectPlanPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.core.SelectPlanView;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class SelectPlanModule {

    private final SelectPlanActivity activity;

    public SelectPlanModule(SelectPlanActivity activity) {
        this.activity = activity;
    }

    @Provides
    @SelectPlanScope
    public SelectPlanView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new SelectPlanView(activity, progressDialog);
    }

    @Provides
    @SelectPlanScope
    public SelectPlanPresenter presenter(SelectPlanView view, SelectPlanModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new SelectPlanPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @SelectPlanScope
    SelectPlanModel model(Api taskApi) {
        return new SelectPlanModel(activity, taskApi);
    }
}
