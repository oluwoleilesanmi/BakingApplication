package com.ilesanmi.oluwole.bakingapplication.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.ilesanmi.oluwole.bakingapplication.di.ApplicationContext;
import com.ilesanmi.oluwole.bakingapplication.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferenceHelper implements PreferenceHelper {
    private static final String PREF_KEY_ACTIVITY_MAIN_POSITION_CLICKED = "PREF_KEY_ACTIVITY_MAIN_POSITION_CLICKED";
    private static final String PREF_KEY_FRAGMENT_STEP_POSITION_CLICKED = "PREF_KEY_FRAGMENT_STEP_POSITION_CLICKED";
    private static final String PREF_KEY_FRAGMENT_STEP_INGREDIENT_CLICKED = "PREF_KEY_FRAGMENT_STEP_INGREDIENT_CLICKED";
    //StepDetailFragment Data
    private static final String PREF_KEY_FRAGMENT_STEP_DETAIL_VIDEO_URL = "PREF_KEY_FRAGMENT_STEP_DETAIL_VIDEO_URL";
    private static final String PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_WHEN_READY = "PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_WHEN_READY";
    private static final String PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_BACK_POSITION = "PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_BACK_POSITION";
    private static final String PREF_KEY_FRAGMENT_STEP_DETAIL_CURRENT_WINDOW_POSITION = "PREF_KEY_FRAGMENT_STEP_DETAIL_CURRENT_WINDOW_POSITION";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferenceHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setPositionClickedInMainActivity(int positionClickedOnMainActivity) {
        mPrefs.edit().putInt(PREF_KEY_ACTIVITY_MAIN_POSITION_CLICKED, positionClickedOnMainActivity).apply();
    }

    @Override
    public int getPositionClickedInMainActivity() {
        return mPrefs.getInt(PREF_KEY_ACTIVITY_MAIN_POSITION_CLICKED, 0);
    }

    @Override
    public void setPositionClickedInStepFragment(int positionClickedInStepFragment) {
        mPrefs.edit().putInt(PREF_KEY_FRAGMENT_STEP_POSITION_CLICKED, positionClickedInStepFragment).apply();
    }

    @Override
    public int getPositionClickedInStepFragment() {
        return mPrefs.getInt(PREF_KEY_FRAGMENT_STEP_POSITION_CLICKED, 0);
    }

    @Override
    public void setIngredientClickInStepFragment(Boolean flag) {
        mPrefs.edit().putBoolean(PREF_KEY_FRAGMENT_STEP_INGREDIENT_CLICKED, flag).apply();
    }

    @Override
    public Boolean getIngredientClickInStepFragment() {
        return mPrefs.getBoolean(PREF_KEY_FRAGMENT_STEP_INGREDIENT_CLICKED,false);
    }

    //Video Player Data
    @Override
    public void setVideoUrlOfStepDetailFragment(String str) {
        mPrefs.edit().putString(PREF_KEY_FRAGMENT_STEP_DETAIL_VIDEO_URL, str).apply();
    }

    @Override
    public String getVideoUrlOfStepDetailFragment() {
        return mPrefs.getString(PREF_KEY_FRAGMENT_STEP_DETAIL_VIDEO_URL,"");
    }

    @Override
    public void setPlayWhenReadyOfStepDetailFragment(Boolean playWhenReady) {
        mPrefs.edit().putBoolean(PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_WHEN_READY, playWhenReady).apply();
    }

    @Override
    public Boolean getPlayWhenReadyOfStepDetailFragment() {
        return mPrefs.getBoolean(PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_WHEN_READY,false);
    }

    @Override
    public void setPlayBackPositionOfStepDetailFragment(Long playBackPosition) {
        mPrefs.edit().putLong(PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_BACK_POSITION,playBackPosition).apply();
    }

    @Override
    public Long getPlayBackPositionOfStepDetailFragment() {
        return mPrefs.getLong(PREF_KEY_FRAGMENT_STEP_DETAIL_PLAY_BACK_POSITION, 0);
    }

    @Override
    public void setCurrentWindowIndexOfStepDetailFragment(int currentWindowIndex) {
        mPrefs.edit().putInt(PREF_KEY_FRAGMENT_STEP_DETAIL_CURRENT_WINDOW_POSITION,currentWindowIndex).apply();
    }

    @Override
    public int getCurrentWindowIndexOfStepDetailFragment() {
        return mPrefs.getInt(PREF_KEY_FRAGMENT_STEP_DETAIL_CURRENT_WINDOW_POSITION, 0);
    }
}
