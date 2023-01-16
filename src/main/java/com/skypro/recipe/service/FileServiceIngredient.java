package com.skypro.recipe.service;

import java.io.File;
import java.nio.file.Path;


public interface FileServiceIngredient {
    boolean saveToFile(String json);

    String readFromFile();


    boolean cleanDataFileIngr();


    File getDataFileIngr();
}


