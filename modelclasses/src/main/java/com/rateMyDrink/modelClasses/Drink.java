package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 12/28/14.
 */

public class Drink {
    private String drinkName;

    public Drink(){

    }

    protected void setDrinkName(String name){
        this.drinkName = name;
    }

    protected String getDrinkName(){
        return this.drinkName;
    }


}
