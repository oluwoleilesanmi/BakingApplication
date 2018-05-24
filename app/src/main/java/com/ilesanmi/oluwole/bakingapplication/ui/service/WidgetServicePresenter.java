package com.ilesanmi.oluwole.bakingapplication.ui.service;

import android.util.Log;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class WidgetServicePresenter implements WidgetMvpPresenter {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private WidgetMvpService service;

    @Inject
    public WidgetServicePresenter(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {

        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(WidgetMvpService service) {
        this.service = service;
    }

    @Override
    public void onDetach() {
        this.service = null;
    }

    public boolean isServiceAttached() {
        return this.service != null;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public WidgetMvpService getMvpView() {
        return service;
    }

    @Override
    public void onViewPrepared(Boolean isInternetBound) {
        getCompositeDisposable().add(getDataManager()
                .loadRecipes(isInternetBound)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::handleReturnedData, this::handleError));
    }


    private void handleReturnedData(List<Recipe> list) {

        if (!isServiceAttached()) {
            return;
        }
        if (list != null && !list.isEmpty()) {
            int positionClick = getDataManager().getPositionClickedInMainActivity();
            getMvpView().updateViewInActivity((ArrayList<Recipe>) list,positionClick);
        }
    }

    private void handleError(Throwable error) {
        Log.i("TroubleShoot", "Main Activity");
    }


}
