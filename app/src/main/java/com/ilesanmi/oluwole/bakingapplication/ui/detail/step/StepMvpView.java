package com.ilesanmi.oluwole.bakingapplication.ui.detail.step;


import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

import java.util.ArrayList;

public interface StepMvpView extends MvpView {
    void updateViewInActivity(ArrayList<Recipe> recipeList, int positionClick);
}
