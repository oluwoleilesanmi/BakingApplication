package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class StepDetailPresenter<V extends StepDetailMvpView> extends BasePresenter<V>
        implements StepDetailMvpPresenter<V> {

    @Inject
    public StepDetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
