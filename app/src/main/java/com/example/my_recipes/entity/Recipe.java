package com.example.my_recipes.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private String ingredients;
}
