package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import java.util.ArrayList;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    private int mTabCount;
    private int positionM;
    private int positionS;
    private ArrayList<Recipe> recipes;

    public DetailPagerAdapter(FragmentManager fragmentManager, ArrayList<Recipe> recipes, int positionM, int positionS) {
        super(fragmentManager);
        this.recipes = recipes;
        this.positionM = positionM;
        this.positionS = positionS;
        this.mTabCount = 0;
        notifyDataSetChanged();
    }

    // After rotation, getItem() is NOT invoked; only this method instantiateItem() gets called
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            default:
                return StepDetailFragment.newInstance(
                        recipes.get(positionM).getSteps().get(positionS).getDescription(),
                        recipes.get(positionM).getSteps().get(positionS).getVideoURL(),
                        recipes.get(positionM).getSteps().get(positionS).getThumbnailURL()
                );
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Object frgObj = super.instantiateItem(container, position);
        StepDetailFragment stepDetailFragment = (StepDetailFragment) frgObj;

        return stepDetailFragment;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return recipes.get(positionM).getSteps().get(positionS).getDescription();
//    }
}
