package com.skypro.recipe.service.impl;
import com.skypro.recipe.model.Ingridient;
import com.skypro.recipe.service.IngridientServices;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@Data
public class IngridientServiceImpl implements IngridientServices {
    private  Map<Long, Ingridient> ingridientMap = new HashMap<>();
    private static long lastId=0;

    @Override
    public Ingridient addIngridient(Ingridient ingridient) {
            ingridientMap.put(this.lastId++, ingridient);
        return ingridient;
    }

    @Override
    public Ingridient getIngridient(long id) {
            return ingridientMap.get(id);
    }

}
