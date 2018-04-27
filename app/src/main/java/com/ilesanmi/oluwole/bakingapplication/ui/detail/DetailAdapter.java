package com.ilesanmi.oluwole.bakingapplication.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.network.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAdapter extends BaseRecyclerViewAdapter<DetailAdapter.IngredientAndStepViewHolder> {

    private ArrayList<Recipe> mRecipeList;
    int size = 0;
    int positioned= 0;

    public DetailAdapter(ArrayList<Recipe> mRecipeList) {
//        Log.i("Adapter 1", "Nigeria");
        this.mRecipeList = mRecipeList;
    }

    //Inflate the recyclerView and then
    //Pass the inflated recyclerView to the recipiesViewHolder
    @Override
    public IngredientAndStepViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_recycler_views;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        IngredientAndStepViewHolder viewHolder = new IngredientAndStepViewHolder(view);

        return viewHolder;
    }

    //The onBindViewHolder is also iterative this is automatically done for you
    //The instance of RecipiesViewHolder returned from onCreateViewHolder above is passed to the parameter holder below.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            super.onBindViewHolder(viewHolder, position);
            IngredientAndStepViewHolder recipiesViewHolder = (IngredientAndStepViewHolder) viewHolder;
            recipiesViewHolder.bind(mRecipeList.get(positioned).getSteps().get(position).getShortDescription(), "");

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void addItems(ArrayList<Recipe> recipeList, int position,int size) {
        mRecipeList.addAll(recipeList);
        this.positioned = position;
        this.size = size;
        notifyDataSetChanged();
    }

    class IngredientAndStepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view)
        TextView mTextView;

        public IngredientAndStepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(String author, String content) {

            mTextView.setText(author);
            //listItemContentView.setText(content);
        }
    }

}
