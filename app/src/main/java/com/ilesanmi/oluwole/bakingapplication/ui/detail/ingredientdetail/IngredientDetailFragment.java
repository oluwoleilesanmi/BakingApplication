package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.di.components.ActivityComponent;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientDetailFragment extends BaseFragment implements IngredientDetailMvpView {

    public static final String FRAGMENT_INGREDIENT_ID = "Ingredient_Detail_Id";

    @Inject
    IngredientDetailMvpPresenter<IngredientDetailMvpView> mPresenter;

    @Inject
    IngredientDetailAdapter mIngredientDetailAdapter;

    @BindView(R.id.ingredient_list_view)
    ListView mListView;


    public static IngredientDetailFragment newInstance() {
        Bundle arguments = new Bundle();
        IngredientDetailFragment fragment = new IngredientDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredientdetail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this);
        }

        mPresenter.onViewPrepared(false);

        mListView.setAdapter(mIngredientDetailAdapter);

        return view;
    }


    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList, int positionClick) {
        mIngredientDetailAdapter.addItems(recipeList, positionClick);
    }


}
