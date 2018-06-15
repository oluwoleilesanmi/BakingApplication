package com.ilesanmi.oluwole.bakingapplication.data.pref;



public interface PreferenceHelper {

    void setPositionClickedInMainActivity(int positionClickedOnMainActivity);
    int getPositionClickedInMainActivity();

    void setPositionClickedInStepFragment(int positionClickedInStepFragment);
    int getPositionClickedInStepFragment();

    void setIngredientClickInStepFragment(Boolean flag);
    Boolean getIngredientClickInStepFragment();

    void setVideoUrlOfStepDetailFragment(String videoUrl);
    String getVideoUrlOfStepDetailFragment();

    void setPlayWhenReadyOfStepDetailFragment(Boolean playWhenReady);
    Boolean getPlayWhenReadyOfStepDetailFragment();

    void setPlayBackPositionOfStepDetailFragment(Long playBackPosition);
    Long getPlayBackPositionOfStepDetailFragment();

    void setCurrentWindowIndexOfStepDetailFragment(int currentWindowIndex);
    int getCurrentWindowIndexOfStepDetailFragment();


}
