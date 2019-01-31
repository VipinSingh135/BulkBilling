package com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.changePassword.ChangePassActivity;

import dagger.Component;

@ChangePassScope
@Component(modules = {ChangePassModule.class }, dependencies = {AppComponent.class})
public interface ChangePassComponent {

    void inject(ChangePassActivity activity);

}
