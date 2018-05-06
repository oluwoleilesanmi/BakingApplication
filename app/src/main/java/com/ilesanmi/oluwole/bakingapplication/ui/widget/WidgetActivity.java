package com.ilesanmi.oluwole.bakingapplication.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;

import javax.inject.Inject;


public class WidgetActivity extends BaseActivity implements WidgetMvpView {

    @Inject
    WidgetMvpPresenter<WidgetMvpView> mPresenter;

    @Inject
    AppWidgetManager appWidgetManager;

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    int recipeClickedInMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);
        setContentView(R.layout.activity_widget);

        getActivityComponent().inject(this);
//        TextView textView = findViewById(R.id.t_text);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
       recipeClickedInMainActivity = getSharedPreference("positionClicked", this);

     //   mPresenter.onViewPrepared(getApplicationContext(),appWidgetManager,appWidgetId,recipeClickedInMainActivity);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();

    }

    public static int getSharedPreference(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }
}
