package com.ilesanmi.oluwole.bakingapplication.ui.main;

import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

/**
 * Created by abayomi on 28/03/2018.
 */

public interface MainMvpPresenter<V extends MvpView>
        extends MvpPresenter<V> {

    void onViewPrepared(Boolean isInternetBound);
    void onPressed(int position);

}
