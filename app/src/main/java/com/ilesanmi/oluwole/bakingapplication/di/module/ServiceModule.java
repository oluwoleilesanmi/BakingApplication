package com.ilesanmi.oluwole.bakingapplication.di.module;

import android.app.Service;

import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.di.PerService;
import com.ilesanmi.oluwole.bakingapplication.ui.service.WidgetMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.service.WidgetServicePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.AppSchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ServiceModule {
    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    WidgetMvpPresenter provideWidgetPresenter(WidgetServicePresenter presenter) {
        return presenter;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
