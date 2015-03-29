package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 3/27/15.
 */
public class Ingredient {
    private int drinkId;
    private String name;
    private double amount;

    public Ingredient(){

    }

    public void setDrinkId(int value){
        this.drinkId = value;
    }

    public int getDrinkId(){
        return this.drinkId;
    }

    public void setIngredientName(String value){
        this.name = value;
    }

    public String getIngredientName(){
        return this.name;
    }

    public void setAmount(double value){
        this.amount = value;
    }

    public double getAmount(){
        return this.amount;
    }
}
