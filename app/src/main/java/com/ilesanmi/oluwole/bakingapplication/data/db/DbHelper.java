package com.ilesanmi.oluwole.bakingapplication.data.db;

import java.util.List;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;


import io.reactivex.Flowable;


public interface DbHelper {

    Flowable<List<Recipe>> loadRecipes(boolean isInternetBound);

    void addRecipe(Recipe recipe);

    void clearData();
}
