package com.ilesanmi.oluwole.bakingapplication.di.components;

import dagger.Component;
import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.activity.detailactivity.DetailActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.detail.DetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainActivity;
import com.ilesanmi.oluwole.bakingapplication.di.module.ActivityModule;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail.IngredientDetailFragment;


/**
 * Created by abayomi on 28/03/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(DetailActivity activity);

    void inject(StepDetailFragment fragment);

    void inject(IngredientDetailFragment fragment);

    void inject(DetailFragment fragment);

    //void inject(WidgetActivity activity);

    void inject(StepFragment fragment);

}
