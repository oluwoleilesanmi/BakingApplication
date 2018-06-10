package com.ilesanmi.oluwole.bakingapplication.ui.detail.activity.detailactivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.detail.DetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail.IngredientDetailFragment;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.ButterKnife;

/**
 * Created by abayomi on 19/03/2018.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {
    private static final int ORIENTATION_DID_NOT_CHANGE = 0;

    private DetailFragment detailFragment;
    private boolean isIngredientClicked = false;
    private IngredientDetailFragment ingredientDetailFragment;
    private int orientationChange = ORIENTATION_DID_NOT_CHANGE;

    @BindInt(R.integer.orientation)
    int defaultOrientation;

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.onAttach(DetailActivity.this);

        //Used to restore the initial starting values of click and orientation
        if (savedInstanceState != null) {
            isIngredientClicked = savedInstanceState.getBoolean("IsIngredientClicked");
            orientationChange = savedInstanceState.getInt("OrientationChange");
            defaultOrientation = savedInstanceState.getInt("DefaultOrientation");
        }


        //Detect if orientation changed and update counter for orientation change
        updateCounterOnOrientationChange();

        //Used to create and restore fragment that was created on orientation changed
        if (orientationChange == ORIENTATION_DID_NOT_CHANGE) {
            mPresenter.isIngredientClickedInStep();
            if (ingredientDetailFragment == null && isIngredientClicked) {
                ingredientDetailFragment = IngredientDetailFragment.newInstance();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.detail_fragment_container, ingredientDetailFragment);
                transaction.commit();
                mPresenter.resetIngredientClickedBooleanPref(false);
            } else if (detailFragment == null) {
                detailFragment = DetailFragment.newInstance();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.detail_fragment_container, detailFragment);
                transaction.commit();
            }
        } else if (orientationChange > ORIENTATION_DID_NOT_CHANGE && isIngredientClicked) {
            ingredientDetailFragment =
                    (IngredientDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);
        } else if (orientationChange > ORIENTATION_DID_NOT_CHANGE && !isIngredientClicked) {
            detailFragment =
                    (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("IsIngredientClicked", isIngredientClicked);
        outState.putInt("OrientationChange", orientationChange);
        outState.putInt("DefaultOrientation", defaultOrientation);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openFragment(Boolean isIngredientClicked) {
        this.isIngredientClicked = isIngredientClicked;
    }

    void updateCounterOnOrientationChange() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE && defaultOrientation == Configuration.ORIENTATION_PORTRAIT) {
            ++orientationChange;
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT && defaultOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            ++orientationChange;
        }
    }
}