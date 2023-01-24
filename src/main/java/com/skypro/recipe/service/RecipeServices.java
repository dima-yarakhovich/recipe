package com.skypro.recipe.service;

import com.skypro.recipe.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RecipeServices {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Long id);

    boolean deleteRecipe(Long id);

    Recipe editRecipe(Long id, Recipe recipe);

    List<Recipe> getAllRecipe();
    void addRecipesFromInputStream(InputStream inputStream) throws IOException;
    void readRecipeFromFile();

    File createRecipesTxtFile();
}
