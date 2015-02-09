package com.rateMyDrink.modelClasses;

import java.util.ArrayList;

/**
 * Created by shanembonner on 15/28/15.
 */
public class MixedDrink extends Drink {
    private ArrayList<String> ingredients;

    public MixedDrink(){
        ingredients = new ArrayList<String>();
    }

    public void setIngredients(ArrayList<String> ingrList){
        for(int i = 0; i < ingrList.size(); i++){
            this.ingredients.set(i, ingrList.get(i));
        }
    }


}
