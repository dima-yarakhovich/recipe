package com.skypro.recipe.service;
import com.skypro.recipe.model.Recipte;

public interface RecipteServices {
    Recipte addRecipte(Recipte recipte);

    Recipte getRecipte(Long id);

}
