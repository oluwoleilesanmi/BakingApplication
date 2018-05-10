package com.ilesanmi.oluwole.bakingapplication.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;

import java.util.ArrayList;

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(getApplicationContext(),intent);
    }
}

class RecipeRemoteViewsFactory implements  RemoteViewsService.RemoteViewsFactory{
    private Context mContext;
    private int positionClicked;
    private ArrayList<Recipe> allRecipes;
    private Recipe selectedRecipe;
    private Bundle bundle;

    public RecipeRemoteViewsFactory(Context context, Intent intent){
        mContext = context;
        bundle = intent.getBundleExtra("Bundle");

        allRecipes = bundle.getParcelableArrayList("Recipe_Array_List");
        positionClicked = bundle.getInt("Position_Clicked", -1);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (positionClicked != -1) {
            if (allRecipes != null || allRecipes.size() != 0){
                selectedRecipe = allRecipes.get(positionClicked);
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (selectedRecipe.getIngredients() == null) return 0;
        return selectedRecipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        if (selectedRecipe.getIngredients() == null || selectedRecipe.getIngredients().size() == 0) {
            return null;
        }
        Recipe.Ingredient ingredient = selectedRecipe.getIngredients().get(i);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_widget);
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