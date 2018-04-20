package com.ilesanmi.oluwole.bakingapplication.di.module;

import android.app.Application;
import android.content.Context;

import com.ilesanmi.oluwole.bakingapplication.data.ApplicationDataManager;
import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.network.ApiCall;
import com.ilesanmi.oluwole.bakingapplication.data.network.ApiHelper;
import com.ilesanmi.oluwole.bakingapplication.data.network.AppApiHelper;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.AppSchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApplicationDataManager appDataManager) {
        return appDataManager;
    }

    /*
    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }
    */
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiCall provideApiCall() {
        return ApiCall.Factory.create();
    }


}
