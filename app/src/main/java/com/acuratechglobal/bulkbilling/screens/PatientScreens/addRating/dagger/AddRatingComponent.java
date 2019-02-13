package com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.addRating.AddRatingActivity;

import dagger.Component;

@AddRatingScope
@Component(modules = {AddRatingModule.class }, dependencies = {AppComponent.class})
public interface AddRatingComponent {

    void inject(AddRatingActivity activity);

}
