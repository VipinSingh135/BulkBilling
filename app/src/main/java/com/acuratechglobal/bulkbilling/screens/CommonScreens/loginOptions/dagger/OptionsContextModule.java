package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.dagger;

import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class OptionsContextModule {
    OptionsActivity splashContext;

    public OptionsContextModule(OptionsActivity context) {
        this.splashContext = context;
    }

    @OptionsScope
    @Provides
    OptionsActivity provideSplashContext() {
        return splashContext;
    }
}
