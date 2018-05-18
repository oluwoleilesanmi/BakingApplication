package com.ilesanmi.oluwole.bakingapplication.di.module;

import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.appwidget.AppWidgetManager;
import android.support.v7.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import android.support.v7.widget.LinearLayoutManager;
import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.di.ActivityContext;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainAdapter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailPagerAdapter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepAdapter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetPresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.AppSchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailPresenter;



@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
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
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    MainAdapter provideAdapter() {
        return new MainAdapter(new ArrayList<>());
    }

    @Provides
    DetailPagerAdapter providePagerAdapter() {
        return new DetailPagerAdapter(mActivity.getSupportFragmentManager());
    }

    @Provides
    StepAdapter provideStepAdapter() {
        return new StepAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(mActivity);
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
    StepMvpPresenter<StepMvpView> provideStepPresenter(StepPresenter<StepMvpView> presenter) {
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
