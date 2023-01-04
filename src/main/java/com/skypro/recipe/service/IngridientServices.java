package com.skypro.recipe.service;
import com.skypro.recipe.model.Ingridient;

import java.util.List;


public interface IngridientServices {

    Ingridient addIngridient(Ingridient ingridient);

    Ingridient getIngridient(long id);
    Ingridient editIngridient(long id, Ingridient ingridient);
    boolean deleteIngridient(long id);
    List<Ingridient> getAllIngridient();
}
