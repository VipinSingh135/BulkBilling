package com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.dagger;

import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.loginOptions.OptionsActivity;

import dagger.Component;

@OptionsScope
@Component(modules = {OptionsContextModule.class, OptionsModule.class}, dependencies = {AppComponent.class})
public interface OptionsComponent {
    void inject(OptionsActivity activity);
}
