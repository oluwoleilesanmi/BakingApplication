package com.ilesanmi.oluwole.bakingapplication.di.components;

import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.di.module.BroadcastReceiverModule;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.BakingWidgetProvider;

import dagger.Component;
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = BroadcastReceiverModule.class)
public interface BroadcastReceiverComponent {

    void inject(BakingWidgetProvider broadcastReceiver);


}

