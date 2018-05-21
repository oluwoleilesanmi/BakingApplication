package com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail;

import com.google.android.exoplayer2.ExoPlayer;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.MvpView;

import java.util.ArrayList;

public interface StepDetailMvpView extends MvpView, ExoPlayer.EventListener {

    void updateViewInActivity(ArrayList<Recipe> recipeList, int positionM, int positionS);

    void updateFragment();
}
