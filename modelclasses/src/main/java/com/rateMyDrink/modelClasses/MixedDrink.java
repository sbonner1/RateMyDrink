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
    private LiquorType maxIng;

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

    //Set the max ingredient
    public void setMaxIngredient(LiquorType setMaxIng) { this.maxIng = setMaxIng; }

    //Get max ingredient to be displayed
    public String getMaxIngredient()
    {
        switch(this.maxIng)
        {
            case RUM:               return "Rum";
            case WHISKEY:           return "Whiskey";
            case VODKA:             return "Vodka";
            case CHOCOLATELIQUOR:   return "Chocolate Liquor";
            case ABSINTHE:          return "Absinthe";
            case BRANDY:            return "Brandy";
            case GIN:               return "Gin";
            case TEQUILA:           return "Tequila";
            case BOURBON:           return "Bourbon";
            case SCOTCH:            return "Scotch";
            case COGNAC:            return "Cognac";
            default:                return null;
        }
    }
}
