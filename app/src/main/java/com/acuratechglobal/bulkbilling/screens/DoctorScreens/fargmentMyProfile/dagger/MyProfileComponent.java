package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fargmentMyProfile.MyProfileDocFragment;

import dagger.Component;

@MyProfileScope
@Component(modules = {MyProfileModule.class }, dependencies = {AppComponent.class})
public interface MyProfileComponent {

    void inject(MyProfileDocFragment activity);

}
