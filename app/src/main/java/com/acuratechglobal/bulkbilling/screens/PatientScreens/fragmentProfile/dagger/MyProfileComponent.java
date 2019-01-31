package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentProfile.PatProfileFragment;

import dagger.Component;

@MyProfileScope
@Component(modules = {MyProfileModule.class }, dependencies = {AppComponent.class})
public interface MyProfileComponent {

    void inject(PatProfileFragment activity);

}
