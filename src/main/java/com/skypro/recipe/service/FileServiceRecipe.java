package com.skypro.recipe.service;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Path;


public interface FileServiceRecipe {
    boolean saveToFile(String json);

    String readFromFile();


    boolean cleanDataFile();


    File getDataFile();
}
