package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;

public interface StepDetailMvpPresenter<V extends StepDetailMvpView>
        extends MvpPresenter<V>  {

    void storeVideoDataInSharedPref(String videoUrl, Boolean playWhenReady, Long playBackPosition, int currentWindowIndex);
    void retrieveVideoDataInSharedPref();
}
