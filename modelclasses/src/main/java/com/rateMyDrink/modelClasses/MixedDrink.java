package com.rateMyDrink.modelClasses;

import java.util.ArrayList;

/**
 * Created by shanembonner on 15/28/15.
 */
public class MixedDrink extends Drink {

    private int id;
    private String instructions;
    private ArrayList<String> ingredients;
    private ArrayList<String> ingrAmount;

    public MixedDrink(){

        ingredients = new ArrayList<String>();
        ingrAmount = new ArrayList<String>();
    }

    public void setId(int value){
        this.id = value;
    }

    public int getId(){
        return this.id;
    }

    public void setInstructions(String instr){
        this.instructions = instr;
    }

    public String getInstructions(){
        return this.instructions;
    }

    public void setIngredients(ArrayList<String> ingrList){
        this.ingredients.addAll(ingrList);
    }

    public ArrayList<String> getIngredients(){
        return this.ingredients;
    }

    public void setIngrAmount(ArrayList<String> ingrAmountList){
        this.ingrAmount.addAll(ingrAmountList);
    }


}
