package com.ilesanmi.oluwole.bakingapplication.ui.main;


import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by abayomi on 28/03/2018.
 */

public interface MainMvpView extends MvpView {

    void updateViewInActivity(ArrayList<Recipe> recipeList);
}
