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
}
