package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 12/28/14.
 */
public class Beer extends Drink {
    private double ABV;
    private int calories;
    //TODO: add more fields

    protected void setABV (int abv){
        this.ABV = abv;
    }

    protected double getABV(){
        return this.ABV;
    }

    protected void setCalories(int calories){
        this.calories = calories;
    }


}
