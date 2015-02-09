package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 1/28/15.
 */

public class Drink {
    private String drinkName;
    private float rating;

    public Drink(){

    }

    public void setDrinkName(String name){
        this.drinkName = name;
    }

    public String getDrinkName(){
        return this.drinkName;
    }


}


