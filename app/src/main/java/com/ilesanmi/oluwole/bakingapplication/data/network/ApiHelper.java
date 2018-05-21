package com.ilesanmi.oluwole.bakingapplication.data.network;



import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.util.List;

import io.reactivex.Flowable;


public interface ApiHelper {

    Flowable<List<Recipe>> getRecipeApiCall();


}
