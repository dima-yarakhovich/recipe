package com.skypro.recipe.service.impl;
import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.RecipeServices;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class RecipeServiceImpl implements RecipeServices {
    private final Map<Long, Recipe> recipeMap = new HashMap<>();
    private static long lastId = 0;


    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(this.lastId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipeMap.get(id);
    }

    @Override
    public boolean deleteRecipe(Long id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Recipe editRecipe(Long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public List<Recipe> getAllRecipe() {
        return new ArrayList<>(this.recipeMap.values());
    }
}
