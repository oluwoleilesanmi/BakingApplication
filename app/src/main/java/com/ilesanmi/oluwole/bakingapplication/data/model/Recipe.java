package com.ilesanmi.oluwole.bakingapplication.data.model;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import com.google.gson.annotations.SerializedName;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcelable;

import com.ilesanmi.oluwole.bakingapplication.utils.DbUtils;
import com.ilesanmi.oluwole.bakingapplication.utils.StepsConverter;
import com.ilesanmi.oluwole.bakingapplication.utils.IngredientsConverter;




@Entity(tableName = DbUtils.RECIPE_TABLE_NAME)
public class Recipe{

    @SerializedName("id")
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    private String name;

    //Always instantiate with a concrete object failure to do so will lead to NullPointer exception.
    @SerializedName("ingredients")
    @ColumnInfo(name = "ingredients_")
    @TypeConverters(IngredientsConverter.class)
    private List<Recipe.Ingredient> ingredients = new ArrayList<>();

    //Always instantiate with a concrete object failure to do so will lead to NullPointer exception.
    @SerializedName("steps")
    @ColumnInfo(name = "steps_")
    @TypeConverters(StepsConverter.class)
    private List<Recipe.Step> steps = new ArrayList<>();

    @SerializedName("servings")
    private Integer servings;

    @SerializedName("image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe.Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Recipe.Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Recipe.Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Recipe.Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public static class Ingredient {
        @SerializedName("quantity")
        private Double quantity;

        @SerializedName("measure")
        private String measure;

        @SerializedName("ingredient")
        private String ingredient;

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

    }

    public static class Step {
        @SerializedName("id")
        private Integer id;

        @SerializedName("shortDescription")
        private String shortDescription;

        @SerializedName("description")
        private String description;

        @SerializedName("videoURL")
        private String videoURL;

        @SerializedName("thumbnailURL")
        private String thumbnailURL;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }

        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }

    }


}




