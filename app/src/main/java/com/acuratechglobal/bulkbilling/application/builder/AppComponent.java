package com.acuratechglobal.bulkbilling.application.builder;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Component;

@AppScope
@Component(modules = {NetworkModule.class,GsonModule.class, SharedPreferenceModule.class,
        AppModule.class, RxModule.class, RestApiServiceModule.class})
public interface AppComponent {

    RxSchedulers rxSchedulers();
    Api apiService();
    SharedPrefsUtil sharedPrefsUtil();
}
