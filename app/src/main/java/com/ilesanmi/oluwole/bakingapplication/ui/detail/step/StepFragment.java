package com.ilesanmi.oluwole.bakingapplication.ui.detail.step;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.OnClick;
import butterknife.BindView;

import android.view.ViewGroup;

import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.support.annotation.Nullable;

import com.ilesanmi.oluwole.bakingapplication.R;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.activity.detailactivity.DetailActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail.IngredientDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

/**
 * Created by abayomi on 19/03/2018.
 */

public class StepFragment extends BaseFragment implements StepMvpView {

    @Inject
    StepMvpPresenter<StepMvpView> mPresenter;

    @Inject
    StepAdapter mStepAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.step_recycler_view)
    RecyclerView mRecyclerView;

    @BindBool(R.bool.m_pane_mode)
    boolean mTwoPane;

    private String description;
    private String videoUrl;
    private String imageUrl;

    public static StepFragment newInstance() {
        Bundle arguments = new Bundle();
        StepFragment fragment = new StepFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }

        mPresenter.onViewPrepared(false);
        createRecyclerView();

        if (mTwoPane) {
            addIngredientDetailInMultiPane();
        }

        return view;
    }

    @OnClick(R.id.ingredients_text_view)
    void onIngredientClick(View v) {

        if (mTwoPane) {
            replaceIngredientDetailInMultiPane();
        } else {
            mPresenter.ingredientIsPressed(true);
            startActivity(DetailActivity.getStartIntent(getContext()));
        }
    }

    private void createRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mStepAdapter);
        //Create a Recycler-view listener and then if specific view in Recycler-view is clicked get the view and position

        mStepAdapter.setOnItemClickListener((view, position) -> {
            mPresenter.stepPressed(position);

            if (mTwoPane) {
                mPresenter.onViewPrepared(false);

            } else {
                startActivity(DetailActivity.getStartIntent(getContext()));

            }
        });
    }

    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipes, int positionM, int positionS) {
        mStepAdapter.addItems(recipes, positionM);
        if (mTwoPane) {
            description = recipes.get(positionM).getSteps().get(positionS).getDescription();
            videoUrl = recipes.get(positionM).getSteps().get(positionS).getVideoURL();
            imageUrl = recipes.get(positionM).getSteps().get(positionS).getThumbnailURL();
            replaceStepDetailInMultiPane();
        }
    }


    private void replaceStepDetailInMultiPane() {

        Fragment stepDetailFragment = StepDetailFragment.newInstance(description,videoUrl,imageUrl);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.item_detail_container, stepDetailFragment)
                .commit();

    }

    private void addIngredientDetailInMultiPane() {

        Fragment ingredientDetailFragment = new IngredientDetailFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.item_detail_container, ingredientDetailFragment)
                .commit();

    }

    private void replaceIngredientDetailInMultiPane() {

        Fragment ingredientDetailFragment = new IngredientDetailFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.item_detail_container, ingredientDetailFragment)
                .commit();

    }

}