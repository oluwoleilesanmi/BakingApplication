package com.ilesanmi.oluwole.bakingapplication.ui.detail.step;

import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;

public interface StepMvpPresenter<V extends StepMvpView>
        extends MvpPresenter<V>  {

    void onViewPrepared(Boolean isInternetBound);
    void onPressed(int position);
}
