package com.ilesanmi.oluwole.bakingapplication.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StepsConverter {

    private static Gson gson = new Gson();
    @TypeConverter
    public static List<Recipe.Step> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        //Used to specify the type of list to be returned eg array-list.
        Type listType = new TypeToken<List<Recipe.Step>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<Recipe.Step> someObjects) {
        return gson.toJson(someObjects);
    }
}

