package com.rateMyDrink.modelClasses;

import java.util.ArrayList;

/**
 * Created by shanembonner on 15/28/15.
 */
public class MixedDrink extends Drink {

    //private int id;
    private String instructions;
    private LiquorType maxIng;
    private ArrayList<Ingredient> ingredients;
   // private ArrayList<Double> ingrAmount;
  //  public String[][] recipe;

    public MixedDrink(){

        ingredients = new ArrayList<Ingredient>();
       // ingrAmount = new ArrayList<Double>();
       // recipe = new String[15][15]; //using a double array would be easier to keep all values in the same order
                                     //and make it easier to store in the database
    }

    public MixedDrink(String name, String desc, String instructions, LiquorType type, ArrayList<Ingredient> ingredientsList){
        this.id = 0;
        this.rating = 0.0f;
        this.drinkName = name;
        this.description = desc;
        this.instructions = instructions;
        this.maxIng = type;
        this.ingredients = ingredientsList;
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

    public void setIngredients(ArrayList<Ingredient> ingrList){
       this.ingredients.addAll(ingrList);
    }

    public ArrayList<Ingredient> getIngredients(){
        return this.ingredients;
    }

    //public void setIngrAmount(ArrayList<Double> ingrAmountList){this.ingrAmount.addAll(ingrAmountList); }

    //public ArrayList<Double> getIngrAmount(){
    //   return this.ingrAmount;
    //}

    //Set the max ingredient
    public void setMaxIngredient(LiquorType setMaxIng) { this.maxIng = setMaxIng; }

    public LiquorType getMaxIngedient(){
        return this.maxIng;
    }
    //Get max ingredient to be displayed
    public String getMaxIngredientReadableType()
    {
        switch(this.maxIng)
        {
            case RUM:               return "RUM";
            case WHISKEY:           return "WHISKEY";
            case VODKA:             return "VODKA";
            case CHOCOLATELIQUOR:   return "CHOCOLATE LIQUOR";
            case ABSINTHE:          return "ABSINTHE";
            case BRANDY:            return "BRANDY";
            case GIN:               return "GIN";
            case TEQUILA:           return "TEQUILA";
            case BOURBON:           return "BOURBON";
            case SCOTCH:            return "SCOTCH";
            case COGNAC:            return "COGNAC";
            default:                return null;
        }
    }
}
