package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 1/28/15.
 */

public class Drink {
    protected String drinkName;
    protected float rating;
    protected int id;

    //constructor
    public Drink(){

    }

    //setter for drink name
    public void setDrinkName(String name){
        this.drinkName = name;
    }

    //getter for drink name
    public String getDrinkName(){
        return this.drinkName;
    }

    //setter for drink rating
    public void setRating(float rating){
        this.rating = rating;
    }

    //getter for drink rating
    public float getRating(){
        return this.rating;
    }

}


