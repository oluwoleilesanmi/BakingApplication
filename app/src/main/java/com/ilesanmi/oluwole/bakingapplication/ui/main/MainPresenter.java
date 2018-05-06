package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.util.Log;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by abayomi on 28/03/2018.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    //Migrate the below observable to SQL-Database(Room)
    @Override
    public void onViewPrepared() {
    //getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getRecipeApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ArrayList<Recipe>>() {

                    @Override
                    public void accept(ArrayList<Recipe> recipes) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (recipes != null) {
                            getMvpView().updateViewInActivity(recipes);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("he 3", "Nigeria");
                    }
                }));
    }

}
