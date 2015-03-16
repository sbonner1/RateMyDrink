package com.rateMyDrink.modelClasses;

import com.fasterxml.jackson.core.JsonParseException;

import java.io.IOException;

/**
 * Created by shanembonner on 1/28/15.
 */

public class Drink {
    protected String drinkName;
    protected String description;
    protected float rating;
    protected int id;

    //constructor
    public Drink(){

    }

    public Drink(String name, String desc){
        this.drinkName = name;
        this.description = desc;
        this.rating = 0;
        this.id = 0;
    }

    public Drink(String json) throws JsonParseException, IOException {
//        this.description = JSON.getObjectMapper().readValue("description", String.class);
//        this.drinkName = JSON.getObjectMapper().readValue("drinkName", String.class);
//        this.rating = JSON.getObjectMapper().readValue("rating", Float.class);
//        this.id = JSON.getObjectMapper().readValue("id", Integer.class);
        Drink d = JSON.getObjectMapper().readValue(json, Drink.class);
        this.drinkName = d.getDrinkName();
        this.description = d.getDescription();
        this.rating = d.getRating();
        this.id = d.getId();
    }


    //setter for the id, which corresponds to its id in the main Drink database table
    public void setId(int value){
        this.id = value;
    }

    //getter for the database id
    public int getId(){
        return this.id;
    }

    //setter for drink name
    public void setDrinkName(String name){
        this.drinkName = name;
    }

    //getter for drink name
    public String getDrinkName(){
        return this.drinkName;
    }

    //setter for the description of the drink
    public void setDescription(String descr){
        this.description = descr;
    }

    //getter for the description of the drink
    public String getDescription(){
        return this.description;
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


