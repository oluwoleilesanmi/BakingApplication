package com.ilesanmi.oluwole.bakingapplication.ui.base;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.util.ArrayList;

/**
 * Created by abayomi on 28/03/2018.
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    boolean isNetworkConnected();

    void updateViewInActivity(ArrayList<Recipe> recipeList);

}
