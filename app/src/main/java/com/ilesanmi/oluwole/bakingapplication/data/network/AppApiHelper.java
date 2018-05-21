package com.ilesanmi.oluwole.bakingapplication.data.network;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class AppApiHelper implements ApiHelper {

    ApiCall mApiCall;

    @Inject
    public AppApiHelper(ApiCall apiCall) {
        mApiCall = apiCall;
    }

    @Override
    public Flowable<List<Recipe>> getRecipeApiCall() {
        return mApiCall.getRecipeApiCall();
    }

}
