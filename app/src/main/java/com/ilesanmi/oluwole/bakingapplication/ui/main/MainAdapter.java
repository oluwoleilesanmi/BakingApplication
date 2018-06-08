package com.ilesanmi.oluwole.bakingapplication.ui.main;

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

/**
 * Created by abayomi on 25/03/2018.
 */

public class MainAdapter extends BaseRecyclerViewAdapter<MainAdapter.RecipiesViewHolder> {

    private ArrayList<Recipe> mRecipeList;

    public MainAdapter(ArrayList<Recipe> mRecipeList){
//        Log.i("Adapter 1", "Nigeria");
        this.mRecipeList = mRecipeList;
    }

    //Inflate the recyclerView and then
    //Pass the inflated recyclerView to the recipiesViewHolder
    @Override
    public RecipiesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_recycler_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        RecipiesViewHolder viewHolder = new RecipiesViewHolder(view);

        return viewHolder;
    }

    //The onBindViewHolder is also iterative this is automatically done for you
    //The instance of RecipiesViewHolder returned from onCreateViewHolder above is passed to the parameter holder below.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
  //      Log.i("Adapter 2", mRecipeList.get(position).getName());

        super.onBindViewHolder(viewHolder, position);
        RecipiesViewHolder recipiesViewHolder = (RecipiesViewHolder) viewHolder; //safe cast
        recipiesViewHolder.bind(mRecipeList.get(position).getName(),"");
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public void addItems(ArrayList<Recipe> recipeList) {
        mRecipeList.addAll(recipeList);
        notifyDataSetChanged();
    }

    class RecipiesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view) TextView mTextView;

        public RecipiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(String author, String content) {

           mTextView.setText(author);
            //listItemContentView.setText(content);
        }
    }

}
