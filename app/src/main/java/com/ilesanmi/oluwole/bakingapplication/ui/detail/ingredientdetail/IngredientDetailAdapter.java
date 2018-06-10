package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.util.ArrayList;

public class IngredientDetailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Recipe> mRecipeList;
    private int size = 0;
    private int positionClick = 0;



    public IngredientDetailAdapter(Context context,ArrayList<Recipe> mRecipeList) {
        this.context = context;
        this.mRecipeList = mRecipeList;
    }

    @Override
    public int getCount() {

        return size;
    }

    public void addItems(ArrayList<Recipe> recipeList, int position) {
        mRecipeList.addAll(recipeList);
        this.positionClick = position;
        this.size = getSizeOfListStepsNestedInRecipe(recipeList,position);
        notifyDataSetChanged();
    }

    private int getSizeOfListStepsNestedInRecipe(ArrayList<Recipe> recipeList, int position) {
        return recipeList.get(position).getSteps().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Double quantity = mRecipeList.get(positionClick).getIngredients().get(position).getQuantity();
        String measure = mRecipeList.get(positionClick).getIngredients().get(position).getMeasure();
        String ingredient = mRecipeList.get(positionClick).getIngredients().get(position).getIngredient();

        if (convertView == null) {
            // If convertView is null then inflate the appropriate layout file
            convertView = LayoutInflater.from(context).inflate(R.layout.ingredient_detail_list, parent, false);
        }
        TextView ingredientTextView = convertView.findViewById(R.id.tv_item_ingredient);
        TextView quantityTextView = convertView.findViewById(R.id.tv_item_quantity);
        TextView measureTextView = convertView.findViewById(R.id.tv_item_measure);


        quantityTextView.setText(quantity.toString());
        measureTextView.setText(measure);
        ingredientTextView.setText(ingredient);


        return convertView;
    }

}
