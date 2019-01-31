package com.acuratechglobal.bulkbilling.application.builder;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context= context;
    }

    @Provides
    @AppScope
    public Context getContext() {
        return context;
    }
}
