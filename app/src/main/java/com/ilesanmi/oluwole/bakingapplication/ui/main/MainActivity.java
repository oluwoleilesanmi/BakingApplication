package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
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

        //Create a recyclerView listener and then if specific view in recyclerView is clicked get the view and position
//        mRecipeAdapter.setOnItemClickListener((view, position) -> {
//
//
//
//            //SharedPreference is used to store position clicked for use in widget.
//            setSharedPreference("positionClicked", position, MainActivity.this);
//
//            //Migrate the passing of position clicked to DetailActivity.class with an intent to the above sharedPreference.
//            startActivity(DetailActivity.getStartIntent(MainActivity.this));
//        });
    }

    public void updateViewInActivity(ArrayList<Recipe> recipeList) {
        mRecipeAdapter.addItems(recipeList);
    }

    @Override
    protected void setUp() {

    }

    public static void setSharedPreference(String key, int value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    @Override
    public void openDetailActivity(int clickedPosition) {
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
