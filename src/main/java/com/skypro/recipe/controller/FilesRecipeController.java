package com.skypro.recipe.controller;


import com.skypro.recipe.service.FileServiceRecipe;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")

public class FilesRecipeController {
    private final FileServiceRecipe fileServiceRecipe;

    public FilesRecipeController(FileServiceRecipe fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileServiceRecipe.getDataFile();
        if (file.exists()) {                                                                                               //проверяем, что он существует
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));                             //открываем поток
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)                                                       //заголовок запроса
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"List_Recipe.json\"")  //чтобы добавить заголовок вручную
                    .body(resource);                                                                                       //передаем тело/сам инпутстриим

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileServiceRecipe.cleanDataFile();
        File dataFile = fileServiceRecipe.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
