package com.skypro.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.service.FileServiceIngredient;
import com.skypro.recipe.service.IngredientServices;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Data
public class IngredientServiceImpl implements IngredientServices {
    private Map<Long, Ingredient> ingredientMap = new LinkedHashMap<>();
    private static long lastId = 0;

    final private FileServiceIngredient fileServiceIngredient;

    public IngredientServiceImpl(FileServiceIngredient fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }

    @PostConstruct
    private void init() {
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(this.lastId++, ingredient);
        saveToFile();
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
            saveToFile();
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

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileServiceIngredient.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
