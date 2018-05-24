package com.ilesanmi.oluwole.bakingapplication.di.components;

import dagger.Component;
import javax.inject.Singleton;
import android.app.Application;
import android.content.Context;
import com.ilesanmi.oluwole.bakingapplication.MvpApplication;
import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;
import com.ilesanmi.oluwole.bakingapplication.di.module.ApplicationModule;


/**
 * Created by abayomi on 28/03/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApplication app);
    //void inject(WidgetService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
