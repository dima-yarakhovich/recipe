package com.skypro.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private int cookingTime;
    ArrayList<Ingredient> ingredients;
    ArrayList<String> steps;
}
