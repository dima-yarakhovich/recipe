package com.skypro.recipe.controller;


import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.service.FileServiceRecipe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Скачивание рецептов в формате txt",
            description = "можно скачать рецепты в txt")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Запрос выполнился"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
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
    @Operation(summary = "Добавление рецептов из файла",
            description = "можно добавить рецепты в том числе из файла txt")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Запрос выполнился"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
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
