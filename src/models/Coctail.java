package models;


import java.util.ArrayList;
import java.util.List;

public class Coctail {
    public String name;
    public List<String> cooking;
    public List<String> Ingredients;


    public int amount;

    public Coctail(String name,Ingredient... ing){
        Ingredients = new ArrayList<String>();

        cooking = new ArrayList<String>();
        this.name = name;
        for(Ingredient s:ing){
            Ingredients.add(s.name);

            cooking.add(s.cooking);
            amount = amount+s.amount;
        }
    }

    public Coctail(){
        Ingredients = new ArrayList<String>();
        cooking = new ArrayList<String>();
        String CoctailName = "Coctail";
        String[] IngredientNames = {"Виски", "Кола", "Лед", "Джин", "Кофе"};
        this.name=CoctailName+((int)(Math.random() * ((90 - 10) + 1)) + 10);
        cooking.add(Cooking.values()[(int)((Math.random() * ((3 - 0) + 1)))].toString());
        Ingredients.add( IngredientNames[(int)((Math.random() * ((4 - 0) + 1)))]);
        this.amount = (int)(Math.random() * ((100 - 10) + 1)) + 10;
    }

    public String toString(){
        return "Название: "+name+" Ингредиенты "+Ingredients+" Действия по приготовлению: "+ cooking +" Объем: "+amount+"мл";
    }


}


