package com.acuratechglobal.bulkbilling.application.builder;

import com.acuratechglobal.bulkbilling.utils.rx.AppRxchedulers;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
class RxModule {

  @AppScope
  @Provides
  RxSchedulers provideRxSchedulers() {
    return new AppRxchedulers();
  }
}