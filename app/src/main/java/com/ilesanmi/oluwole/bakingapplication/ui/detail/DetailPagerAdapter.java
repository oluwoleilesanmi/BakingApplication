package com.ilesanmi.oluwole.bakingapplication.ui.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail.IngredientDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.step.StepFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

public class DetailPagerAdapter extends FragmentPagerAdapter {

    private int mTabCount;

    public DetailPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return StepFragment.newInstance();
            case 1:
                return IngredientDetailFragment.newInstance();

            case 2:
                return StepDetailFragment.newInstance();
            default:
                return null;
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
