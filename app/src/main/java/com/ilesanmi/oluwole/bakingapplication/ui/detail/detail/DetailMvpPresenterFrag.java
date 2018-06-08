package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;

import java.util.ArrayList;


public interface DetailMvpPresenterFrag<V extends DetailMvpViewFrag> extends MvpPresenter<V> {

    void onViewPrepared(Boolean isInternetBound);
}
