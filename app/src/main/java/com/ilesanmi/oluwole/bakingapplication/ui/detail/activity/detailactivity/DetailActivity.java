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

import butterknife.BindBool;
import butterknife.BindInt;
import butterknife.ButterKnife;

/**
 * Created by abayomi on 19/03/2018.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {


    DetailFragment detailFragment;
    IngredientDetailFragment ingredientDetailFragment;

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    boolean isIngredientClicked = false;

    boolean questionIsIngredientClicked;

    int orientationChanged = 0;

    @BindInt(R.integer.orientation)
    int defaultOrientation;

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

        if (savedInstanceState != null) {
            questionIsIngredientClicked = savedInstanceState.getBoolean("IsIngredientClicked");
            orientationChanged = savedInstanceState.getInt("OrientationChanged");
            defaultOrientation = savedInstanceState.getInt("DefaultOrientation");
        }

        int currentOrientation = getResources().getConfiguration().orientation;
        //int orientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE && defaultOrientation == Configuration.ORIENTATION_PORTRAIT) {
            ++orientationChanged;
        }else if(currentOrientation == Configuration.ORIENTATION_PORTRAIT && defaultOrientation == Configuration.ORIENTATION_LANDSCAPE){
            ++orientationChanged;
        }

        if (orientationChanged == 0) {
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
        } else if (orientationChanged > 0 && questionIsIngredientClicked) {
            ingredientDetailFragment =
                    (IngredientDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);
        } else if (orientationChanged > 0 && !questionIsIngredientClicked) {
            detailFragment =
                    (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("IsIngredientClicked", questionIsIngredientClicked);
        outState.putInt("OrientationChanged", orientationChanged);
        outState.putInt("DefaultOrientation",defaultOrientation);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openFragment(Boolean ingredientClick) {
        isIngredientClicked = ingredientClick;
        questionIsIngredientClicked = ingredientClick;
    }
}