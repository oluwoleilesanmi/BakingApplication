package com.ilesanmi.oluwole.bakingapplication.data.db;

import java.util.List;
import io.reactivex.Flowable;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import com.ilesanmi.oluwole.bakingapplication.utils.DbUtils;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;




@Dao
public interface RecipeDao {
    @Query("SELECT * FROM " + DbUtils.RECIPE_TABLE_NAME)
    Flowable<List<Recipe>> getAllRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

    @Query("DELETE FROM " + DbUtils.RECIPE_TABLE_NAME)
    void deleteAll();


}
