package com.example.my_recipes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_recipes.activitys.EditRecipeActivity;
import com.example.my_recipes.dao.RecipeDao;
import com.example.my_recipes.databinding.ItemRecipeBinding;
import com.example.my_recipes.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private ArrayList<Recipe> recipeList;
    RecipeDao recipeDao;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipeList, RecipeDao recipeDao){
        this.context = context;
        this.recipeList = recipeList;
        this.recipeDao = recipeDao;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeBinding binding = ItemRecipeBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        holder.binding.textViewRecipeName.setText(recipe.getName());
        holder.binding.textViewIngredients.setText(recipe.getIngredients());
        holder.binding.textViewInstructions.setText(recipe.getInstructions());

        holder.binding.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditRecipeActivity.class);
            intent.putExtra("recipe_id",recipe.getId());
            context.startActivity(intent);

        });

        holder.binding.btndelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("אישור")
                            .setMessage("האם אתה בטוח רוצה למחוק?")
                                    .setPositiveButton("כן",(dialog, which) -> {
                                        recipeDao.delete(recipe);
                                        recipeList.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemChanged(position, recipeList.size());
                                        ArrayList<Recipe> updatedRecipes = (ArrayList<Recipe>) recipeDao.getAll();
                                        updateRecipes(updatedRecipes);
                                    })
                    .setNegativeButton("לא", null).show();
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        private ItemRecipeBinding binding;

        public RecipeViewHolder(ItemRecipeBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

    }
    public void updateRecipes(ArrayList<Recipe> recipes){
        this.recipeList = recipes;
        notifyDataSetChanged();
    }

}
