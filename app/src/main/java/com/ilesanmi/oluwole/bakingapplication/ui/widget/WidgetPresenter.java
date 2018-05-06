package com.ilesanmi.oluwole.bakingapplication.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.util.Log;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class WidgetPresenter<V extends WidgetMvpView> extends BasePresenter<V>
        implements WidgetMvpPresenter<V> {

    @Inject
    public WidgetPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    //Migrate the below observable to SQL-Database(Room)
    @Override
    public void onViewPrepared(final Context context, final AppWidgetManager appWidgetManager,
                               final int appWidgetId, final int position) {
        //getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getRecipeApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<ArrayList<Recipe>>() {

                    @Override
                    public void accept(ArrayList<Recipe> recipes) throws Exception {
                        if (recipes != null) {
                            Log.i("TroubleShoot", "Rxjava5");
                           BakingWidgetProvider.updateAppWidget(context,appWidgetManager,appWidgetId,recipes,position);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("TroubleShoot", "Rxjava");
                    }
                }));
    }
}
