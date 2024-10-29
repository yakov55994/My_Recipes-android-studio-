package com.example.my_recipes.db;

import android.content.Context;

import androidx.room.Database;

import com.example.my_recipes.dao.RecipeDao;
import com.example.my_recipes.entity.Recipe;

import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Recipe.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase instance;

    public static synchronized RecipeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RecipeDatabase.class,
                            "recipe_db")
                    .allowMainThreadQueries() .build();
        }
        return instance;
    }
    public abstract RecipeDao recipeDao();
}
