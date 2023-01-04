package com.skypro.recipe.controller;

import com.skypro.recipe.model.Recipte;
import com.skypro.recipe.service.impl.RecipteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/recipte")
public class RecipteController {
    private final RecipteServiceImpl recipteService;

    public RecipteController(RecipteServiceImpl recipteService) {
        this.recipteService = recipteService;
    }

    @GetMapping("/{id}")
    public Recipte getRecipe(@PathVariable Long id) {
        return this.recipteService.getRecipte(id);
    }


    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody Recipte recipte) {
        return ResponseEntity.ok(recipteService.addRecipte(recipte));
    }

    @GetMapping
    public Collection<Recipte> getAllRecipte() {
        return this.recipteService.getAllRecipte();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipte(@PathVariable long id) {
        if (recipteService.deleteRecipte(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipte> editRecipte(@PathVariable long id, @RequestBody Recipte recipte) {
        Recipte recipte1 = recipteService.editRecipte(id, recipte);
        if (recipte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipte);
    }
}
