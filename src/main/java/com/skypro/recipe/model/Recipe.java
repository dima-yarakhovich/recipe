package com.skypro.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Recipe {
    private String name;
    private int cookingTime;
    private List ingredients;
    private List steps;
}
