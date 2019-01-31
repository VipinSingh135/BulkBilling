package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.dagger;

import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;

import dagger.Component;

@MainScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
