package com.ilesanmi.oluwole.bakingapplication.data.db;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Flowable;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;


public class AppDbHelper implements DbHelper{
    private RecipeDao recipeDao;

    @Inject
    public AppDbHelper(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    public Flowable<List<Recipe>> loadRecipes(boolean internetBound) {
        //this line links the observable to its data-source (database)
        //get data
        return recipeDao.getAllRecipes();
    }


    @Override
    public void addRecipe(Recipe recipe) {
        //this line links the observable to its data-source (database)
        //insert data
        recipeDao.insert(recipe);
    }

    @Override
    public void clearData() {
        //this line links the observable to its data-source (database)
        // Clear old data
        recipeDao.deleteAll();
    }




}
