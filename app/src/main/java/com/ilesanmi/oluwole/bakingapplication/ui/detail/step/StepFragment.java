package com.ilesanmi.oluwole.bakingapplication.ui.detail.step;

import android.view.View;
import java.util.ArrayList;
import android.os.Bundle;
import javax.inject.Inject;
import butterknife.OnClick;
import butterknife.BindView;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.ilesanmi.oluwole.bakingapplication.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;

/**
 * Created by abayomi on 19/03/2018.
 */

public class StepFragment extends BaseFragment implements StepMvpView {

    public static final String FRAGMENT_ID = "Step_Id";

    @Inject
    StepPresenter<StepMvpView> mPresenter;

    @Inject
    StepAdapter mDetailAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.detail_recycler_view)
    RecyclerView mRecyclerView;

    ArrayList<Recipe> mRecipes;
    private boolean mTwoPane;

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
        View view = inflater.inflate(R.layout.activity_item_list, container, false);

        ButterKnife.bind(this, view);
        createRecyclerView();

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }
        mPresenter.onViewPrepared(true);

        //recipe.getSteps().get(clickedPosition).getVideoURL();


        return view;
    }

    @OnClick(R.id.ingredients_text_view)
    void onIngredientClick(View v) {
        //if screen is large enough load fragment beside activity.
//        if (mTwoPane) {
//            Fragment fragment2 = IngredientDetailFragment
//                    .newInstance(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), mRecipes));
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.item_detail_container, fragment2, IngredientDetailFragment.FRAGMENT_ID)
//                    .commit();
//        }
//        //if screen not large enough load ItemDetailActivity.
//        else {
//            Intent intent = ItemDetailActivity
//                    .getStartIntent(DetailActivity.this, getClickedPositionFromIntentSentFromMainActivity(), mRecipes, 0, 1);
//
//            startActivity(intent);
//        }


    }

    public void createRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDetailAdapter);
        //Create a Recycler-view listener and then if specific view in Recycler-view is clicked get the view and position
        mDetailAdapter.setOnItemClickListener((view, position) -> {
//                if (mTwoPane) {
//                    Fragment fragment = setFragment(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), mRecipes), position);
//
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.item_detail_container, fragment, StepDetailFragment.FRAGMENT_ID)
//                            .commit();
//                } else {
//                    Intent intent = ItemDetailActivity
//                            .getStartIntent(DetailActivity.this, getClickedPositionFromIntentSentFromMainActivity(), mRecipes, position, 0);
//
//                    startActivity(intent);
//                }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTwoPane) {
//            expandVideoView(exoPlayerView);
//            setViewVisibility(descriptionCard, false);
//            hideSystemUI();
//        }

    }

    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {
        this.mRecipes = recipeList;
        mDetailAdapter.addItems(recipeList,
                getClickedPositionFromIntentSentFromMainActivity(),
                getSizeOfListStepsNestedInRecipe(recipeList));
    }

    public int getClickedPositionFromIntentSentFromMainActivity() {
        //     return getIntent().getIntExtra("positionClickedInMainActivity", 0);
        return 0;
    }

    public int getSizeOfListStepsNestedInRecipe(ArrayList<Recipe> recipeList) {
        return recipeList.get(getClickedPositionFromIntentSentFromMainActivity()).getSteps().size();
    }
}