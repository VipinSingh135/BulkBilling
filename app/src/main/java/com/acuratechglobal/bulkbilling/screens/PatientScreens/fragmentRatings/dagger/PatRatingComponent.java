package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentRatings.PatRatingFragment;

import dagger.Component;

@PatRatingScope
@Component(modules = {PatRatingModule.class }, dependencies = {AppComponent.class})
public interface PatRatingComponent {

    void inject(PatRatingFragment activity);

}
