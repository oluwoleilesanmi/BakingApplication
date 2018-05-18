package com.ilesanmi.oluwole.bakingapplication.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.utils.StepsConverter;
import com.ilesanmi.oluwole.bakingapplication.utils.IngredientsConverter;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
@TypeConverters({IngredientsConverter.class, StepsConverter.class})
public abstract class RecipeDb extends RoomDatabase {

  public abstract RecipeDao recipeDao();
}
