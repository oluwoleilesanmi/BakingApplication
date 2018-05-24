package com.ilesanmi.oluwole.bakingapplication.di.components;

import com.ilesanmi.oluwole.bakingapplication.di.PerService;
import com.ilesanmi.oluwole.bakingapplication.di.module.ServiceModule;
import com.ilesanmi.oluwole.bakingapplication.ui.service.WidgetService;


import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(WidgetService service);

}
