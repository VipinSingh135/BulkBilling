package com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.dagger;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.SettingsFragment;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core.SettingsModel;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core.SettingsPresenter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentSetting.core.SettingsView;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class SettingsModule {

    private final SettingsFragment fragment;

    public SettingsModule(SettingsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @SettingsScope
    public SettingsView view(SharedPrefsUtil prefs) {
        return new SettingsView(fragment,prefs);
    }

    @Provides
    @SettingsScope
    public SettingsPresenter presenter(SettingsView view, SettingsModel model) {
        CompositeDisposable compositeSubscription = new CompositeDisposable();
        return new SettingsPresenter(view, model, compositeSubscription);
    }

    @Provides
    @SettingsScope
    SettingsModel model(Api taskApi) {
        return new SettingsModel(fragment, taskApi);
    }
}
