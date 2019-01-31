package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.dagger;

import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core.SplashModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core.SplashPresenter;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.core.SplashView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @SplashScope
    @Provides
    SplashPresenter providePresenter( SplashModel model) {
        return new SplashPresenter(model);
    }


    @SplashScope
    @Provides
    SplashView provideSplashView(SplashActivity context) {
        return new SplashView(context);
    }


    @SplashScope
    @Provides
    SplashModel provideSplashModel(SplashActivity ctx , SharedPrefsUtil prefs) {
        return new SplashModel(ctx, prefs);
    }
}
