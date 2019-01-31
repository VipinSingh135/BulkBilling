package com.acuratechglobal.bulkbilling.application.builder;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.api.WebConstants;
import com.acuratechglobal.bulkbilling.utils.rx.RxSchedulers;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class RestApiServiceModule {

  @AppScope
  @Provides
  public Api taskApi(Gson gson, OkHttpClient okHttpClient, RxSchedulers rxSchedulers) {
    Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(
      RxJava2CallAdapterFactory.createWithScheduler(rxSchedulers.network()))
      .addConverterFactory(GsonConverterFactory.create(gson))
      .baseUrl(WebConstants.ACTION_BASE_URL)
      .client(okHttpClient)
      .build();
    return retrofit.create(Api.class);
  }
}