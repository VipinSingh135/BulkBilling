package com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.bookAppointment.BookAppointmentActivity;

import dagger.Component;

@AppointmentScope
@Component(modules = {AppointmentModule.class }, dependencies = {AppComponent.class})
public interface AppointmentComponent {

    void inject(BookAppointmentActivity activity);

}
