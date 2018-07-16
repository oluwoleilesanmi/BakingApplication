package com.ilesanmi.oluwole.bakingapplication.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.utils.AppConstants;
import com.ilesanmi.oluwole.bakingapplication.utils.StringProcessor;
import com.ilesanmi.oluwole.bakingapplication.utils.NetworkUtils;


import java.io.IOException;
import java.util.ArrayList;

public class WidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(getApplicationContext(), intent);
    }
}

class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private int positionM = 0;
    private Recipe selectedRecipe;
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
    }


    @Override
    public void onCreate() {

    }

    //placed network operation below in an asyncTask thread app crashes.
    //Removed network operation below from the asyncTask thread app does-nt crash.
    //The observation noted above should be investigated to confirm reason.
    public void onDataSetChanged() {
        String jsonInStringForm = "";
        try {
            jsonInStringForm = NetworkUtils.getResponseFromHttpUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        positionM = context.getSharedPreferences(AppConstants.SHAREDPREF_NAME,Context.MODE_PRIVATE)
                .getInt("PREF_KEY_ACTIVITY_MAIN_POSITION_CLICKED",0);
        recipes = (ArrayList<Recipe>) StringProcessor.process(jsonInStringForm);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

        if (recipes != null || recipes.size() != 0) {
            selectedRecipe = recipes.get(positionM);
        }

        if (selectedRecipe.getIngredients() == null) return 0;
        return selectedRecipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        if (selectedRecipe.getIngredients() == null || selectedRecipe.getIngredients().size() == 0) {
            return null;
        }

        Recipe.Ingredient ingredient = selectedRecipe.getIngredients().get(i);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_list);
        views.setTextViewText(R.id.tv_ingredient_widget, ingredient.getIngredient());
        //views.setTextViewText(R.id.tv_quantity_widget, ingredient.getQuantity() + " " + ingredient.getMeasure());
        return views;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
