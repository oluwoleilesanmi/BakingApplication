package com.ilesanmi.oluwole.bakingapplication.data.network;

import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import java.util.ArrayList;
import io.reactivex.Observable;

public interface ApiHelper {

    Observable<ArrayList<Recipe>> getRecipeApiCall();


}
