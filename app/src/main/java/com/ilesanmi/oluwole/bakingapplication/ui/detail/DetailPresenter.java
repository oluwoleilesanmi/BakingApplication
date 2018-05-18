package com.ilesanmi.oluwole.bakingapplication.ui.detail;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BasePresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by abayomi on 28/03/2018.
 */

public class DetailPresenter<V extends DetailMvpView> extends BasePresenter<V>
        implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
