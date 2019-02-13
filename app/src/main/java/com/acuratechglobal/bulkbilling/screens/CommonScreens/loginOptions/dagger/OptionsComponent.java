package com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.dagger;

import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.splash.SplashActivity;

import dagger.Component;

@SplashScope
@Component(modules = {SplashContextModule.class, SplashModule.class}, dependencies = {AppComponent.class})
public interface SplashComponent {
    void inject(SplashActivity activity);
}
