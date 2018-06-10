package com.ilesanmi.oluwole.bakingapplication.utils.rx;

import android.os.AsyncTask;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsyTask extends AsyncTask<Void, Void, String> {
    private String bakingAppJson = "";
    private AsyncResponse asyncR = null;

    public interface AsyncResponse {
        void retrieveRecipes(List<Recipe> recipes);
    }

    //PAY ATTENTION TO URL REDIRECTS
    @Override
    protected String doInBackground(Void... voids) {
        //String bakingAppJson = "";
        try {
            bakingAppJson = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildBakingAppUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bakingAppJson;
    }

    @Override
    protected void onPostExecute(String resultJson) {

        asyncR.retrieveRecipes(processResult(resultJson));
    }

    public static List<Recipe> processResult(String result) {
        List<Recipe> recipes = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            int recipesLength = jsonArray.length();

            for (int i = 0; i < recipesLength; i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int ingredientsLength = jsonObject.getJSONArray("ingredients").length();
                int stepsLength = jsonObject.getJSONArray("steps").length();

                Recipe recipe = new Recipe();
                ArrayList<Recipe.Ingredient> ingredients = new ArrayList<>();
                ArrayList<Recipe.Step> steps = new ArrayList<>();


                recipe.setId(jsonObject.getInt("id"));
                recipe.setName(jsonObject.getString("name"));


                for (int j = 0; j < ingredientsLength; j++) {
                    Recipe.Ingredient ingredient = new Recipe.Ingredient();

                    ingredient.setIngredient(jsonObject.getJSONArray("ingredients").getJSONObject(j).getString("ingredient"));
                    ingredient.setQuantity(jsonObject.getJSONArray("ingredients").getJSONObject(j).getDouble("quantity"));
                    ingredient.setMeasure(jsonObject.getJSONArray("ingredients").getJSONObject(j).getString("measure"));

                    ingredients.add(ingredient);

                }

                for (int k = 0; k < stepsLength; k++) {
                    Recipe.Step step = new Recipe.Step();

                    step.setId(jsonObject.getJSONArray("steps").getJSONObject(k).getInt("id"));
                    step.setShortDescription(jsonObject.getJSONArray("steps").getJSONObject(k).getString("shortDescription"));
                    step.setDescription(jsonObject.getJSONArray("steps").getJSONObject(k).getString("description"));
                    step.setVideoURL(jsonObject.getJSONArray("steps").getJSONObject(k).getString("videoURL"));
                    step.setThumbnailURL(jsonObject.getJSONArray("steps").getJSONObject(k).getString("thumbnailURL"));

                    steps.add(step);
                }

                recipe.setIngredients(ingredients);
                recipe.setSteps(steps);
                recipe.setServings(jsonObject.getInt("servings"));
                recipe.setImage(jsonObject.getString("image"));

                recipes.add(recipe);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}