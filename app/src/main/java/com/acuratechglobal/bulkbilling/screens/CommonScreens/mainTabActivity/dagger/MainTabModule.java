package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.dagger;

import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.core.MainModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.core.MainPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.core.MainView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    MainTabActivity mainContext;

    public MainModule(MainTabActivity context) {
        this.mainContext = context;
    }

    @MainScope
    @Provides
    MainTabActivity provideMainContext() {
        return mainContext;
    }


    @MainScope
    @Provides
    MainPresenter providePresenter(MainModel model, MainView view, RxSchedulers rxSchedulers) {
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
