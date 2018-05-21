package com.ilesanmi.oluwole.bakingapplication.data;

import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Flowable;
import android.content.Context;

import com.ilesanmi.oluwole.bakingapplication.data.db.DbHelper;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.data.network.ApiHelper;
import com.ilesanmi.oluwole.bakingapplication.data.pref.PreferenceHelper;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;

/**
 * Created by abayomi on 19/03/2018.
 */
@Singleton
public class ApplicationDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferenceHelper mPreferenceHelper;
    private final ApiHelper mApiHelper;
    List<Recipe> caches;

    @Inject
    public ApplicationDataManager(@ApplicationContext Context context, DbHelper dbHelper, ApiHelper apiHelper,
                                  PreferenceHelper preferenceHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferenceHelper = preferenceHelper;
        mApiHelper = apiHelper;

        caches = new ArrayList<>();
    }

    @Override
    public Flowable<List<Recipe>> getRecipeApiCall() {
        return mApiHelper.getRecipeApiCall();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        mDbHelper.addRecipe(recipe);
    }

//Database uses list internet uses arraylist
   public Flowable<List<Recipe>> refreshData() {
        return getRecipeApiCall().doOnNext(list -> {
            // Clear cache
            caches.clear();
            // Clear data in local storage
            mDbHelper.clearData();
            // Break the list up into individual recipes and then concatenate them back.
        }).flatMap(Flowable::fromIterable).doOnNext(recipe -> {
            caches.add(recipe);
            addRecipe(recipe);
        }).toList().toFlowable();
   }


    @Override
    public Flowable<List<Recipe>> loadRecipes(boolean isInternetBound) {
        if (isInternetBound) {
            return refreshData();
        } else {
            if (caches.size() > 0) {
                // if cache is available, return it immediately
                return Flowable.just(caches);
            } else {
                // else return data from local storage
                return mDbHelper.loadRecipes(false)
                        .take(1)
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(recipe -> caches.add(recipe))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        .switchIfEmpty(
                                refreshData()); // If local data is empty, fetch from remote source instead.
            }
        }
    }

    public Flowable<Recipe> getRecipe(final int recipeId) {
        return Flowable.fromIterable(caches).filter(recipe -> recipe.getId() == recipeId);

    }

    @Override public void clearData() {
        caches.clear();
        mDbHelper.clearData();
    }

    @Override
    public void setPositionClickedInMainActivity(int positionClickedOnMainActivity) {
        mPreferenceHelper.setPositionClickedInMainActivity(positionClickedOnMainActivity);
    }

    @Override
    public int getPositionClickedInMainActivity() {
        return mPreferenceHelper.getPositionClickedInMainActivity();
    }

    @Override
    public void setPositionClickedInStepFragment(int positionClickedInStepFragment) {
        mPreferenceHelper.setPositionClickedInStepFragment(positionClickedInStepFragment);
    }

    @Override
    public int getPositionClickedInStepFragment() {
     return mPreferenceHelper.getPositionClickedInStepFragment();
    }
}
