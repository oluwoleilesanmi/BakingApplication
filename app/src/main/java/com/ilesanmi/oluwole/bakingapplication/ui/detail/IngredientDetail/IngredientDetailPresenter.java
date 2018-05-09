package com.ilesanmi.oluwole.bakingapplication.ui.detail.IngredientDetail;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class IngredientDetailPresenter<V extends IngredientDetailMvpView> extends BasePresenter<V>
        implements IngredientDetailMvpPresenter<V> {

    @Inject
    public IngredientDetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
