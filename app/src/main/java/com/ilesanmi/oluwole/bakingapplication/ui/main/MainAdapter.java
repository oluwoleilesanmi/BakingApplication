package com.ilesanmi.oluwole.bakingapplication.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.base.BaseRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abayomi on 25/03/2018.
 */

public class MainAdapter extends BaseRecyclerViewAdapter<MainAdapter.RecipiesViewHolder> {

    private ArrayList<Recipe> mRecipeList;
    private Context context;

    public MainAdapter(ArrayList<Recipe> mRecipeList, Context context) {
        this.context = context;
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
        recipiesViewHolder.bind(mRecipeList.get(position).getName(),mRecipeList.get(position).getImage());
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

        @BindView(R.id.text_view)
        TextView mTextView;

        @BindView(R.id.main_image_view)
        ImageView mImageView;

        RecipiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(String author, String imageUrl) {

            mTextView.setText(author);
            if (imageUrl != null && !imageUrl.isEmpty()) {

                Picasso.with(context)
                        .load(imageUrl)
                        .into(mImageView);
            } else {
                setImageViewVisibility(mImageView, false);
            }
        }

        private void setImageViewVisibility(View view, boolean imageAvailable) {
            if (imageAvailable) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

}
