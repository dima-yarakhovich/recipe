package com.skypro.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.FileServiceRecipe;
import com.skypro.recipe.service.RecipeServices;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
@Data
public class RecipeServiceImpl implements RecipeServices {
    private Map<Long, Recipe> recipeMap = new TreeMap<>();
    private static long lastId = 0;

    final private FileServiceRecipe fileServiceRecipe;

    public RecipeServiceImpl(FileServiceRecipe fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }


    @PostConstruct
    private void init() {
        try {
            readRecipeFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Map<String, Object> map = new HashMap<>();
            map.put("id", lastId);
            map.put("recipes", recipeMap);
            String json = new ObjectMapper().writeValueAsString(map);
            fileServiceRecipe.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addRecipesFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, "|");
                Recipe recipe = new Recipe(String.valueOf(array[0]),Integer.valueOf(array[1]));
                addRecipe(recipe);
            }
        }
    }

    public void readRecipeFromFile() {
        try {
            String json = fileServiceRecipe.readRecipeFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File createRecipesTxtFile() {
            Path path = fileServiceRecipe.createTempFile("Recipes");
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
                for (Recipe recipe : recipeMap.values()) {
                    writer.append(recipe.toString());
                    writer.append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
            return path.toFile();
        }



    }
