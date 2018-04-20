package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.base.RecyclerViewListener;

import java.util.ArrayList;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    MainAdapter mRecipeAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        mPresenter.onAttach(MainActivity.this);
        mPresenter.onViewPrepared();

        createRecyclerView();
    }

    public void createRecyclerView(){
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecipeAdapter.setOnItemClickListener(new RecyclerViewListener.OnItemClickListener() {

            @Override
            public void OnItemClick(View view, int position) {

            }
        });
    }

    public void updateViewInActivity(ArrayList<Recipe> recipeList){
        mRecipeAdapter.addItems(recipeList);
    }
}
