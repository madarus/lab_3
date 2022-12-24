package models;

import java.util.ArrayList;
import java.util.List;

public class AlcoCoctail extends Coctail{
    public List<Integer> strength;
    public AlcoCoctail(String name, Ingredient... ing) {
        super(name,  ing);
        strength = new ArrayList<Integer>();
        for(Ingredient s:ing)
            strength.add(s.strength);

    }


    public double getStrength(){
        return strength.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public String toString(){
        return "Название: "+name+" Ингредиенты "+Ingredients+" Действия по приготовлению: "+ cooking +" Крепость: "+getStrength()+" Объем: "+amount+"мл";
    }
}
