package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.dagger;

import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;

import dagger.Component;

@MainScope
@Component(modules = {MainTabModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainTabActivity activity);
}
