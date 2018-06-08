package com.ilesanmi.oluwole.bakingapplication.data.pref;



public interface PreferenceHelper {

    void setPositionClickedInMainActivity(int positionClickedOnMainActivity);
    int getPositionClickedInMainActivity();

    void setPositionClickedInStepFragment(int positionClickedInStepFragment);
    int getPositionClickedInStepFragment();

    void setIngredientClickInStepFragment(Boolean flag);
    Boolean getIngredientClickInStepFragment();
}
