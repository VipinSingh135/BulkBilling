package com.acuratechglobal.bulkbilling.screens.CommonScreens.login.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.login.LoginActivity;

import dagger.Component;

@LoginScope
@Component(modules = {LoginModule.class }, dependencies = {AppComponent.class})
public interface LoginComponent {

    void inject(LoginActivity activity);

}
