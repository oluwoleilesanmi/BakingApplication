package com.ilesanmi.oluwole.bakingapplication.ui.detail.activity.detailactivity;

import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

/**
 * Created by abayomi on 28/03/2018.
 */

public interface DetailMvpPresenter<V extends MvpView>
        extends MvpPresenter<V> {

    void isIngredientClickedInStep();
    void resetIngredientClickedBooleanPref(Boolean flag);
}
