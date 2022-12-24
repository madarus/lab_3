package models;

public class Ingredient {
    public String name;
    public int amount;
    public int strength;
    public String cooking;

    public Ingredient(String name, int amount, int strength, String cooking){
        this.name = name;
        this.amount = amount;
        this.strength = strength;
        this.cooking = cooking;
    }
}
