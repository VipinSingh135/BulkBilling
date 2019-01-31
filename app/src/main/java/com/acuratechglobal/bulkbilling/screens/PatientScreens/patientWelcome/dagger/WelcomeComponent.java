package com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.patientWelcome.WelcomeActivity;

import dagger.Component;

@WelcomeScope
@Component(modules = {WelcomeModule.class }, dependencies = {AppComponent.class})
public interface WelcomeComponent {

    void inject(WelcomeActivity activity);

}
