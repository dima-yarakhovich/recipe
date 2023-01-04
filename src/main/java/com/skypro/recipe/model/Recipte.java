package com.skypro.recipe.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Recipte {

    private  String name;
    private  int cookingTime;
    ArrayList<Ingridient> ingridients;
    ArrayList<String> steps;
}
