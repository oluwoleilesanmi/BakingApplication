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
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailActivity;

/**
 * Created by abayomi on 19/03/2018.
 */

public class StepFragment extends BaseFragment implements StepMvpView {

    public static final String FRAGMENT_ID = "Step_Id";

    @Inject
    StepMvpPresenter<StepMvpView> mPresenter;

    @Inject
    StepAdapter mStepAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.detail_recycler_view)
    RecyclerView mRecyclerView;

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
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }
        createRecyclerView();
        mPresenter.onViewPrepared(false);



        return view;
    }

    @OnClick(R.id.ingredients_text_view)
    void onIngredientClick(View v) {
        //if screen is large enough load fragment beside activity.
//        if (!mTwoPane) {
            ((DetailActivity)getContext()).openFragment(2);

//        }
        //if screen not large enough load ItemDetailActivity.


    }

    public void createRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mStepAdapter);
        //Create a Recycler-view listener and then if specific view in Recycler-view is clicked get the view and position
        mStepAdapter.setOnItemClickListener((view, position) -> {

            mPresenter.onPressed(position);
            ((DetailActivity)getContext()).openFragment(1);

            //                if (mTwoPane) {
//                    Fragment fragment = setFragment(getParcelable(getClickedPositionFromIntentSentFromMainActivity(), mRecipes), position);
//
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
    public void updateViewInActivity(ArrayList<Recipe> recipeList,int positionClick) {
        mStepAdapter.addItems(recipeList, positionClick);
    }

    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {

    }



}