package com.ilesanmi.oluwole.bakingapplication.di.components;

import android.app.Application;
import android.content.Context;

import com.ilesanmi.oluwole.bakingapplication.MvpApplication;
import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;
import com.ilesanmi.oluwole.bakingapplication.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by abayomi on 28/03/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApplication app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
