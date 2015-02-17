package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 1/28/15.
 */
public class Beer extends Drink {
    private int id;
    private double ABV;
    private int calories;

    //TODO: add more fields

    public void setABV (int abv){
        this.ABV = abv;
    }

    public double getABV(){
        return this.ABV;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    public int getCalories() { return this.calories; }

    //TODO: getters/setters for enum BeerType

}
