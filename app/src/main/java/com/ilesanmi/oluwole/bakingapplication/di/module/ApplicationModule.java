package com.ilesanmi.oluwole.bakingapplication.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.ilesanmi.oluwole.bakingapplication.utils.DbUtils;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.db.AppDbHelper;
import com.ilesanmi.oluwole.bakingapplication.data.db.DbHelper;
import com.ilesanmi.oluwole.bakingapplication.data.db.RecipeDao;
import com.ilesanmi.oluwole.bakingapplication.data.db.RecipeDb;
import com.ilesanmi.oluwole.bakingapplication.data.network.ApiCall;
import com.ilesanmi.oluwole.bakingapplication.data.network.ApiHelper;
import com.ilesanmi.oluwole.bakingapplication.data.network.AppApiHelper;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;

import com.ilesanmi.oluwole.bakingapplication.utils.rx.AppSchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.data.ApplicationDataManager;


@Module
public class ApplicationModule {
    private static final String DATABASE = "database_name";

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

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    RecipeDao provideQuestionDao(RecipeDb recipeDb) {
        return recipeDb.recipeDao();
    }

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return DbUtils.DATABASE_NAME;
    }

    @Provides
    @Singleton
    RecipeDb provideRecipeDao(@ApplicationContext Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, RecipeDb.class, databaseName).build();
    }
}
