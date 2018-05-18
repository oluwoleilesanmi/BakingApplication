package com.ilesanmi.oluwole.bakingapplication.di.module;

import dagger.Module;
import dagger.Provides;

import android.content.BroadcastReceiver;
import io.reactivex.disposables.CompositeDisposable;
import com.ilesanmi.oluwole.bakingapplication.di.PerActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetMvpView;
import com.ilesanmi.oluwole.bakingapplication.ui.widget.WidgetPresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.AppSchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;



@Module
public class BroadcastReceiverModule {

    private final BroadcastReceiver mBroadcastReceiver;


    public BroadcastReceiverModule(BroadcastReceiver broadcastReceiver) {
        mBroadcastReceiver = broadcastReceiver;
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
    @PerActivity
    WidgetMvpPresenter<WidgetMvpView> provideWidgetPresenter(WidgetPresenter<WidgetMvpView> presenter) {
        return presenter;
    }
}