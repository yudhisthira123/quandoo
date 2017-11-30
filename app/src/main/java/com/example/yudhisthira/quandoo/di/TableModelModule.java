package com.example.yudhisthira.quandoo.di;

import com.example.yudhisthira.quandoo.model.ITableModel;
import com.example.yudhisthira.quandoo.model.TableListClient;
import com.example.yudhisthira.quandoo.model.TableLocalModel;
import com.example.yudhisthira.quandoo.model.TableModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by yudhisthira
 */

@Module
public class TableModelModule {

    @Provides
    @Singleton
    TableLocalModel provideCustomerLocalModel() {
        return new TableLocalModel();
    }

    @Provides
    @Singleton
    TableListClient provideCustomerListClient(Retrofit retrofit) {
        return retrofit.create(TableListClient.class);
    }

    @Provides
    @Singleton
    ITableModel provideTableModel(TableLocalModel tableLocalModel, TableListClient tableListClient) {
        return new TableModelImpl(tableLocalModel, tableListClient);
    }
}
