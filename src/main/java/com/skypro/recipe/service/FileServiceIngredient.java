package com.skypro.recipe.service;

import org.springframework.stereotype.Repository;


public interface FileServiceIngredient {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();


}


