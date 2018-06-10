package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;


public interface DetailMvpPresenterFrag<V extends DetailMvpViewFrag> extends MvpPresenter<V> {

    void onViewPrepared(Boolean isInternetBound);
}
