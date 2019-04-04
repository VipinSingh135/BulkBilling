package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.dagger;

import android.app.ProgressDialog;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core.FavouritesModel;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core.FavouritesPresenter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.core.FavouritesView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class FavouritesModule {

    private final MainTabActivity activity;

    public FavouritesModule(MainTabActivity activity) {
        this.activity = activity;
    }

    @Provides
    @FavouritesScope
    public FavouritesView view() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        return new FavouritesView(activity, progressDialog);
    }

    @Provides
    @FavouritesScope
    public FavouritesPresenter presenter(FavouritesView view, FavouritesModel model, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new FavouritesPresenter(view, model, compositeSubscription, rxSchedulers);
    }

    @Provides
    @FavouritesScope
    FavouritesModel model(Api taskApi, SharedPrefsUtil prefsUtil) {
        return new FavouritesModel(activity, taskApi, prefsUtil);
    }
}
