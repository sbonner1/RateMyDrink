package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 2/5/15.
 */
public class Liquor extends Drink {
    private int id;
    private float content;

    public Liquor(){

    }

    public void setAlcoholContent(float content){
        this.content = content;
    }

    public float getAlcoholContent(){
        return this.content;
    }
}
