package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyAppointments.PatAppointmentFragment;

import dagger.Component;

@PatAppointmentScope
@Component(modules = {PatAppointmentModule.class }, dependencies = {AppComponent.class})
public interface PatAppointmentComponent {

    void inject(PatAppointmentFragment activity);

}
