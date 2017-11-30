package com.example.yudhisthira.quandoo.di;

import com.example.yudhisthira.quandoo.model.CustomerListClient;
import com.example.yudhisthira.quandoo.model.CustomerLocalModel;
import com.example.yudhisthira.quandoo.model.CustomerModelImpl;
import com.example.yudhisthira.quandoo.model.ICustomerModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by yudhisthira
 */

@Module
public class CustomerModelModule {

    @Provides
    @Singleton
    CustomerLocalModel provideCustomerLocalModel() {
        return new CustomerLocalModel();
    }

    @Provides
    @Singleton
    CustomerListClient provideCustomerListClient(Retrofit retrofit) {
        return retrofit.create(CustomerListClient.class);
    }

    @Provides
    @Singleton
    ICustomerModel provideCustomerModel(CustomerLocalModel customerLocalModel, CustomerListClient customerListClient) {
        return new CustomerModelImpl(customerLocalModel, customerListClient);
    }
}
