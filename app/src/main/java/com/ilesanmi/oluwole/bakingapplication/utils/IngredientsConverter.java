package com.ilesanmi.oluwole.bakingapplication.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IngredientsConverter {

    private static Gson gson = new Gson();
    @TypeConverter
    public static List<Recipe.Ingredient> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Recipe.Ingredient>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<Recipe.Ingredient> someObjects) {
        return gson.toJson(someObjects);
    }

    /*
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    */

}
