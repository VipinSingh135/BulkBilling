package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentMyRatings.DocRatingsFragment;

import dagger.Component;

@DocRatingsScope
@Component(modules = {DocRatingsModule.class }, dependencies = {AppComponent.class})
public interface DocRatingsComponent {

    void inject(DocRatingsFragment activity);

}
