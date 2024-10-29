package com.example.my_recipes.activitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.my_recipes.dao.RecipeDao;
import com.example.my_recipes.databinding.EditRecipeBinding;
import com.example.my_recipes.db.RecipeDatabase;
import com.example.my_recipes.entity.Recipe;

public class EditRecipeActivity extends AppCompatActivity {

    private EditRecipeBinding binding;
    private RecipeDao recipeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = EditRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        recipeDao = RecipeDatabase.getInstance(this).recipeDao();

        Recipe recipe = recipeDao.getRecipeById(String.valueOf(recipeId));
        binding.editTextRecipeName.setText(recipe.getName());
        binding.editTextTextIngredients.setText(recipe.getIngredients());
        binding.editTextTextInstructions.setText(recipe.getInstructions());

        binding.btnSaveAfterEdit.setOnClickListener(v -> {

            String updateName = binding.editTextRecipeName.getText().toString();
            String updateIngredients = binding.editTextTextIngredients.getText().toString();
            String updateInstructions = binding.editTextTextInstructions.getText().toString();

            recipe.setName(updateName);
            recipe.setIngredients(updateIngredients);
            recipe.setInstructions(updateInstructions);

            recipeDao.update(recipe);
            finish();
        });
    }
}
