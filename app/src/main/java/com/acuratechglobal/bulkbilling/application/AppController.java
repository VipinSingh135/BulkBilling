package com.acuratechglobal.bulkbilling.application;

import android.app.Application;

import com.acuratechglobal.bulkbilling.application.builder.AppComponent;
import com.acuratechglobal.bulkbilling.application.builder.AppModule;
import com.acuratechglobal.bulkbilling.application.builder.DaggerAppComponent;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.leakcanary.LeakCanary;

import timber.log.BuildConfig;
import timber.log.Timber;

public class AppController extends Application{

    private static AppComponent appComponent;
    private static String userID;
    private static int userType;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();
        initialiseFasebookSdk();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

    }

    private void initialiseFasebookSdk() {
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        AppController.userID = userID;
    }


    public static void setAppComponent(AppComponent appComponent) {
        AppController.appComponent = appComponent;
    }

    public static int getUserType() {
        return userType;
    }

    public static void setUserType(int userType) {
        AppController.userType = userType;
    }
}
