package com.example.yudhisthira.quandoo.di;

import com.example.yudhisthira.quandoo.model.CustomerLocalModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudhisthira
 */

@Module
public class CustomerLocalModelModule {
    @Provides
    @Singleton
    CustomerLocalModel provideCutomerLocalModel() {
        return new CustomerLocalModel();
    }
}

