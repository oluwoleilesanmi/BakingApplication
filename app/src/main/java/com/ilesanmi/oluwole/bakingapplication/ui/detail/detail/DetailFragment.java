package com.ilesanmi.oluwole.bakingapplication.ui.detail.detail;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends BaseFragment implements DetailMvpViewFrag {

    @Inject
    DetailMvpPresenterFrag<DetailMvpViewFrag> mPresenter;

//    @Inject
//    DetailPagerAdapter sPagerAdapter;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindBool(R.bool.m_pane_mode)
    boolean mTwoPane;

    DetailPagerAdapter mPagerAdapter;

    public static DetailFragment newInstance() {
        Bundle arguments = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }

        mPresenter.onViewPrepared(false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList, int positionM, int positionS) {
        mPagerAdapter = new DetailPagerAdapter(getChildFragmentManager(), recipeList, positionM, positionS);
        mPagerAdapter.setCount(1);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.step_detail)));

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
}
