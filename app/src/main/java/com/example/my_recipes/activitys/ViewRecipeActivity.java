package com.example.my_recipes.activitys;

import android.os.Bundle;
import android.widget.Toast;
//import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.my_recipes.RecipeAdapter;
import com.example.my_recipes.dao.RecipeDao;
import com.example.my_recipes.databinding.ViewRecipesBinding;
import com.example.my_recipes.db.RecipeDatabase;
import com.example.my_recipes.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ViewRecipeActivity extends AppCompatActivity {

    private ViewRecipesBinding binding;
    private RecipeDao recipeDao;
    private RecipeAdapter recipeAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(this);
        recipeDao = recipeDatabase.recipeDao();

        loadRecipes();

    }

    private void loadRecipes(){
        List<Recipe> recipes = recipeDao.getAll();
        if (recipes != null && !recipes.isEmpty()){
            recipeAdapter = new RecipeAdapter(this, (ArrayList<Recipe>) recipes, recipeDao);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(recipeAdapter);
        } else {
            Toast.makeText(this, "No recipes found", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        List<Recipe> updateRecipes = recipeDao.getAll();
        if (updateRecipes != null){
            recipeAdapter.updateRecipes((ArrayList<Recipe>) updateRecipes);
        }
    }
}
