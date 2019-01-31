package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.SettingsFragment;

import dagger.Component;

@SettingsScope
@Component(modules = {SettingsModule.class }, dependencies = {AppComponent.class})
public interface SettingsComponent {

    void inject(SettingsFragment activity);

}
