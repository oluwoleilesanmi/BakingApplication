package com.ilesanmi.oluwole.bakingapplication.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.base.RecyclerViewListener;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.IngredientDetail.IngredientDetailFragment;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.LayoutActivity.ItemDetailActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.stepdetail.StepDetailFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private boolean mTwoPane;

    public static Intent getStartIntent(Context context, int clickedPosition) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("positionClickedInMainActivity", clickedPosition);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        getActivityComponent().inject(DetailActivity.this);
        ButterKnife.bind(this);

        mPresenter.onAttach(this);
        mPresenter.onViewPrepared();

        createRecyclerView();

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @OnClick(R.id.ingredients_text_view)
    void onIngredientClick(View v) {
        //if screen is large enough load fragment beside activity.
        if (mTwoPane) {
            Fragment fragment2 = IngredientDetailFragment
                    .newInstance(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), mRecipes));

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment2, IngredientDetailFragment.FRAGMENT_ID)
                    .addToBackStack(null)
                    .commit();
        }
        //if screen not large enough load ItemDetailActivity.
        else {
            Intent intent = ItemDetailActivity
                    .getStartIntent(DetailActivity.this, getClickedPositionFromIntentSentFromMainActivity(), mRecipes, 0, 1);

            startActivity(intent);
        }


    }


    public void createRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDetailAdapter);
        mDetailAdapter.setOnItemClickListener(new RecyclerViewListener.OnItemClickListener() {
            //Create a Recycler-view listener and then if specific view in Recycler-view is clicked get the view and position
            @Override
            public void OnItemClick(View view, int position) {
                if (mTwoPane) {
                    Fragment fragment = setFragment(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), mRecipes), position);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment, StepDetailFragment.FRAGMENT_ID)
                            .addToBackStack(null)
                            .commit();
                } else {
                    Intent intent = ItemDetailActivity
                            .getStartIntent(DetailActivity.this, getClickedPositionFromIntentSentFromMainActivity(), mRecipes, position, 0);

                    startActivity(intent);
                }
            }
        });
    }

    public Parcelable getParcelable(int positionClickedInMainActivity, ArrayList<Recipe> recipes) {
        return recipes.get(positionClickedInMainActivity);
    }

    public Fragment setFragment(Parcelable parcelable, int positionClickedFromDetailActivity) {
        return StepDetailFragment.newInstance(parcelable, positionClickedFromDetailActivity);
    }


    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {
        this.mRecipes = recipeList;
        mDetailAdapter.addItems(recipeList,
                getClickedPositionFromIntentSentFromMainActivity(),
                getSizeOfListStepsNestedInRecipe(recipeList));
    }

    public int getClickedPositionFromIntentSentFromMainActivity() {
        return getIntent().getIntExtra("positionClickedInMainActivity", 0);
    }

    public int getSizeOfListStepsNestedInRecipe(ArrayList<Recipe> recipeList) {
        return recipeList.get(getClickedPositionFromIntentSentFromMainActivity()).getSteps().size();
    }

    @Override
    public void openDetailActivity() {

    }
}
