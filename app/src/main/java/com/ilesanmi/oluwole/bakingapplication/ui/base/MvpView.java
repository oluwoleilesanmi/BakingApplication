package com.ilesanmi.oluwole.bakingapplication.ui.base;

import android.support.annotation.StringRes;

import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;

import java.util.ArrayList;

/**
 * Created by abayomi on 28/03/2018.
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(String message);

    void showMessage(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void updateViewInActivity(ArrayList<Recipe> recipeList);

}
