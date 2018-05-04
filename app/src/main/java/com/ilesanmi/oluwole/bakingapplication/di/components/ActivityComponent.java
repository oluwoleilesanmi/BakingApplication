
package com.ilesanmi.oluwole.bakingapplication.di.components;

import com.ilesanmi.oluwole.bakingapplication.di.module.ActivityModule;
import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.BakingWidgetProvider;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetActivity;

import dagger.Component;

/**
 * Created by abayomi on 28/03/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(StepDetailFragment fragment);

    void inject(DetailActivity activity);

    void inject(WidgetActivity activity);

}
