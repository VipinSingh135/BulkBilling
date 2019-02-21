package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core.PatRatingModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core.PatRatingPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.core.PatRatingView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PatRatingModule {

    private final MainActivity activity;

    public PatRatingModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PatRatingScope
    public PatRatingView view(SharedPrefsUtil prefs) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new PatRatingView(activity, progressDialog,prefs);
    }

    @Provides
    @PatRatingScope
    public PatRatingPresenter presenter(PatRatingView view, PatRatingModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new PatRatingPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @PatRatingScope
    PatRatingModel model(Api taskApi) {
        return new PatRatingModel(activity, taskApi);
    }
}
