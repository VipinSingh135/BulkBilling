package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.DocHomeFragment;

import dagger.Component;

@HomeScope
@Component(modules = {HomeModule.class }, dependencies = {AppComponent.class})
public interface HomeComponent {

    void inject(DocHomeFragment activity);

}
