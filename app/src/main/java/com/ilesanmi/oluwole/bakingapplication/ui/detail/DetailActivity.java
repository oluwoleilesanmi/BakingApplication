package com.ilesanmi.oluwole.bakingapplication.ui.detail;

import android.os.Bundle;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;

/**
 * Created by abayomi on 19/03/2018.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @Inject
    DetailPagerAdapter mPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.feed_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(DetailActivity.this);
        setUnBinder(ButterKnife.bind(this));

        setUp();
    }

    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mPagerAdapter.setCount(2);

        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.step)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.detail)));

        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void openDetailActivity() {

    }













    /*
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
    */
}
