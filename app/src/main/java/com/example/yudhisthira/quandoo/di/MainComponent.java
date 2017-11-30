package com.example.yudhisthira.quandoo.di;

import com.example.yudhisthira.quandoo.MainActivity;
import com.example.yudhisthira.quandoo.TableActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yudhisthira
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        CustomerPresenterModule.class,
        CustomerModelModule.class,
        TablePresenterModule.class,
        TableModelModule.class,
        NetworkModule.class
})
public interface MainComponent {
    void inject(MainActivity activity);
    void inject(TableActivity activity);
}
