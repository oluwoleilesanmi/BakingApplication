package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.SimpleIdlingResource;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import android.support.test.espresso.IdlingResource;


public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    MainAdapter mRecipeAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    IdlingResource simpleIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        mPresenter.onAttach(MainActivity.this);
        mPresenter.onViewPrepared(false);

        createRecyclerView();
    }

    public void createRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecipeAdapter.setOnItemClickListener((view, position) -> {
            mPresenter.onPositionPressed(position);
            openDetailActivity();
        });
    }

    public void updateViewInActivity(ArrayList<Recipe> recipeList) {
        mRecipeAdapter.addItems(recipeList);
    }

    @Override
    protected void setUp() {

    }

    public void openDetailActivity() {
        startActivity(DetailActivity.getStartIntent(MainActivity.this));
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }

}
