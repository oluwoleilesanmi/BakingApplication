package com.ilesanmi.oluwole.bakingapplication.ui.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.utils.NetworkUtils;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.AsyTask;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService  {


    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(getApplicationContext(), intent);
    }
}

class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = "SyncService";

    private Context mContext;
    private int positionClicked = 0;
    private ArrayList<Recipe> allRecipes = new ArrayList<>();
    private Recipe selectedRecipe;
    private Bundle bundle;


    public RecipeRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;



    }


//    @SuppressLint("StaticFieldLeak")
//    @Override
//    public void onCreate() {
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... voids) {
//                String bakingAppJson = "";
//                try {
//                    bakingAppJson = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildBakingAppUrl());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return bakingAppJson;
//            }
//
//            @Override
//            protected void onPostExecute(String resultJson) {
//                ArrayList<Recipe> r = (ArrayList<Recipe>) AsyTask.processResult(resultJson);
//                Log.i("cee",Integer.toString(r.size()));
//                allRecipes = (ArrayList<Recipe>) AsyTask.processResult(resultJson);
//                Log.i("cee",allRecipes.get(1).getName());
//                Log.i("cee",Integer.toString(r.size()));
//            }
//        }.execute();


    @Override
    public void onCreate() {

    }

    //i put the network operation on a thread it didnt work
    //i removed it for the asynctask it works
    public void onDataSetChanged() {
        String bakingAppJson = "";
        try {
            bakingAppJson = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildBakingAppUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        allRecipes = (ArrayList<Recipe>) AsyTask.processResult(bakingAppJson);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

            if (allRecipes != null || allRecipes.size() != 0) {
                selectedRecipe = allRecipes.get(1);
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
