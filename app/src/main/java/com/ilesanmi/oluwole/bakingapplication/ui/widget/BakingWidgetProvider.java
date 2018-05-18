package com.ilesanmi.oluwole.bakingapplication.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.ilesanmi.oluwole.bakingapplication.MvpApplication;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.BroadcastReceiverComponent;
import com.ilesanmi.oluwole.bakingapplication.di.components.DaggerBroadcastReceiverComponent;
import com.ilesanmi.oluwole.bakingapplication.di.module.BroadcastReceiverModule;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    @Inject
    WidgetMvpPresenter<WidgetMvpView> mPresenter;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, ArrayList<Recipe> recipes, int positionClicked) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        Intent intent = new Intent(context, WidgetService.class);
        Bundle bundle = new Bundle();

      //  bundle.putParcelableArrayList("Recipe_Array_List", recipes);
        bundle.putInt("Position_Clicked", positionClicked);

        intent.putExtra("Bundle", bundle);

        views.setRemoteAdapter(R.id.widget_list_view, intent);
        views.setTextViewText(R.id.widget_baking_text, getRecipeName(recipes, positionClicked));


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    //called when a widget is created and updated
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        BroadcastReceiverComponent component = DaggerBroadcastReceiverComponent.builder()
                .broadcastReceiverModule(new BroadcastReceiverModule(this))
                .applicationComponent(((MvpApplication) context.getApplicationContext()).getComponent())
                .build();
        component.inject(this);

        int recipeClickedInMainActivity = getSharedPreference("positionClicked", context);

        //There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            mPresenter.onViewPrepared(context, appWidgetManager, appWidgetId, recipeClickedInMainActivity);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static String getRecipeName(ArrayList<Recipe> recipe, int clickedRecipe) {
        return recipe.get(clickedRecipe).getName();
    }

    public static int getSharedPreference(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }

}

