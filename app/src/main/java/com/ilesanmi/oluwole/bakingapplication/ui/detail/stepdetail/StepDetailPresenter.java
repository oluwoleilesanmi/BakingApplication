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


    @Override
    public void storeVideoDataInSharedPref(String videoUrl, Boolean playWhenReady, Long playBackPosition, int currentWindowIndex) {
        getDataManager().setVideoUrlOfStepDetailFragment(videoUrl);
        getDataManager().setPlayWhenReadyOfStepDetailFragment(playWhenReady);
        getDataManager().setPlayBackPositionOfStepDetailFragment(playBackPosition);
        getDataManager().setCurrentWindowIndexOfStepDetailFragment(currentWindowIndex);
    }
    @Override
    public void retrieveVideoDataInSharedPref() {
        getMvpView().updateVideoView(getDataManager().getVideoUrlOfStepDetailFragment(),getDataManager().getPlayWhenReadyOfStepDetailFragment(),
                getDataManager().getPlayBackPositionOfStepDetailFragment(),getDataManager().getCurrentWindowIndexOfStepDetailFragment());
    }


}
