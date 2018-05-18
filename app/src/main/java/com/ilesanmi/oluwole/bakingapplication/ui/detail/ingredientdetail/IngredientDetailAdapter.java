package com.ilesanmi.oluwole.bakingapplication.ui.detail.ingredientdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ilesanmi.oluwole.bakingapplication.R;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

public class IngredientDetailAdapter extends BaseAdapter {
    private Context context;
    private Recipe recipe;

    public IngredientDetailAdapter(Context context, Recipe recipe) {
        this.context = context;
        this.recipe = recipe;
    }

    @Override
    public int getCount() {
        return recipe.getIngredients().size();
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
        Double quantity = recipe.getIngredients().get(position).getQuantity();
        String measure = recipe.getIngredients().get(position).getMeasure();
        String ingredient = recipe.getIngredients().get(position).getIngredient();

        if (convertView == null) {
            // If convertView is null then inflate the appropriate layout file
            convertView = LayoutInflater.from(context).inflate(R.layout.ingredient_detail_content, parent, false);
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
