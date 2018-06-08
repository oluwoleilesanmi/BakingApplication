package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail.IngredientDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

import java.util.ArrayList;


public class DetailPagerAdapter extends FragmentPagerAdapter {

   private int mTabCount;
   private int positionM;
   private int positionS;
   private ArrayList<Recipe> recipes;

    public DetailPagerAdapter(FragmentManager fragmentManager,ArrayList<Recipe> recipes,int positionM,int positionS) {
        super(fragmentManager);
        this.recipes = recipes;
        this.positionM = positionM;
        this.positionS = positionS;
        this.mTabCount = 0;
        notifyDataSetChanged();
    }

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

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
