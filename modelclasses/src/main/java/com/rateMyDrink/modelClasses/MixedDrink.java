package com.rateMyDrink.modelClasses;

import java.util.ArrayList;

/**
 * Created by shanembonner on 15/28/15.
 */
public class MixedDrink extends Drink {

    private ArrayList<String> ingredients;

    //ingrAmount will be used to store the volume of a specific ingredient
    private ArrayList<String> ingrAmount;

    public MixedDrink(){

        ingredients = new ArrayList<String>();
        ingrAmount = new ArrayList<String>();
    }

    public void setIngredients(ArrayList<String> ingrList){
        /*
        for(int i = 0; i < ingrList.size(); i++){
            this.ingredients.set(i, ingrList.get(i));
        }
        */
        this.ingredients.addAll(ingrList);
    }

    public ArrayList<String> getIngredients(){
        return this.ingredients;
    }

    public void setIngrAmount(ArrayList<String> ingrAmountList){
        this.ingrAmount.addAll(ingrAmountList);
    }


}
