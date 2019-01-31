package com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.resetPassword.ResetPassActivity;

import dagger.Component;

@ResetPassScope
@Component(modules = {ResetPassModule.class }, dependencies = {AppComponent.class})
public interface ResetPassComponent {

    void inject(ResetPassActivity activity);

}
