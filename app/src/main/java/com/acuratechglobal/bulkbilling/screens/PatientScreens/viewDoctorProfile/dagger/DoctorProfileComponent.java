package com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;

import dagger.Component;

@DoctorProfileScope
@Component(modules = {DoctorProfileModule.class }, dependencies = {AppComponent.class})
public interface DoctorProfileComponent {

    void inject(DoctorProfileActivity activity);

}
