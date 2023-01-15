package com.skypro.recipe.service.impl;

import com.skypro.recipe.service.FileServiceIngredient;
import com.skypro.recipe.service.FileServiceRecipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceRecipeImpl implements FileServiceRecipe {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name1.to.data.file}")
    private String dataFileName;


    @Override
    public boolean saveToFile(String json) {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            cleanDataFile();
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile() {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

