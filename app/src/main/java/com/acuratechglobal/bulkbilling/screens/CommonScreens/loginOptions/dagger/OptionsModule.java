package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.dagger;

import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core.OptionsModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core.OptionsPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.core.OptionsView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class OptionsModule {

    @OptionsScope
    @Provides
    OptionsPresenter providePresenter(OptionsModel model, OptionsView view) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new OptionsPresenter(model,view,compositeSubscription);
    }


    @OptionsScope
    @Provides
    OptionsView provideSplashView(OptionsActivity context) {
        return new OptionsView(context);
    }


    @OptionsScope
    @Provides
    OptionsModel provideSplashModel(OptionsActivity ctx , SharedPrefsUtil prefs) {
        return new OptionsModel(ctx, prefs);
    }
}
