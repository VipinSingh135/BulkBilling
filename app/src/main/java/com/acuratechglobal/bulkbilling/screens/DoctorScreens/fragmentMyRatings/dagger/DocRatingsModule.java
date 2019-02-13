package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core.DocRatingsView;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core.DocRatingsModel;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.core.DocRatingsPresenter;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class DocRatingsModule {

    private final MainActivity activity;

    public DocRatingsModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @DocRatingsScope
    public DocRatingsView view(SharedPrefsUtil prefs) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new DocRatingsView(activity, progressDialog,prefs);
    }

    @Provides
    @DocRatingsScope
    public DocRatingsPresenter presenter(DocRatingsView view, DocRatingsModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new DocRatingsPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @DocRatingsScope
    DocRatingsModel model(Api taskApi,SharedPrefsUtil prefsUtil) {
        return new DocRatingsModel(activity, taskApi,prefsUtil);
    }
}
