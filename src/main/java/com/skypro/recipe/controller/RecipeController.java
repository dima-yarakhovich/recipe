package com.skypro.recipe.controller;

import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.impl.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Операции добавления/удаления/редактирования/просмотра рецептов:")
public class RecipeController {
    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр рецепта", description = "можно искать по порядковому номеру")
    @Parameters(value = {
            @Parameter(example = "0-100")

    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт найден"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})

    public Recipe getRecipe(@PathVariable Long id) {
        return this.recipeService.getRecipe(id);
    }


    @PostMapping
    @Operation(summary = "Добавление рецепта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping
    @Operation(summary = "Просмотр всех рецептов приложения")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все рецерты доступны для просмотра",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Collection<Recipe> getAllRecipe() {
        return this.recipeService.getAllRecipe();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "нужно удалять по порядковому номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт отредактирован"),
            @ApiResponse(responseCode = "400", description = "имеется ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "такого действия не существует либо URL неверный в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    @Operation(summary = "Редактирование рецепта", description = "можно редактировать по порядковому номеру")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
}
