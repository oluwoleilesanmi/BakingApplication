package com.ilesanmi.oluwole.bakingapplication.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;
    //Always instantiate with a concrete object failure to do so will lead to NullPointer exception.
    @SerializedName("ingredients")
    @Expose
    private List<Recipe.Ingredient> ingredients = new ArrayList<>();
    //Always instantiate with a concrete object failure to do so will lead to NullPointer exception.
    @SerializedName("steps")
    @Expose
    private List<Recipe.Step> steps = new ArrayList<>();

    @SerializedName("servings")
    @Expose
    private Integer servings;

    @SerializedName("image")
    @Expose
    private String image;

    public Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        in.readList(ingredients, Ingredient.class.getClassLoader());
        in.readList(steps, Step.class.getClassLoader());
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeInt(servings);
        dest.writeString(image);
    }


    public static class Ingredient implements Parcelable {
        @SerializedName("quantity")
        @Expose
        private Double quantity;

        @SerializedName("measure")
        @Expose
        private String measure;

        @SerializedName("ingredient")
        @Expose
        private String ingredient;

        protected Ingredient(Parcel in) {
            quantity = in.readDouble();
            measure = in.readString();
            ingredient = in.readString();
        }

        public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
            @Override
            public Ingredient createFromParcel(Parcel in) {
                return new Ingredient(in);
            }

            @Override
            public Ingredient[] newArray(int size) {
                return new Ingredient[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(quantity);
            dest.writeString(measure);
            dest.writeString(ingredient);
        }
    }

    public static class Step implements Parcelable {
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("shortDescription")
        @Expose
        private String shortDescription;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("videoURL")
        @Expose
        private String videoURL;

        @SerializedName("thumbnailURL")
        @Expose
        private String thumbnailURL;

        protected Step(Parcel in) {
            id = in.readInt();
            shortDescription = in.readString();
            description = in.readString();
            videoURL = in.readString();
            thumbnailURL = in.readString();
        }

        public static final Creator<Step> CREATOR = new Creator<Step>() {
            @Override
            public Step createFromParcel(Parcel in) {
                return new Step(in);
            }

            @Override
            public Step[] newArray(int size) {
                return new Step[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(shortDescription);
            dest.writeString(description);
            dest.writeString(videoURL);
            dest.writeString(thumbnailURL);
        }
    }


}




