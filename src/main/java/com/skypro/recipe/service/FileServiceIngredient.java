package com.skypro.recipe.service;

import java.io.File;
import java.nio.file.Path;


public interface FileServiceIngredient {
    boolean saveToFile(String json);

    String readFromFile();


    Path createTempFileIngr(String suffix);

    boolean cleanDataFileIngr();


    File getDataFileIngr();
}


