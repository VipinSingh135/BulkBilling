package com.acuratechglobal.bulkbilling.application.builder;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceModule {

    @AppScope
    @Provides
    @Inject
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("BBPrefs", Context.MODE_PRIVATE);
    }

}
