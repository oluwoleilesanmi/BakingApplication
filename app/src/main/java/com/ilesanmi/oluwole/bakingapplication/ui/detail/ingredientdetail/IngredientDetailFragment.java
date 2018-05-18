package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientDetailFragment extends BaseFragment implements IngredientDetailMvpView {
    public static final String FRAGMENT_ID = "Ingredient_Detail_Id";

    @BindView(R.id.ingredient_list_view)
    ListView mListView;

    private IngredientDetailAdapter mIngredientDetailAdapter;

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
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);

        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable("RECIPE");
            mIngredientDetailAdapter= new IngredientDetailAdapter(getActivity(), recipe);
        }

        mListView.setAdapter(mIngredientDetailAdapter);

        return view;
    }


    @Override
    public void updateViewInActivity(ArrayList<Recipe> recipeList) {

    }
}
