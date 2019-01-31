package com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.forgotPassword.ForgotPassActivity;

import dagger.Component;

@ForgotPassScope
@Component(modules = {ForgotPassModule.class }, dependencies = {AppComponent.class})
public interface ForgotPassComponent {

    void inject(ForgotPassActivity activity);

}
