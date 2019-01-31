package com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.dagger;


import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.selectPlan.SelectPlanActivity;

import dagger.Component;

@SelectPlanScope
@Component(modules = {SelectPlanModule.class }, dependencies = {AppComponent.class})
public interface SelectPlanComponent {

    void inject(SelectPlanActivity activity);

}
