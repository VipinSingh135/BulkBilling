package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentMyFavourites.PatFavouritesFragment;

import dagger.Component;

@FavouritesScope
@Component(modules = {FavouritesModule.class }, dependencies = {AppComponent.class})
public interface PatFavouritesComponent {

    void inject(PatFavouritesFragment activity);

}
