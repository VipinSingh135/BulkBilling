package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyAppointments.DocAppointmentFragment;

import dagger.Component;

@DocAppointmentScope
@Component(modules = {DocAppointmentModule.class }, dependencies = {AppComponent.class})
public interface DocAppointmentComponent {

    void inject(DocAppointmentFragment activity);

}
