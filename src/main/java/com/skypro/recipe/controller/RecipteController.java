package com.skypro.recipe.controller;

import com.skypro.recipe.model.Recipte;
import com.skypro.recipe.service.impl.RecipteServiceImpl;
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
@RequestMapping("/recipte")
@Tag(name = "Рецепты",description = "Операции добавления/удаления/редактирования/просмотра рецептов:")
public class RecipteController {
    private final RecipteServiceImpl recipteService;

    public RecipteController(RecipteServiceImpl recipteService) {
        this.recipteService = recipteService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр рецепта",description = "можно искать по порядковому номеру")
    @Parameters(value = {
            @Parameter(example = "0-100")

    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation =Recipte.class))
                            )
                    }
            )
    })
    public Recipte getRecipe(@PathVariable Long id) {
        return this.recipteService.getRecipte(id);
    }


    @PostMapping
    @Operation(summary = "Добавление рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation =Recipte.class))
                            )
                    }
            )
    })
    public ResponseEntity<?> addRecipe(@RequestBody Recipte recipte) {
        return ResponseEntity.ok(recipteService.addRecipte(recipte));
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
                                    array = @ArraySchema(schema = @Schema(implementation =Recipte.class))
                            )
                    }
            )
    })
    public Collection<Recipte> getAllRecipte() {
        return this.recipteService.getAllRecipte();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта",description = "нужно удалять по порядковому номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation =Recipte.class))
                            )
                    }
            )
    })
    public  ResponseEntity<Void> deleteRecipte(@PathVariable long id) {
        if (recipteService.deleteRecipte(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт отредактирован",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation =Recipte.class))
                            )
                    }
            )
    })
    @Operation(summary = "Редактирование рецепта",description = "можно редактировать по порядковому номеру")
    public ResponseEntity<Recipte> editRecipte(@PathVariable long id, @RequestBody Recipte recipte) {
        Recipte recipte1 = recipteService.editRecipte(id, recipte);
        if (recipte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipte);
    }
}
