package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.dagger;

import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashContextModule {
    SplashActivity splashContext;

    public SplashContextModule(SplashActivity context) {
        this.splashContext = context;
    }

    @SplashScope
    @Provides
    SplashActivity provideSplashContext() {
        return splashContext;
    }
}
