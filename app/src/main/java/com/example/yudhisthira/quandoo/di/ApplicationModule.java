package com.example.yudhisthira.quandoo.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudhisthira
 */

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context getApplicationContext(Application application) {
        return application.getBaseContext();
    }
}
