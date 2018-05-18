package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import android.util.Log;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class IngredientDetailPresenter<V extends IngredientDetailMvpView> extends BasePresenter<V>
        implements IngredientDetailMvpPresenter<V> {

    @Inject
    public IngredientDetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
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
        Log.i("Hello","Nigeria");
    }


}
