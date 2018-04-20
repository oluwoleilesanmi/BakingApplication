package com.ilesanmi.oluwole.bakingapplication.data;

import android.content.Context;

import com.ilesanmi.oluwole.bakingapplication.data.network.ApiHelper;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by abayomi on 19/03/2018.
 */
@Singleton
public class ApplicationDataManager implements DataManager {

    private final Context mContext;
    //private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public ApplicationDataManager(@ApplicationContext Context context, ApiHelper apiHelper) {
        mContext = context;
        //mDbHelper = dbHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<ArrayList<Recipe>> getRecipeApiCall() {
        return mApiHelper.getRecipeApiCall();
    }
}
