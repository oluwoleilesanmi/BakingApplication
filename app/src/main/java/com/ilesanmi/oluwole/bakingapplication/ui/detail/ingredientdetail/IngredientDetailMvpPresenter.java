package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;

public interface IngredientDetailMvpPresenter <V extends IngredientDetailMvpView>
        extends MvpPresenter<V> {

    void onViewPrepared(Boolean isInternetBound);
}
