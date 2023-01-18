package com.skypro.recipe.service;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Path;


public interface FileServiceRecipe {
    boolean saveToFile(String json);

    String readRecipeFromFile();


    boolean cleanDataFile();


    File getDataFile();


    Path createTempFile(String suffix);
}
