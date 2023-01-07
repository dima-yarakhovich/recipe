package com.skypro.recipe.service;
import com.skypro.recipe.model.Recipe;

import java.util.List;

public interface RecipeServices {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Long id);

    boolean deleteRecipe(Long id);

    Recipe editRecipe(Long id, Recipe recipe);

    List<Recipe> getAllRecipe();


}
