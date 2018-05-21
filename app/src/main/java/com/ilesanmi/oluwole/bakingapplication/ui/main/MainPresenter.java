package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.util.Log;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

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

    @Override
    public void onViewPrepared(Boolean isInternetBound) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .loadRecipes(isInternetBound)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::handleReturnedData, this::handleError, () -> getMvpView().hideLoading()));
    }

    @Override
    public void onPressed(int position) {
        getDataManager().setPositionClickedInMainActivity(position);
    }

    private void handleReturnedData(List<Recipe> list) {
        //view.stopLoadingIndicator();
        if (!isViewAttached()) {
            return;
        }
        if (list != null && !list.isEmpty()) {
            getMvpView().updateViewInActivity((ArrayList<Recipe>) list);
        }
    }

    private void handleError(Throwable error) {
        Log.i("TroubleShoot","Main Activity");
    }






}
