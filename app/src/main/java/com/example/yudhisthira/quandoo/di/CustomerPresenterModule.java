package com.example.yudhisthira.quandoo.di;

import com.example.yudhisthira.quandoo.model.ICustomerModel;
import com.example.yudhisthira.quandoo.presenter.CustomerPresenterImpl;
import com.example.yudhisthira.quandoo.presenter.ICustomerPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudhisthira
 */

@Module
public class CustomerPresenterModule {
    @Provides
    @Singleton
    ICustomerPresenter provideCustomerPresenter(ICustomerModel customerModel) {
        return new CustomerPresenterImpl(customerModel);
    }
}
