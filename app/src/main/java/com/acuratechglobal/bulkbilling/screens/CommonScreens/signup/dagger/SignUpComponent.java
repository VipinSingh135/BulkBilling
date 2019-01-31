package com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.signup.SignUpActivity;

import dagger.Component;

@SignUpScope
@Component(modules = {SignUpModule.class }, dependencies = {AppComponent.class})
public interface SignUpComponent {

    void inject(SignUpActivity activity);

}
