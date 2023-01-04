package com.skypro.recipe.service;
import com.skypro.recipe.model.Recipte;

import java.util.List;

public interface RecipteServices {
    Recipte addRecipte(Recipte recipte);

    Recipte getRecipte(Long id);

    boolean deleteRecipte(Long id);

    Recipte editRecipte(Long id, Recipte recipte);

    List<Recipte> getAllRecipte();


}
