package com.skypro.recipe.service.impl;

import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.service.IngredientServices;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class IngredientServiceImpl implements IngredientServices {
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private static long lastId = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(this.lastId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(this.ingredientMap.values());
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        }
        return false;
    }

}
