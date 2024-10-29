package com.example.my_recipes.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_recipes.R;
import com.example.my_recipes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnAddRecipe.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, AddRecipeActivity.class))
        );
        binding.btnViewRecipe.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, ViewRecipeActivity.class))
        );
    }
}