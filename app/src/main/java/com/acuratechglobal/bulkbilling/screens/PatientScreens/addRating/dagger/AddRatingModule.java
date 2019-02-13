package com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.AddRatingActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core.AddRatingModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core.AddRatingPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.core.AddRatingView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AddRatingModule {

    private final AddRatingActivity activity;

    public AddRatingModule(AddRatingActivity activity) {
        this.activity = activity;
    }

    @Provides
    @AddRatingScope
    public AddRatingView view(SharedPrefsUtil prefs) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new AddRatingView(activity, progressDialog,prefs);
    }

    @Provides
    @AddRatingScope
    public AddRatingPresenter presenter(AddRatingView view, AddRatingModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new AddRatingPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @AddRatingScope
    AddRatingModel model(Api taskApi) {
        return new AddRatingModel(activity, taskApi);
    }
}
