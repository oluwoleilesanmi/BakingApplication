package com.ilesanmi.oluwole.bakingapplication.ui.base;

/**
 * Created by abayomi on 28/03/2018.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}
