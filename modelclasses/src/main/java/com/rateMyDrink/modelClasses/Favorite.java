package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 5/3/15.
 */
public class Favorite {

    private int drinkId;
    private int userId;

    public Favorite(){

    }


    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
