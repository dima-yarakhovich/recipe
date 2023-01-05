package com.skypro.recipe.controller;

import com.skypro.recipe.model.Ingridient;
import com.skypro.recipe.model.Recipte;
import com.skypro.recipe.service.impl.IngridientServiceImpl;
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
@RequestMapping("/ingridient")
@Tag(name = "Ингредиенты", description = "Операции добавления/удаления/редактирования/просмотра ингредиентов:")
public class IngridientController {
    private final IngridientServiceImpl ingridientService;

    public IngridientController(IngridientServiceImpl ingridientService) {
        this.ingridientService = ingridientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингридиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добавлен",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingridient.class))
                            )
                    }
            )
    })
    public ResponseEntity<?> addIngridient(@RequestBody Ingridient ingridient) {
        return ResponseEntity.ok(ingridientService.addIngridient(ingridient));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр ингридиента", description = "можно искать по порядковому номеру")
    @Parameters(value = {
            @Parameter(example = "0-100")

    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Просмотр ингредиента",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingridient.class))
                            )
                    }
            )
    })
    public ResponseEntity getIngridient(@PathVariable Long id) {
        Ingridient ingridient = this.ingridientService.getIngridient(id);
        if (ingridient == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }

    @GetMapping
    @Operation(summary = "Просмотр всех ингридиентов приложения")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список ингредиентов:",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingridient.class))
                            )
                    }
            )
    })
    public Collection<Ingridient> getAllIngridient() {
        return this.ingridientService.getAllIngridient();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингридиента", description = "нужно удалять по порядковому номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удален",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingridient.class))
                            )
                    }
            )
    })
    public ResponseEntity<Void> deleteIngridient(@PathVariable long id) {
        if (ingridientService.deleteIngridient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингридиента", description = "можно редактировать по порядковому номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент отредоктирован",
                    content = {
                            @Content(
                                    mediaType = "aplication/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingridient.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingridient> editIngridient(@PathVariable long id, @RequestBody Ingridient ingridient) {
        Ingridient ingridient1 = ingridientService.editIngridient(id, ingridient);
        if (ingridient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }
}


