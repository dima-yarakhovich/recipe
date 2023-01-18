package com.skypro.recipe.controller;

import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.service.impl.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.Collection;


@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Операции добавления/удаления/редактирования/просмотра ингредиентов:")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента", description = "Для добавления ингредиента необходимо названиие, кол-во и едю измерения")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добавлен",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр ингредиента", description = "можно искать по порядковому номеру")
    @Parameters(value = {
            @Parameter(example = "0-100")

    })
    @ApiResponses(value =  {@ApiResponse(responseCode = "200", description = "ингредиент был найден"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})

    public ResponseEntity<?> getIngredient(@PathVariable Long id) {
        Ingredient ingredient = this.ingredientService.getIngredient(id);
        if (ingredient == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    @Operation(summary = "Просмотр всех ингредиентов приложения")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список ингредиентов:",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Collection<Ingredient> getAllIngredient() {
        return this.ingredientService.getAllIngredient();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента", description = "нужно удалять по порядковому номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Ингредиент удален"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})


    public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента", description = "можно редактировать по порядковому номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент отредактирован",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

}


