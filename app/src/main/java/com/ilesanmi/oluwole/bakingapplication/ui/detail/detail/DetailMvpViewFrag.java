package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

import java.util.ArrayList;

public interface DetailMvpViewFrag extends MvpView {
    void updateViewInActivity(ArrayList<Recipe> recipeList, int positionM, int positionS);

}
