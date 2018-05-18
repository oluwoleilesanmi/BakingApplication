package com.ilesanmi.oluwole.bakingapplication.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ilesanmi.oluwole.bakingapplication.MvpApplication;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.DaggerActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.di.module.ActivityModule;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;

import java.util.ArrayList;

import butterknife.Unbinder;

/**
 * Created by abayomi on 19/03/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(MvpApplication.get(this).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void onFragmentAttached() {

    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    public void updateViewInActivity(ArrayList<Recipe> recipeList) {

    }

    protected abstract void setUp();

}
