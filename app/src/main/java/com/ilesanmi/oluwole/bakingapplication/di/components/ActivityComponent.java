
package com.ilesanmi.oluwole.bakingapplication.di.components;

import com.ilesanmi.oluwole.bakingapplication.di.module.ActivityModule;
import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by abayomi on 28/03/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);


}
