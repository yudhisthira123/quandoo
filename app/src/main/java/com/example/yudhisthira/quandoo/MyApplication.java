package com.example.yudhisthira.quandoo;

import android.app.Application;

import com.example.yudhisthira.quandoo.di.ApplicationModule;
import com.example.yudhisthira.quandoo.di.DaggerMainComponent;
import com.example.yudhisthira.quandoo.di.MainComponent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.orhanobut.hawk.Hawk;

/**
 * Created by yudhisthira
 */

public class MyApplication extends Application {
    private MainComponent mainComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        init();
        initBackgroundService();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    private void init() {
        mainComponent = DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Hawk.init(this).build();
    }

    private void initBackgroundService() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if(ConnectionResult.SUCCESS == result) {
            GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(this);

            gcmNetworkManager.schedule(BackgroundService.create());
        }
        else {

        }
    }

}
