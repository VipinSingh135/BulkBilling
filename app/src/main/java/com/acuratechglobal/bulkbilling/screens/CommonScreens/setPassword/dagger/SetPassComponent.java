package com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.SetPassActivity;

import dagger.Component;

@SetPassScope
@Component(modules = {SetPassModule.class }, dependencies = {AppComponent.class})
public interface SetPassComponent {

    void inject(SetPassActivity activity);

}
