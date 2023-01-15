package com.skypro.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.FileServiceRecipe;
import com.skypro.recipe.service.RecipeServices;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Data
public class RecipeServiceImpl implements RecipeServices {
    private final Map<Long, Recipe> recipeMap = new LinkedHashMap<>();
    private static long lastId = 0;

    final private FileServiceRecipe fileServiceRecipe;

    public RecipeServiceImpl(FileServiceRecipe fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }

    @PostConstruct
    private void init() {

    }


    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(this.lastId++, recipe);
        saveToFile();
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
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public List<Recipe> getAllRecipe() {
        return new ArrayList<>(this.recipeMap.values());
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileServiceRecipe.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
