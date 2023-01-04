package com.skypro.recipe.service.impl;

import com.skypro.recipe.model.Recipte;
import com.skypro.recipe.service.RecipteServices;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class RecipteServiceImpl implements RecipteServices {
    private final Map<Long, Recipte> recipteMap = new HashMap<>();
    private static long lastId = 0;


    @Override
    public Recipte addRecipte(Recipte recipte) {
        recipteMap.put(this.lastId++, recipte);
        return recipte;
    }

    @Override
    public Recipte getRecipte(Long id) {
        return recipteMap.get(id);
    }

    @Override
    public boolean deleteRecipte(Long id) {
        if (recipteMap.containsKey(id)) {
            recipteMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Recipte editRecipte(Long id, Recipte recipte) {
        if (recipteMap.containsKey(id)) {
            recipteMap.put(id, recipte);
            return recipte;
        }
        return null;
    }

    @Override
    public List<Recipte> getAllRecipte() {
        return new ArrayList<>(this.recipteMap.values());
    }
}
