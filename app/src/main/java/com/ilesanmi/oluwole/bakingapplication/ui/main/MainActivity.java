package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.base.RecyclerViewListener;
import com.ilesanmi.oluwole.bakingapplication.ui.detail.DetailActivity;

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
        //set with
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecipeAdapter.setOnItemClickListener(new RecyclerViewListener.OnItemClickListener() {
        //create a recyclerview listener and then if specific view in recyclerview is clicked get the view and position
            @Override
            public void OnItemClick(View view, int position) {
                Log.i("Activity click", Integer.toString(position));
                setSharedPreference("positionClicked",position,MainActivity.this);
                startActivity(DetailActivity.getStartIntent(MainActivity.this,position));
            }
        });
    }

    public void updateViewInActivity(ArrayList<Recipe> recipeList){
        mRecipeAdapter.addItems(recipeList);
    }

    public static void setSharedPreference(String key, int value, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }


    @Override
    public void openDetailActivity(int clickedPosition) {
    }
}
