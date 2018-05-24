package com.ilesanmi.oluwole.bakingapplication.ui.service;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.util.ArrayList;

public interface WidgetMvpService {
    void updateViewInActivity(ArrayList<Recipe> recipeList, int position);
}
