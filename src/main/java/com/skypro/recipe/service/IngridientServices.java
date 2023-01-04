package com.skypro.recipe.service;
import com.skypro.recipe.model.Ingridient;


public interface IngridientServices {

    Ingridient addIngridient(Ingridient ingridient);

    Ingridient getIngridient(long id);
}
