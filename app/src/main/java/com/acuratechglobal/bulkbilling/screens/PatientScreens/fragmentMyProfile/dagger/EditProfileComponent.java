package com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.editProfile.EditProfileActivity;

import dagger.Component;

@EditProfileScope
@Component(modules = {EditProfileModule.class }, dependencies = {AppComponent.class})
public interface EditProfileComponent {

    void inject(EditProfileActivity activity);

}
