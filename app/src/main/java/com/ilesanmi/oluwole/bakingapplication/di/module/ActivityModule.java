package com.ilesanmi.oluwole.bakingapplication.di.module;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.ActivityContext;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;
import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailAdapter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainAdapter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetPresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.AppSchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
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
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    MainAdapter provideAdapter() {
        return new MainAdapter(new ArrayList<Recipe>());
    }

    @Provides
    DetailAdapter provideIngredentAndStepAdapter() {
        return new DetailAdapter(new ArrayList<Recipe>());
    }

    @Provides
    LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    StepDetailMvpPresenter<StepDetailMvpView> provideStepDetailPresenter(StepDetailPresenter<StepDetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailMvpView> provideDetailPresenter(DetailPresenter<DetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    WidgetMvpPresenter<WidgetMvpView> provideWidgetPresenter(WidgetPresenter<WidgetMvpView> presenter) {
        return presenter;
    }

    @Provides
    AppWidgetManager provideAppWidgetManager(@ApplicationContext Context context) {
        return AppWidgetManager.getInstance(context);
    }




}
