package com.ilesanmi.oluwole.bakingapplication;

import android.app.Application;
import android.content.Context;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.di.components.ApplicationComponent;
import com.ilesanmi.oluwole.bakingapplication.di.components.DaggerApplicationComponent;
import com.ilesanmi.oluwole.bakingapplication.di.module.ApplicationModule;

import javax.inject.Inject;


public class MvpApplication extends Application {
    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    public static MvpApplication get(Context context) {
        return (MvpApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

    }



    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
