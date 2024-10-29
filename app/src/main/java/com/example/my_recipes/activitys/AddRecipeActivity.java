package com.example.my_recipes.activitys;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_recipes.RecipeAdapter;
import com.example.my_recipes.dao.RecipeDao;
import com.example.my_recipes.databinding.AddRecipeBinding;
import com.example.my_recipes.db.RecipeDatabase;
import com.example.my_recipes.entity.Recipe;
import java.util.List;


public class AddRecipeActivity extends AppCompatActivity {

    private AddRecipeBinding binding;
    private RecipeDatabase recipeDatabase;
    private RecipeDao recipeDao;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recipeDatabase = RecipeDatabase.getInstance(this);
        recipeDao = recipeDatabase.recipeDao();

        binding.btnSaveRecipe.setOnClickListener(v -> {

            String name = binding.recipeName.getText().toString();
            String ingredients = binding.ingredients.getText().toString();
            String instructions = binding.Instructions.getText().toString();

            if (!name.isEmpty() && !ingredients.isEmpty() && !instructions.isEmpty()){

                Recipe newRecipe = new Recipe(name,ingredients,instructions);
                try
                {
                    recipeDao.insert(newRecipe);
//                    List<Recipe> updatedRecipes = recipeDao.getAll();
//                    recipeAdapter.updateRecipes(updatedRecipes);
                    Toast.makeText(this, "Recipe added successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Error adding recipe:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
