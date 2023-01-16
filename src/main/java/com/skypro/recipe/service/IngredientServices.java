package com.skypro.recipe.service;

import com.skypro.recipe.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface IngredientServices {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(long id);

    Ingredient editIngredient(long id, Ingredient ingredient);


    boolean deleteIngredient(long id);

    List<Ingredient> getAllIngredient();
}
