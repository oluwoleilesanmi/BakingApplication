package com.ilesanmi.oluwole.bakingapplication.ui.detail.step;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends BaseRecyclerViewAdapter<StepAdapter.StepViewHolder> {

    private ArrayList<Recipe> mRecipeList;
    private int size = 0;
    private int positioned = 0;

    public StepAdapter(ArrayList<Recipe> mRecipeList) {
        this.mRecipeList = mRecipeList;
    }

    //Inflate the recyclerView and then
    //Pass the inflated recyclerView to the recipiesViewHolder
    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_recycler_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        StepViewHolder viewHolder = new StepViewHolder(view);

        return viewHolder;
    }

    //The onBindViewHolder is also iterative this is automatically done for you
    //The instance of RecipiesViewHolder returned from onCreateViewHolder above is passed to the parameter holder below.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            super.onBindViewHolder(viewHolder, position);
            StepViewHolder stepViewHolder = (StepViewHolder) viewHolder;
            stepViewHolder.bind(mRecipeList.get(positioned).getSteps().get(position).getShortDescription());

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void addItems(ArrayList<Recipe> recipeList, int position) {
        mRecipeList.addAll(recipeList);
        this.positioned = position;
        this.size = getSizeOfListStepsNestedInRecipe(recipeList,position);
        notifyDataSetChanged();
    }

    private int getSizeOfListStepsNestedInRecipe(ArrayList<Recipe> recipeList, int position) {
        return recipeList.get(position).getSteps().size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view)
        TextView mTextView;

        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(String author) {

            mTextView.setText(author);
            //listItemContentView.setText(content);
        }
    }

}
