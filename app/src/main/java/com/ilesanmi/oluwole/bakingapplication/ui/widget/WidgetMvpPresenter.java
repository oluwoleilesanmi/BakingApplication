package com.ilesanmi.oluwole.bakingapplication.ui.widget;

import android.content.Context;
import android.appwidget.AppWidgetManager;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;



public interface WidgetMvpPresenter <V extends MvpView>
        extends MvpPresenter<V> {
    void onViewPrepared(Context context, AppWidgetManager appWidgetManager,
                        int appWidgetId, int position);
}
