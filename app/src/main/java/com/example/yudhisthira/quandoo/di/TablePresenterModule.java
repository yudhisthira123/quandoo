package com.example.yudhisthira.quandoo.di;

import com.example.yudhisthira.quandoo.model.ITableModel;
import com.example.yudhisthira.quandoo.presenter.ITablePresenter;
import com.example.yudhisthira.quandoo.presenter.TablePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudhisthira
 */

@Module
public class TablePresenterModule {
    @Provides
    @Singleton
    ITablePresenter provideTablePresenter(ITableModel tableModel) {
        return new TablePresenterImpl(tableModel);
    }
}
