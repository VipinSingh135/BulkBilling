package com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.PhoneVerifyActivity;

import dagger.Component;

@PhoneVerifyScope
@Component(modules = {PhoneVerifyModule.class }, dependencies = {AppComponent.class})
public interface PhoneVerifyComponent {

    void inject(PhoneVerifyActivity activity);

}
