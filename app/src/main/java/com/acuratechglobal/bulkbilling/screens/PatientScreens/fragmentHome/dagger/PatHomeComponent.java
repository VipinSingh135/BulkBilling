package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.PatHomeFragment;

import dagger.Component;

@HomeScope
@Component(modules = {PatHomeModule.class }, dependencies = {AppComponent.class})
public interface PatHomeComponent {

    void inject(PatHomeFragment activity);

}
