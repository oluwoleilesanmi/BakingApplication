package com.ilesanmi.oluwole.bakingapplication.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.base.RecyclerViewListener;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainAdapter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainMvpPresenter;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainMvpView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abayomi on 19/03/2018.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    @Inject
    DetailAdapter mDetailAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.detail_recycler_view)
    RecyclerView mRecyclerView;

    ArrayList<Recipe> mRecipes;

    public static Intent getStartIntent(Context context, int clickedPosition) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("positionClickedInMainActivity", clickedPosition);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(DetailActivity.this);
        ButterKnife.bind(this);

        mPresenter.onAttach(this);
        mPresenter.onViewPrepared();

        createRecyclerView();


        if (savedInstanceState == null) {
//            Bundle arguments = new Bundle();
//            arguments.putString(StepDetailFragment.FRAGMENT_ID,
//                    getIntent().getStringExtra(StepDetailFragment.FRAGMENT_ID));
//            StepDetailFragment fragment = new StepDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.swap, fragment)
//                    .commit();
        }
    }

    public void createRecyclerView() {
        //set with
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDetailAdapter);
        mDetailAdapter.setOnItemClickListener(new RecyclerViewListener.OnItemClickListener() {
            //create a recyclerview listener and then if specific view in recyclerview is clicked get the view and position
            @Override
            public void OnItemClick(View view, int position) {
                Fragment fragment = setFragment(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), mRecipes),position);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.swap, fragment)
                        .commit();
            }
        });
    }
    public Parcelable getParcelable(int positionClickedInMainActivity, ArrayList<Recipe> recipes){
        return recipes.get(positionClickedInMainActivity);
    }
    public Fragment setFragment(Parcelable parcelable,int positionClickedFromDetailActivity){
        return StepDetailFragment.newInstance(parcelable,positionClickedFromDetailActivity);
    }


    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {
        this.mRecipes = recipeList;
        mDetailAdapter.addItems(recipeList,
                getClickedPositionFromIntentSentFromMainActivity(),
                getSizeOfListStepsNestedInRecipe(recipeList));
    }

    public int getClickedPositionFromIntentSentFromMainActivity() {
        Intent intent = getIntent();
        Integer positionClickedInMainActivity = intent.getIntExtra("positionClickedInMainActivity",0);
        return positionClickedInMainActivity;
    }

    public int getSizeOfListStepsNestedInRecipe(ArrayList<Recipe> recipeList){
        return recipeList.get(getClickedPositionFromIntentSentFromMainActivity()).getSteps().size();
    }

    @Override
    public void openDetailActivity() {

    }
}
