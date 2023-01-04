package com.skypro.recipe.controller;
import com.skypro.recipe.model.Ingridient;
import com.skypro.recipe.service.impl.IngridientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/ingridient")
public class IngridientController {
    private final IngridientServiceImpl ingridientService;

    public IngridientController(IngridientServiceImpl ingridientService) {
        this.ingridientService = ingridientService;
    }

    @PostMapping
    public ResponseEntity<?> addIngridient(@RequestBody Ingridient ingridient) {
        return ResponseEntity.ok(ingridientService.addIngridient(ingridient));
    }

    @GetMapping("/{id}")
    public ResponseEntity getIngridient(@PathVariable Long id) {
        Ingridient ingridient = this.ingridientService.getIngridient(id);
        if (ingridient == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }
    @GetMapping
    public Collection<Ingridient> getAllIngridient() {
        return this.ingridientService.getAllIngridient();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngridient(@PathVariable long id) {
        if (ingridientService.deleteIngridient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingridient> editIngridient(@PathVariable long id, @RequestBody Ingridient ingridient) {
        Ingridient ingridient1 = ingridientService.editIngridient(id, ingridient);
        if (ingridient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingridient);
    }
}


