package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;

import dagger.Component;

@CreateProfileScope
@Component(modules = {CreateProfileModule.class }, dependencies = {AppComponent.class})
public interface CreateProfileComponent {

    void inject(CreateProfileActivity activity);

}
