package com.skypro.recipe.service.impl;

import com.skypro.recipe.service.FileServiceIngredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceIngredientImpl implements FileServiceIngredient {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.to.data.file}")
    private String dataFileName;


    @Override
    public boolean saveToFile(String json) {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            cleanDataFileIngr();
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
    public Path createTempFileIngr(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", "suffix");   //временный файл
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean cleanDataFileIngr() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFileIngr() {
        return new File(dataFilePath + "/" + dataFileName);
    }
}
