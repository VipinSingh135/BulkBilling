package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.dagger;

import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core.MainModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core.MainPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core.MainView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    MainActivity mainContext;

    public MainModule(MainActivity context) {
        this.mainContext = context;
    }

    @MainScope
    @Provides
    MainActivity provideMainContext() {
        return mainContext;
    }


    @MainScope
    @Provides
    MainPresenter providePresenter(MainModel model,MainView view, RxSchedulers rxSchedulers) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new MainPresenter(model, view, compositeSubscription, rxSchedulers);
    }


    @MainScope
    @Provides
    MainView provideMainView(SharedPrefsUtil prefs) {
        return new MainView(mainContext,prefs);
    }


    @MainScope
    @Provides
    MainModel provideMainModel(SharedPrefsUtil prefs) {
        return new MainModel(mainContext,prefs);
    }
}
