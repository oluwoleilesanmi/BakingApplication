package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

class DetailPagerAdapter extends FragmentPagerAdapter {

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

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return recipes.get(positionM).getSteps().get(positionS).getDescription();
//    }
}
