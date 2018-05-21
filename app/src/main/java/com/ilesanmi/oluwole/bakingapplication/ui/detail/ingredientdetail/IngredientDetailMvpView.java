package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

import java.util.ArrayList;

public interface IngredientDetailMvpView extends MvpView {

    void updateViewInActivity(ArrayList<Recipe> recipeList, int positionClick);
}
